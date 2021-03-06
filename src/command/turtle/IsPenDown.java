package command.turtle;

import action.Actions;
import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class IsPenDown implements Command {

	private Actions myActions;
	
	public IsPenDown(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.isPenDown();
	}

}
