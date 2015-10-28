package command.control;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
public class MakeUserInstruction implements Command {

	private VariableManager myVarMgr;
	private UserCommandManager myUsrMgr;

	public MakeUserInstruction(VariableManager varMgr, UserCommandManager usrMgr) {
		myVarMgr = varMgr;
		myUsrMgr = usrMgr;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		if (args.length != 3)
			throw new ParseFormatException("Args # mismatch");
		String name = args[0].name();
		Command body = args[2];
		String[] vars = args[1].toString().split("\\s+");
		for (String s : vars) {
			if (s.charAt(0) != ':')
				throw new ParseFormatException("\"" + s + "\" is not a variable");
		}
		UserCommand usr = new UserCommand(myVarMgr, name, vars, body);
		return myUsrMgr.add(name, usr, usr.getNumArgs()) ? 1 : 0;
	}

}
