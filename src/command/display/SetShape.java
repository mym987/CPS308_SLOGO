package command.display;

import action.Actions;
import command.Command;

class SetShape implements Command {
	
	private Actions myActions;

	public SetShape(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setShape(args[0].evaluate());
	}

}
