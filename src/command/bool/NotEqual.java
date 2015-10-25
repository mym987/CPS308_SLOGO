package command.bool;

import command.Command;
import parser.ParseFormatException;

class NotEqual implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return args[0].evaluate() != args[1].evaluate()?1:0;
	}

}
