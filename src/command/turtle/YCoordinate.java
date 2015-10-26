package command.turtle;

import action.Actions;
import command.Command;

class YCoordinate implements Command {

	private Actions myActions;
	
	public YCoordinate(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.yCoordinate();
	}

}
