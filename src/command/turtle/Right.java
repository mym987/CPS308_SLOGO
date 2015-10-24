package command.turtle;

import action.Actions;
import command.Command;

class Right implements Command {

	private Actions myActions;
	
	public Right(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		double value = 0;
		for(Command cmd:args)
			value = myActions.right(cmd.evaluate());
		return value;
	}

}
