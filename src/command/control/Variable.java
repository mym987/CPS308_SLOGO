package command.control;

import command.Command;
/**
 * A class representing variable command
 * 
 * @author Mike Ma (ym67)
 *
 */
class Variable implements Command {

	private final String myName;
	private VariableManager myManager;

	public Variable(String name, VariableManager manager) {
		myName = name;
		myManager = manager;
	}

	@Override
	public double evaluate(Command... args) {
		return myManager.getValue(myName);
	}

	@Override
	public String toString() {
		return myName;
	}
	
	@Override
	public String name() {
		return myName;
	}

}
