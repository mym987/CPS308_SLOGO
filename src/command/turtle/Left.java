package command.turtle;

import action.Actions;
import command.Command;

class Left implements Command {

	private Actions myActions;
	
	public Left(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		double value = 0;
		for(Command cmd:args)
			value = myActions.left(cmd.evaluate());
		return value;
	}

}
