package command.bool;

import command.Command;

class LessThan implements Command {

	@Override
	public double evaluate(Command... args) {
		return args[0].evaluate() < args[1].evaluate()?1:0;
	}

}
