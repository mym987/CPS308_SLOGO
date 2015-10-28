package command.control;

import command.Command;
import parser.ParseFormatException;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class UserCommand implements Command {
	
	private final String myName;
	private final Command myBody;
	private final String[] myVariables;
	private VariableManager myVarManager;
	
	protected UserCommand(VariableManager manager, String name, String[] vars, Command body){
		myName = name;
		myVariables = vars;
		myBody = body;
		myVarManager = manager;
	}
	
	public int getNumArgs(){
		return myVariables.length;
	}

	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		int scope = myVarManager.addScope();
		if(args.length!=myVariables.length)
			throw new RuntimeException("Expect "+myVariables.length+" args, but received "+args.length);
		for(int i=0;i<myVariables.length;i++){
			myVarManager.setVar(myVariables[i], args[i].evaluate(), scope);
		}
		double result = myBody.evaluate();
		myVarManager.deleteScope(scope);
		return result;
	}
	
	@Override
	public String name() {
		return myName;
	}

}
