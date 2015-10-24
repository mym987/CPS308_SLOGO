package command.turtle;

import action.Actions;
import command.Command;

class ID implements Command {

	private Actions myActions;
	
	public ID(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.id();
	}

}
