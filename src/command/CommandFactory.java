package command;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import command.Command;
import command.CommandList;
import action.Actions;
import parser.ParseFormatException;
import util.PropertyLoader;
/**
 * Must use the same instance to produce commands
 * Otherwise variable scope might not work 
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandFactory {
	
	private CommandLoader myLoader;
	
	public CommandFactory(Actions actions) throws IOException{
		myLoader = new CommandLoader(actions);
	}
	
	public Command getCommand(String name,List<Command> args) throws ParseFormatException{	
		switch (myCommandCatalog.get(name)) {
		case TURTLE_COMMAND:
			return TurtleCommands.getCommand(myActions, name, args);
		case TURTLE_QUERY:
			return TurtleCommands.getQuery(myActions, name, args);
		case MATH:
			return MathCommands.get(name, args);
		case BOOLEAN:
			return BooleanCommands.get(name, args);
		case CONTROL:
			return myControlCommands.get(name, args);
		case DISPLAY:
			return DisplayCommands.get(myActions, name, args);
		case MULTIPLE:
			return MultipleCommands.get(myActions, name, args);
		case CLUSTER:
			return new CommandList(args);
		case USER_DEFINED:
			return myControlCommands.getUserDefined(name, args);
		default:
			throw new ParseFormatException("\""+name+"\" does not exist");
		}
	}
	
	/**
	 * Get a constant command
	 * @param value
	 * @return a constant command
	 */
	public Command getConstant(double value){
		return (args)->{return value;};
	}
	
	/**
	 * Get a variable command
	 * @param name start with ":"
	 * @return a variable command
	 * @throws ParseFormatException 
	 */
	public Command getVarable(String name) throws ParseFormatException{
		return myControlCommands.getVariable(name);
	}
	
	public Command getEmptyCommand(String name){
		return new Command(){
			public double evaluate(Command... args) {
				throw new RuntimeException("This command should never be executed");
			}
			public String toString() {
				return name;
			}
		};
	}
	
	/**
	 * Reserve the name space and number of arguments for a function
	 * @param name name of the function
	 * @param numArgs number of arguments of the function
	 */
	public void reserveNameSpace(String name,int numArgs){
		myCommandCatalog.put(name, USER_DEFINED);
		myControlCommands.reserveNameSpace(name, numArgs);
	}
	
	/**
	 * Clear temporary name space
	 */
	public void clearTempNameSpace(){
		myControlCommands.clearTempNameSpace();
	}
	
	/**
	 * Get the number of arguments needed for a command
	 * @param name name of the command
	 * @return number of arguments 
	 * @throws ParseFormatException when the command name is not found
	 */
	public int getNumArgs(String name) throws ParseFormatException{
		if(myNumArgsRules.containsKey(name))
			return myNumArgsRules.get(name);
		return myControlCommands.getNumArgs(name);
	}
}
