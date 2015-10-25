package command.control;

import command.Command;
import parser.ParseFormatException;

public class IfElse implements Command {

	public IfElse(VariableManager varMgr, UserCommandManager usrMgr) {
		//do nothing
	}
	
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		if (args[0].evaluate() != 0)
			return args[1].evaluate();
		else
			return args[2].evaluate();
	}

}
