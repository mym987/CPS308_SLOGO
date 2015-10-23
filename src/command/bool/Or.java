package command.bool;

import command.Command;

public class Or implements Command {

	@Override
	public double evaluate(Command... args) {
		for(Command cmd:args){
			if(cmd.evaluate()!=0)
				return 1;
		}
		return 0;
	}

}
