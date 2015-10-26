package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class Backward implements Command {

	private Actions myActions;
	
	public Backward(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double value = 0;
		for(Command cmd:args)
			value = myActions.backward(cmd.evaluate());
		return value;
	}

}
