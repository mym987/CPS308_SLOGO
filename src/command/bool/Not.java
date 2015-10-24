package command.bool;

import command.Command;

class Not implements Command {

	@Override
	public double evaluate(Command... args) {
		return (args[0].evaluate()==0)?1:0;
	}

}
