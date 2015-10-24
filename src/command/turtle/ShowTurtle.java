package command.turtle;

import action.Actions;
import command.Command;

class ShowTurtle implements Command {

	private Actions myActions;
	
	public ShowTurtle(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.showTurtle();
	}

}
