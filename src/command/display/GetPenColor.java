package command.display;

import action.Actions;
import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
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
