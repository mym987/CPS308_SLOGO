package command.math;

import command.Command;

class Remainder implements Command {

	@Override
	public double evaluate(Command... args) {
		return args[0].evaluate() % args[1].evaluate();
	}

}
