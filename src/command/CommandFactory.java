package command;

import java.io.IOException;
import java.util.List;

import command.Command;
import action.Actions;
import parser.ParseFormatException;

/**
 * Must use the same instance to produce commands Otherwise variable scope might
 * not work
 * 
 * Dependency: CommandLoader
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandFactory {

	private CommandLoader myLoader;

	public CommandFactory(Actions actions) throws IOException {
		myLoader = new CommandLoader(actions);
	}
	/**
	 * Case insensitive
	 * @param name
	 * @param args
	 * @return
	 * @throws ParseFormatException
	 */
	public Command getCommand(String name, List<Command> args) throws ParseFormatException {
		return myLoader.getCommand(name, args);
	}

	public void setCaseSensitivite(boolean isSensitive) {
		myLoader.setCaseSensitivite(isSensitive);
	}
	/**
	 * Get a constant command
	 * 
	 * @param value
	 * @return a constant command
	 */
	public Command getConstant(double value) {
		return (args) -> {
			return value;
		};
	}

	/**
	 * Get a variable command
	 * 
	 * @param name
	 *            start with ":"
	 * @return a variable command
	 * @throws ParseFormatException
	 */
	public Command getVarable(String name) throws ParseFormatException {
		return myLoader.getVarable(name);
	}

	/**
	 * Get an empty command with merely a concrete name
	 * 
	 * @param name
	 *            name of the command
	 * @return command
	 */
	public Command getEmptyCommand(String name) {
		return new Command() {
			public double evaluate(Command... args) {
				throw new RuntimeException("This command should never be executed");
			}

			public String toString() {
				return name;
			}

			public String name() {
				return name;
			}
		};
	}

	/**
	 * Get the number of arguments needed for a command
	 * 
	 * @param name
	 *            name of the command
	 * @return number of arguments
	 * @throws ParseFormatException
	 *             when the command name is not found
	 */
	public int getNumArgs(String name) throws ParseFormatException {
		return myLoader.getNumArgs(name);
	}

	/**
	 * Reserve the name space and number of arguments for a function
	 * 
	 * @param name
	 *            name of the function
	 * @param numArgs
	 *            number of arguments of the function
	 */
	public void reserveNameSpace(String name, int numArgs) {
		myLoader.reserveNameSpace(name, numArgs);
	}

	/**
	 * Clear temporary name space
	 */
	public void clearTempNameSpace() {
		myLoader.clearTempNameSpace();
	}
}
