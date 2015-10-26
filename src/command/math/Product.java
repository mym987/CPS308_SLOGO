package command.math;

import command.Command;
import parser.ParseFormatException;

class Product implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double product = 1;
		for (Command cmd : args)
			product *= cmd.evaluate();
		return product;
	}

}
