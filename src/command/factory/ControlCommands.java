package command.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import command.Command;
import command.CommandList;
import parser.ParseFormatException;

class ControlCommands {
	
	private VariableManager myVarManager;
	private Map<String,UserCommand> myUserDefined;
	private Map<String,Integer> myTempNameSpace;
	private Set<String> myReservedKeys;

	public ControlCommands(Set<String> reservedKeys) {
		myVarManager = new VariableManager();
		myUserDefined = new HashMap<>();
		myReservedKeys = new HashSet<>(reservedKeys);
		myTempNameSpace = new HashMap<>();
	}

	public Command get(String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "MakeVariable":
			return myVarManager.makeVar(args);
		case "Repeat":
			return repeat(args);
		case "DoTimes":
			return doTimes(args);
		case "For":
			return forCommand(args);
		case "If":
			return ifCommand(args);
		case "IfElse":
			return ifElse(args);
		case "MakeUserInstruction":
			return makeUserDefined(args);
		default:
			throw new ParseFormatException(name + " does not exist");
		}
	}
	
	/**
	 * Get the number of arguments for user defined commands
	 * @param name
	 * @return number of arguments
	 * @throws ParseFormatException when the command is not found
	 */
	protected int getNumArgs(String name) throws ParseFormatException{
		if(myUserDefined.containsKey(name))
			return myUserDefined.get(name).getNumArgs();
		else if(myTempNameSpace.containsKey(name))
			return myTempNameSpace.get(name);
		else
			throw new ParseFormatException("\""+name +"\""+ " does not exist");
	}
	
	protected void clearTempNameSpace(){
		myTempNameSpace.clear();
	}
	
	protected void reserveNameSpace(String name, int numArgs){
		myTempNameSpace.put(name, numArgs);
	}
	
	protected Variable getVariable(String name) throws ParseFormatException{
		return myVarManager.getVar(name);
	}
	
	protected Command getUserDefined(String name, List<Command> args) throws ParseFormatException{
		getNumArgs(name); //test if the command exists
		Command[] arguments = args.toArray(new Command[args.size()]);
		return (cmd)->{
			return myUserDefined.get(name).evaluate(arguments);
		};
	}
	
	private Command makeUserDefined(List<Command> args) throws ParseFormatException{
		if(args.size()!=3)
			throw new ParseFormatException("Args # mismatch");
		String name = args.get(0).toString();
		Command body = args.get(2);
		String[] vars = args.get(1).toString().split("\\s+");
		for(String s:vars){
			if(s.charAt(0)!=':')
				throw new ParseFormatException("\""+s+"\" is not a variable");
		}
		return (cmd)->{
			if(!myReservedKeys.contains(name)){
				UserCommand usr = new UserCommand(myVarManager, name,vars,body);
				myUserDefined.put(usr.toString(), usr);
				myReservedKeys.add(usr.toString());
				return 1;
			}else{
				return 0;
			}
		};
		
	}
	
	private Command repeat(List<Command> args) {
		return (cmd) -> {
			double times = args.get(0).evaluate(), result = 0;
			Command body = args.get(1);
			for (int i = 0; i < times; i++) {
				result = body.evaluate();
			}
			return result;
		};

	}

	private Command doTimes(List<Command> args) throws ParseFormatException {

		String name;
		Command loopBody;
		CommandList condition;
		try {
			condition = (CommandList) args.get(0);
			loopBody = args.get(1);
			name = condition.get(0).toString();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		return (cmd) -> {
			int scope = myVarManager.addScope();
			double result = 0;
			int max = (int) condition.get(1).evaluate();
			for (int i = 1; i <= max; i++) {
				myVarManager.setVar(name, i, scope);
				result = loopBody.evaluate();
			}
			myVarManager.deleteScope(scope);
			return result;
		};

	}

	private Command forCommand(List<Command> args) throws ParseFormatException {
		String var;
		Command loopBody;
		CommandList condition;
		try {
			condition = (CommandList) args.get(0);
			loopBody = args.get(1);
			var = condition.get(0).toString();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		return (cmd)->{
			int scope = myVarManager.addScope();
			double start = condition.get(1).evaluate();
			double end = condition.get(2).evaluate();
			double step = condition.get(3).evaluate();
			double result = 0;
			for(double i = start; i<=end;i+=step){
				myVarManager.setVar(var, i, scope);
				result = loopBody.evaluate();
			}
			myVarManager.deleteScope(scope);
			return result;
		};
	}

	private Command ifCommand(List<Command> args) {
		return (cmd) -> {
			if (args.get(0).evaluate() != 0)
				return args.get(1).evaluate();
			else
				return 0;
		};
	}

	private Command ifElse(List<Command> args) {
		return (cmd) -> {
			if (args.get(0).evaluate() != 0)
				return args.get(1).evaluate();
			else
				return args.get(2).evaluate();
		};
	}

}
