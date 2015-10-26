package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class SetPosition implements Command {

	private Actions myActions;
	
	public SetPosition(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return myActions.setPosition(args[0].evaluate(), args[1].evaluate());
	}

}
