package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.ParseFormatException;

class VariableManager {

	private List<Map<String, Double>> myVariables;
	private int myStartScope;

	protected VariableManager() {
		clear();
	}

	protected Command makeVar(List<Command> args) throws ParseFormatException {
		if (args.size() != 2 || !(args.get(0) instanceof Variable))
			throw new ParseFormatException("Format Error for Making Variable");
		return (cmd) -> {
			double value = args.get(1).evaluate();
			setVar(args.get(0).toString(),value);
			return value;
		};
	}
	
	protected void setVar(String name, double value, int scope) {
		checkNameFormat(name);
		myVariables.get(scope).put(name, value);
	}
	
	protected void setVar(String name, double value){
		checkNameFormat(name);
		for (int i = myVariables.size() - 1; i >= myStartScope; i--) {
			if (myVariables.get(i).containsKey(name)){
				myVariables.get(i).put(name, value);
				return;
			}
		}
		myVariables.get(0).put(name, value);
	}

	protected Variable getVar(String name){
		checkNameFormat(name);
		return new Variable(name, this);
	}
	
	protected double getValue(String name){
		checkNameFormat(name);
		for (int i = myVariables.size() - 1; i >= myStartScope; i--) {
			if (myVariables.get(i).containsKey(name))
				return myVariables.get(i).get(name);
		}
		if(!myVariables.get(0).containsKey(name))
			throw new NullVariableException("\""+name+ "\" is not initialized");
		return myVariables.get(0).get(name);
	}

	protected int newScope() {
		myVariables.add(new HashMap<>());
		myStartScope = myVariables.size() - 1;
		return myVariables.size() - 1;
	}
	
	protected int addScope() {
		myVariables.add(new HashMap<>());
		return myVariables.size() - 1;
	}
	
	protected int deleteScope(){
		myVariables.remove(myVariables.size()-1);
		return myVariables.size()-1;
	}
	
	protected int deleteScope(int scope){
		System.out.println("Deleting: "+scope);
		while(myVariables.size()>scope){
			myVariables.remove(myVariables.size()-1);
		}
		return myVariables.size()-1;
	}
	
	protected void clear(){
		myVariables = new ArrayList<>();
		myVariables.add(new HashMap<>());
		myStartScope = 0;
	}
	
	protected void checkNameFormat(String name) throws RuntimeException{
		if(!name.startsWith(":"))
			throw new RuntimeException("\""+name+"\" is not a valid variable name");
	}
}
