package command.factory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import command.Command;
import command.CommandList;
import model.Actions;
import parser.ParseFormatException;

class MultipleCommands {

	public static Command get(Actions actions, String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "ID":
			return (c) -> {
				return actions.id();
			};
		case "Turtles":
			return (c) -> {
				return actions.turtles();
			};
		case "Tell":
			return tell(actions, args);
		case "Ask":
			return ask(actions, args);
		case "AskWith":
			return askWith(actions, args);
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}

	/**
	 * sets turtles that will follow commands hereafter returns last value in
	 * turtles list note, if turtle has not previously existed, it is created
	 * and placed at the home location note, if more than one turtle is active,
	 * commands run return value associated with the last active turtle
	 * 
	 * @param actions
	 * @param args 
	 * @return Command
	 * @throws ParseFormatException
	 */
	private static Command tell(Actions actions, List<Command> args) throws ParseFormatException {
		CommandList list;
		try {
			list = (CommandList) args.get(0);
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		return (c) -> {
			double val = 0;
			Set<Integer> set = new HashSet<>();
			for (Command e : list) {
				val = e.evaluate();
				set.add((int) val);
			}
			actions.setFollowers(set);
			return val;
		};

	}

	/**
	 * tell turtles given in first list to run commands given in the second list
	 * returns result of last command run note, after commands are run,
	 * currently active list of turtles returns to that set by the last TELL
	 * command (or default active turtle if TELL never given) note, if more than
	 * one turtle is active, commands run return value associated with the last
	 * active turtle
	 * 
	 * @param actions
	 * @param args
	 * @return Command
	 * @throws ParseFormatException
	 */
	private static Command ask(Actions actions, List<Command> args) throws ParseFormatException {
		CommandList list;
		Command body;
		try {
			list = (CommandList) args.get(0);
			body = args.get(1);
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		return (c) -> {
			int id = actions.id();
			double val = 0;
			for (Command e : list) {
				actions.setActive((int) e.evaluate());
				val = body.evaluate();
			}
			actions.setActive(id);
			return val;
		};
	}

	/**
	 * tell turtles matching given condition to run commands given in the second
	 * list returns result of last command run note, after commands are run,
	 * currently active list of turtles returns to that set by the last TELL
	 * command (or default active turtle if TELL never given) note, if more than
	 * one turtle is active, commands run return value associated with the last
	 * active turtle
	 * 
	 * @param actions
	 * @param args
	 * @return Command
	 * @throws ParseFormatException
	 */
	private static Command askWith(Actions actions, List<Command> args) throws ParseFormatException {
		Command condition = args.get(0), body = args.get(1);
		return (c) -> {
			int id = actions.id(), max = actions.turtles();
			double val = 0;
			for(int i = 0;i<max;i++){
				actions.setActive(i);
				if(condition.evaluate()!=0)
					val = body.evaluate();
			}
			actions.setActive(id);
			return val;
		};
	}
}
