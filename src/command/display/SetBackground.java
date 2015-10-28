package command.display;

import action.Actions;
import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class SetBackground implements Command {
	
	private Actions myActions;

	public SetBackground(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return myActions.setBackground(args[0].evaluate());
	}

}
