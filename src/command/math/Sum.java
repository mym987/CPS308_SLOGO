package command.math;

import command.Command;

class Sum implements Command {

	@Override
	public double evaluate(Command... args) {
		double sum = 0;
		for (Command cmd : args)
			sum += cmd.evaluate();
		return sum;
	}

}
