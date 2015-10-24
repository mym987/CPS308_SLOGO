package command.turtle;

import action.Actions;
import command.Command;

class SetHeading implements Command {

	private Actions myActions;
	
	public SetHeading(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setHeading(args[0].evaluate());
	}

}
