package command.turtle;

import action.Actions;
import command.Command;

class Backward implements Command {

	private Actions myActions;
	
	public Backward(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		double value = 0;
		for(Command cmd:args)
			value = myActions.forward(cmd.evaluate());
		return value;
	}

}
