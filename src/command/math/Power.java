package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Power implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.pow(args[0].evaluate(), args[1].evaluate());
	}

}
