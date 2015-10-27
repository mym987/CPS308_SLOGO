package command.display;

import action.Actions;
import command.Command;

class GetPenColor implements Command {
	
	private Actions myActions;

	public GetPenColor(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.getPenColor();
	}

}
