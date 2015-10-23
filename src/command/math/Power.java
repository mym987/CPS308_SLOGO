package command.math;

import command.Command;

public class Power implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.pow(args[0].evaluate(), args[1].evaluate());
	}

}
