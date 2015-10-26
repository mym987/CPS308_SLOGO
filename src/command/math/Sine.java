package command.math;

import command.Command;
import parser.ParseFormatException;

class Sine implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.sin(Math.toRadians(args[0].evaluate()));
	}

}
