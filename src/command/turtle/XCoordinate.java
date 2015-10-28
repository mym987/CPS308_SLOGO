package command.turtle;

import action.Actions;
import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class XCoordinate implements Command {

	private Actions myActions;
	
	public XCoordinate(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.xCoordinate();
	}

}
