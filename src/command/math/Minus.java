package command.math;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Minus implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return -args[0].evaluate();
	}

}
