package command;

import parser.ParseFormatException;

/**
 * Do not add more methods to this interface, since Lambda expression only works
 * with interface with no more than 1 method, unless you add "default" modifier
 * to the method.
 * 
 * @author Mike Ma (ym67)
 *
 */
public interface Command {
	/**
	 * Evaluate the command and return its value
	 * 
	 * @param args
	 *            an array of arguments
	 * @return a double
	 * @throws ParseFormatException
	 */
	public double evaluate(Command... args) throws ParseFormatException;
	
	default public String name(){
		return getClass().getName().substring(getClass().getPackage().getName().length()+1);
	}
}
