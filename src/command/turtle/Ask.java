package command.turtle;

import action.Actions;
import command.Command;
import command.CommandList;
/**
 * tell turtles given in first list to run commands given in the second list
 * returns result of last command run note, after commands are run,
 * currently active list of turtles returns to that set by the last TELL
 * command (or default active turtle if TELL never given) note, if more than
 * one turtle is active, commands run return value associated with the last
 * active turtle
 * 
 * @author Mike Ma (ym67)
 *
 */
class Ask implements Command {

	private Actions myActions;

	public Ask(Actions actions) {
		myActions = actions;
	}

	@Override
	public double evaluate(Command... args) {
		CommandList list = (CommandList) args[0];
		Command body = args[1];
		int id = myActions.id();
		double val = 0;
		for (Command e : list) {
			myActions.setActive((int) e.evaluate());
			val = body.evaluate();
		}
		myActions.setActive(id);
		return val;
	}

}
