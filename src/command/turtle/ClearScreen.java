package command.turtle;

import action.Actions;
import command.Command;

class ClearScreen implements Command {

	private Actions myActions;
	
	public ClearScreen(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.clearScreen();
	}

}
