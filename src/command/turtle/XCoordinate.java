package command.turtle;

import action.Actions;
import command.Command;

class XCoordinate implements Command {

	private Actions myActions;
	
	public XCoordinate(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.xCoordinate();
	}

}
