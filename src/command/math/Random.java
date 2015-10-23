package command.math;

import command.Command;

public class Random implements Command {
	
	java.util.Random myRand = new java.util.Random();

	@Override
	public double evaluate(Command... args) {
		return myRand.nextInt((int) Math.ceil(args[0].evaluate()));
	}

}
