package command.math;

import command.Command;

class Quotient implements Command {

	@Override
	public double evaluate(Command... args) {
		double q = args[0].evaluate();
		for (int i = 1; i < args.length; i++)
			q /= args[i].evaluate();
		return q;
	}

}
