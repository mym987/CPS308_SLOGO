package command.bool;

import command.Command;

public class And implements Command {

	@Override
	public double evaluate(Command... args) {
		for(Command cmd:args){
			if(cmd.evaluate()==0)
				return 0;
		}
		return 1;
	}

}
