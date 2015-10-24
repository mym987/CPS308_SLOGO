package command.display;

import action.Actions;
import command.Command;

public class ClearStamps implements Command {
	
	private Actions myActions;

	public ClearStamps(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.clearStamp();
	}

}
