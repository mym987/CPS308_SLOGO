package command.display;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

class SetPenSize implements Command {
	
	private Actions myActions;

	public SetPenSize(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return myActions.setPenSize(args[0].evaluate());
	}

}
