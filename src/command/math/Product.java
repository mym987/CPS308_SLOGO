package command.math;

import command.Command;

public class Product implements Command {

	@Override
	public double evaluate(Command... args) {
		double product = 1;
		for (Command cmd : args)
			product *= cmd.evaluate();
		return product;
	}

}
