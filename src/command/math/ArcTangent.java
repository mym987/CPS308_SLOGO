package command.math;

import command.Command;
import parser.ParseFormatException;

class ArcTangent implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.toDegrees(Math.atan(args[0].evaluate()));
	}

}
