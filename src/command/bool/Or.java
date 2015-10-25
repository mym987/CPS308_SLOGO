package command.bool;

import command.Command;
import parser.ParseFormatException;

class Or implements Command {

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		for(Command cmd:args){
			if(cmd.evaluate()!=0)
				return 1;
		}
		return 0;
	}

}
