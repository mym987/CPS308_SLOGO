package command.display;

import action.Actions;
import command.Command;

public class SetPalette implements Command {
	
	private Actions myActions;

	public SetPalette(Actions actions) {
		myActions = actions;
	}
	@Override
	public double evaluate(Command... args) {
		return myActions.setPalette(args[0].evaluate(), args[1].evaluate(), args[2].evaluate(),
				args[3].evaluate());
	}

}
