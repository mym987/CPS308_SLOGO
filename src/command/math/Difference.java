package command.math;

import command.Command;
import parser.ParseFormatException;

class Difference implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double diff = args[0].evaluate();
		for (int i = 1; i < args.length; i++)
			diff -= args[i].evaluate();
		return diff;
	}

}
