package command.turtle;

import action.Actions;
import command.Command;

class PenUp implements Command {

	private Actions myActions;
	
	public PenUp(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.penUp();
	}

}
