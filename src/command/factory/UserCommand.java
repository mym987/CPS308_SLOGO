package command.factory;

import command.Command;

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
	public double evaluate(Command... args) {
		int scope = myVarManager.newScope();
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
	public String toString() {
		return myName;
	}

}
