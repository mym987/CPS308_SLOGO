package command.math;

import command.Command;

class Pi implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.PI;
	}

}
