package command.math;

import command.Command;

public class Tangent implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.tan(Math.toRadians(args[0].evaluate()));
	}

}
