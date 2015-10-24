package command.display;

import action.Actions;
import command.Command;

class SetPenColor implements Command {
	
	private Actions myActions;

	public SetPenColor(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setPenColor(args[0].evaluate());
	}

}
