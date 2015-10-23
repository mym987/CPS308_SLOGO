package command.math;

import command.Command;

public class Minus implements Command {

	@Override
	public double evaluate(Command... args) {
		return -args[0].evaluate();
	}

}
