
package command.turtle;

import java.util.HashSet;
import java.util.Set;

import action.Actions;
import command.Command;
import command.CommandList;
/**
 * sets turtles that will follow commands hereafter returns last value in
 * turtles list note, if turtle has not previously existed, it is created
 * and placed at the home location note, if more than one turtle is active,
 * commands run return value associated with the last active turtle
 * @author Mike Ma (ym67)
 *
 */
class Tell implements Command {

	private Actions myActions;

	public Tell(Actions actions) {
		myActions = actions;
	}

	@Override
	public double evaluate(Command... args) {
		CommandList list = (CommandList) args[0];
		double val = 0;
		Set<Integer> set = new HashSet<>();
		for (Command e : list) {
			val = e.evaluate();
			set.add((int) val);
		}
		myActions.setFollowers(set);
		myActions.setActive((int) val);
		return val;
	}

}
