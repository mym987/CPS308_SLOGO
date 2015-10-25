package command.control;

import command.Command;
import command.CommandList;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
public class DoTimes implements Command {

	private VariableManager myVarMgr;

	public DoTimes(VariableManager varMgr, UserCommandManager usrMgr) {
		myVarMgr = varMgr;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		String name;
		Command loopBody;
		CommandList condition;
		try {
			condition = (CommandList) args[0];
			loopBody = args[1];
			name = condition.get(0).name();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		int scope = myVarMgr.addScope();
		double result = 0;
		int max = (int) condition.get(1).evaluate();
		for (int i = 1; i <= max; i++) {
			myVarMgr.setVar(name, i, scope);
			result = loopBody.evaluate();
		}
		myVarMgr.deleteScope(scope);
		return result;
	}

}
