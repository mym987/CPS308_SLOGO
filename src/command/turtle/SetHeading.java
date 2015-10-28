package command.turtle;

import action.Actions;
import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class SetHeading implements Command {

	private Actions myActions;
	
	public SetHeading(Actions actions){
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return myActions.setHeading(args[0].evaluate());
	}

}
