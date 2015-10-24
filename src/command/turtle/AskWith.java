package command.turtle;

import action.Actions;
import command.Command;

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
	public double evaluate(Command... args) {
		Command condition = args[0], body = args[1];
		int id = myActions.id();
		int max = myActions.turtles();
		double val = 0;
		for (int i = 0; i < max; i++) {
			myActions.setActive(i);
			if (condition.evaluate() != 0)
				val = body.evaluate();
		}
		myActions.setActive(id);
		return val;
	}

}
