package command.bool;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Not implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return (args[0].evaluate()==0)?1:0;
	}

}
