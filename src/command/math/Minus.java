package command.math;

import command.Command;

class Minus implements Command {

	@Override
	public double evaluate(Command... args) {
		return -args[0].evaluate();
	}

}
