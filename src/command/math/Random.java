package command.math;

import command.Command;
import parser.ParseFormatException;

class Random implements Command {
	
	java.util.Random myRand = new java.util.Random();

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		return myRand.nextInt((int) Math.ceil(args[0].evaluate()));
	}

}
