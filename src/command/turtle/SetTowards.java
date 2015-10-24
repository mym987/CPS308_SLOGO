package command.turtle;

import action.Actions;
import command.Command;

class SetTowards implements Command {

	private Actions myActions;
	
	public SetTowards(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setTowards(args[0].evaluate(), args[1].evaluate());
	}

}
