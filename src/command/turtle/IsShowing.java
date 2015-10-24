package command.turtle;

import action.Actions;
import command.Command;

class IsShowing implements Command {

	private Actions myActions;
	
	public IsShowing(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.isShowing();
	}

}
