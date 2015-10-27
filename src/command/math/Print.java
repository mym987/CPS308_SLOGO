package command.math;

import command.Command;
import parser.ParseFormatException;

public class Print implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double val = args[0].evaluate();
		System.out.println(val);
		return val;
	}

}
