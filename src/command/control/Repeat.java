package command.control;

import command.Command;
import parser.ParseFormatException;

public class Repeat implements Command {

	public Repeat(VariableManager varMgr, UserCommandManager usrMgr) {
		//do nothing
	}
	
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double times = args[0].evaluate(), result = 0;
		Command body = args[1];
		for (int i = 0; i < times; i++) {
			result = body.evaluate();
		}
		return result;
	}

}
