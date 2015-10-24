package command.math;

import command.Command;

class ArcTangent implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.toDegrees(Math.atan(args[0].evaluate()));
	}

}
