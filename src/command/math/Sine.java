package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Sine implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.sin(Math.toRadians(args[0].evaluate()));
	}

}
