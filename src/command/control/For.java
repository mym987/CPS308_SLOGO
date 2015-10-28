package command.control;

import command.Command;
import command.CommandList;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class For implements Command {

	private VariableManager myVarMgr;

	public For(VariableManager varMgr, UserCommandManager usrMgr) {
		myVarMgr = varMgr;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		String var;
		Command loopBody;
		CommandList condition;
		try {
			condition = (CommandList) args[0];
			loopBody = args[1];
			var = condition.get(0).name();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		int scope = myVarMgr.addScope();
		double start = condition.get(1).evaluate();
		double end = condition.get(2).evaluate();
		double step = condition.get(3).evaluate();
		double result = 0;
		for (double i = start; i <= end; i += step) {
			myVarMgr.setVar(var, i, scope);
			result = loopBody.evaluate();
		}
		myVarMgr.deleteScope(scope);
		return result;
	}

}
