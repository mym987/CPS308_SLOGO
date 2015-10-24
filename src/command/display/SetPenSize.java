package command.display;

import action.Actions;
import command.Command;

class SetPenSize implements Command {
	
	private Actions myActions;

	public SetPenSize(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setPenSize(args[0].evaluate());
	}

}
