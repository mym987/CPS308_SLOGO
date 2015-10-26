package command.turtle;

import java.util.HashSet;
import java.util.Set;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

/**
 * tell turtles matching given condition to run commands given in the second
 * list returns result of last command run note, after commands are run,
 * currently active list of turtles returns to that set by the last TELL command
 * (or default active turtle if TELL never given) note, if more than one turtle
 * is active, commands run return value associated with the last active turtle
 * 
 * @author Mike Ma (ym67)
 *
 */
class AskWith implements Command {

	private Actions myActions;

	public AskWith(Actions actions) {
		myActions = actions;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		Command condition = args[0], body = args[1];
		int id = myActions.id();
		Set<Integer> original = myActions.getFollowers();
		int max = myActions.turtles();
		double val = 0;
		for (int i = 1; i <= max; i++) {
			Set<Integer> tmp = new HashSet<>();
			tmp.add(i);
			myActions.setFollowers(tmp);
			if (condition.evaluate() != 0)
				val = body.evaluate();
		}
		myActions.setFollowers(original);
		myActions.setActive(id);
		return val;
	}

}
