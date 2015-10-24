package command.display;

import action.Actions;
import command.Command;

class SetBackground implements Command {
	
	private Actions myActions;

	public SetBackground(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setBackground(args[0].evaluate());
	}

}
