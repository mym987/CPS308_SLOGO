package command.math;

import command.Command;

public class Difference implements Command {

	@Override
	public double evaluate(Command... args) {
		double diff = args[0].evaluate();
		for (int i = 1; i < args.length; i++)
			diff -= args[i].evaluate();
		return diff;
	}

}
