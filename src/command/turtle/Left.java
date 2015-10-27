package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class Left implements Command {

	private Actions myActions;
	
	public Left(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double value = 0;
		for(Command cmd:args)
			value = myActions.left(cmd.evaluate());
		return value;
	}

}
