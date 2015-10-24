package command.math;

import command.Command;

class NaturalLog implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.log(args[0].evaluate());
	}

}
