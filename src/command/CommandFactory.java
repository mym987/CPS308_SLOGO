package command;

import model.Actions;
/**
 * Must use the same instance to produce commands
 * Otherwise variable scope might not work 
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandFactory {
	
	private Actions myActions;
	
	public CommandFactory(Actions actions){
		myActions = actions;
	}
	
	public Command getCommand(String name,Command...args){
		return null;
	}
}
