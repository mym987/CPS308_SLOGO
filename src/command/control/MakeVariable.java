package command.control;

import command.Command;
import parser.ParseFormatException;
/**
 * Make variable command 
 * 
 * @author Mike Ma (ym67)
 *
 */
public class MakeVariable implements Command {

	private VariableManager myVarMgr;

	public MakeVariable(VariableManager varMgr, UserCommandManager usrMgr) {
		myVarMgr = varMgr;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		if (args.length < 2 || !(args[0] instanceof Variable))
			throw new ParseFormatException("Format Error for Making Variable");
		double value = args[1].evaluate();
		myVarMgr.setVar(args[0].name(), value);
		return value;
	}

}
