package command.commands;

import command.Command;

public class Variable implements Command {

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

}
