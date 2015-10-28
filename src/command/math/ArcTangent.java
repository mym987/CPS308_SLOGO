package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class ArcTangent implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.toDegrees(Math.atan(args[0].evaluate()));
	}

}
