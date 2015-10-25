package command.math;

import command.Command;
import parser.ParseFormatException;

class Sum implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double sum = 0;
		for (Command cmd : args)
			sum += cmd.evaluate();
		return sum;
	}

}
