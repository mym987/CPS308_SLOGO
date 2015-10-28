package command.turtle;

import action.Actions;
import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Heading implements Command {

	private Actions myActions;
	
	public Heading(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.heading();
	}

}
