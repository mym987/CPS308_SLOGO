package command.turtle;

import java.util.Set;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

/**
 * tell turtles given in first list to run commands given in the second list
 * returns result of last command run note, after commands are run, currently
 * active list of turtles returns to that set by the last TELL command (or
 * default active turtle if TELL never given) note, if more than one turtle is
 * active, commands run return value associated with the last active turtle
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
	public double evaluate(Command... args) throws ParseFormatException {

		Command body = args[1];
		int id = myActions.id();
		Set<Integer> original = myActions.getFollowers();
		(new Tell(myActions)).evaluate(args[0]);
		double val = body.evaluate();
		myActions.setFollowers(original);
		myActions.setActive(id);
		return val;

	}

}
