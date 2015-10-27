package command.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.NullVariableException;
import parser.ParseFormatException;

class VariableManager {

	private List<Map<String, Double>> myVariables;
	private int myStartScope;

	protected VariableManager() {
		clear();
	}

	/**
	 * Set the value of the variable in current scope
	 * 
	 * @param name
	 * @param value
	 * @param scope
	 */
	protected void setVar(String name, double value, int scope) {
		myVariables.get(scope).put(name, value);
	}

	/**
	 * Set the value of the variable. First look for the variable in existing
	 * scope. If not found, create the variable in the global scope
	 * 
	 * @param name
	 * @param value
	 */
	protected void setVar(String name, double value) {
		for (int i = myVariables.size() - 1; i >= myStartScope; i--) {
			if (myVariables.get(i).containsKey(name)) {
				myVariables.get(i).put(name, value);
				return;
			}
		}
		myVariables.get(myStartScope).put(name, value);
	}

	protected Variable getVar(String name) throws ParseFormatException {
		checkNameFormat(name);
		return new Variable(name, this);
	}

	protected double getValue(String name) {
		for (int i = myVariables.size() - 1; i >= myStartScope; i--) {
			if (myVariables.get(i).containsKey(name))
				return myVariables.get(i).get(name);
		}
		if (!myVariables.get(0).containsKey(name))
			throw new NullVariableException("\"" + name + "\" is not initialized");
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

	protected int deleteScope() {
		myVariables.remove(myVariables.size() - 1);
		return myVariables.size() - 1;
	}

	protected int deleteScope(int scope) {
		while (myVariables.size() > scope) {
			myVariables.remove(myVariables.size() - 1);
		}
		return myVariables.size() - 1;
	}

	protected void clear() {
		myVariables = new ArrayList<>();
		myVariables.add(new HashMap<>());
		myStartScope = 0;
	}

	protected void checkNameFormat(String name) throws ParseFormatException {
		if (name.charAt(0) != ':')
			throw new ParseFormatException("\"" + name + "\" is not a valid variable name");
	}

	protected Map<String,Double> outputVar() {
		return myVariables.get(0);
	}
}
