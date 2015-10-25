package command.math;

import command.Command;
import parser.ParseFormatException;

class Tangent implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.tan(Math.toRadians(args[0].evaluate()));
	}

}
