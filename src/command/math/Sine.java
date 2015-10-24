package command.math;

import command.Command;

class Sine implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.sin(Math.toRadians(args[0].evaluate()));
	}

}
