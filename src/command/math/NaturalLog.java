package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class NaturalLog implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return Math.log(args[0].evaluate());
	}

}
