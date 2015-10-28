package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Cosine implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.cos(Math.toRadians(args[0].evaluate()));
	}

}
