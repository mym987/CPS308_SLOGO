package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class Right implements Command {

	private Actions myActions;
	
	public Right(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double value = 0;
		for(Command cmd:args)
			value = myActions.right(cmd.evaluate());
		return value;
	}

}
