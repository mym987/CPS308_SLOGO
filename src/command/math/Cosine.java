package command.math;

import command.Command;

public class Cosine implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.cos(Math.toRadians(args[0].evaluate()));
	}

}
