package command;

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
	 * @return a number
	 */
	public Number evaluate(Command... args);
}
