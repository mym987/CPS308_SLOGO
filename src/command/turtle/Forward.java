package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class Forward implements Command {

	private Actions myActions;
	
	public Forward(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double value = 0;
		for(Command cmd:args)
			value = myActions.forward(cmd.evaluate());
		return value;
	}

}
