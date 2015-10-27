package command.display;

import action.Actions;
import command.Command;

class Stamp implements Command {
	
	private Actions myActions;

	public Stamp(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.stamp();
	}

}
