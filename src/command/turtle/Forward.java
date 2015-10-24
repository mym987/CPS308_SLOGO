package command.turtle;

import action.Actions;
import command.Command;

class Forward implements Command {

	private Actions myActions;
	
	public Forward(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		double value = 0;
		for(Command cmd:args)
			value = myActions.backward(cmd.evaluate());
		return value;
	}

}
