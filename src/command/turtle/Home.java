package command.turtle;

import action.Actions;
import command.Command;

class Home implements Command {

	private Actions myActions;
	
	public Home(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.home();
	}

}
