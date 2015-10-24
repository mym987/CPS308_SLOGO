package command.turtle;

import action.Actions;
import command.Command;

class Turtles implements Command {

	private Actions myActions;
	
	public Turtles(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.turtles();
	}

}
