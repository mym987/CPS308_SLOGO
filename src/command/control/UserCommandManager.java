package command.control;

import command.Command;

/**
 * An interface which allows client to add user defined commands to the factory
 * 
 * @author Mike Ma (ym67)
 *
 */
public interface UserCommandManager {
	
	public boolean add(String name, Command cmd, int numArgs);
	
}
