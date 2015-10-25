package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import action.Actions;
import command.bool.BooleanCommandLoader;
import command.control.ControlCommandLoader;
import command.control.UserCommandManager;
import command.display.DisplayCommandLoader;
import command.math.MathCommandLoader;
import command.turtle.TurtleCommandLoader;
import parser.ParseFormatException;
import util.PropertyLoader;
/**
 * Load all commands from commands.properties
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandLoader implements UserCommandManager{
	
	private final static String TURTLE_COMMAND = "TurtleCommand";
	private final static String TURTLE_QUERY = "TurtleQuery";
	private final static String MATH = "Math";
	private final static String BOOLEAN = "Boolean";
	private final static String CONTROL = "Control";
	private final static String DISPLAY = "Display";
	private final static String USER_DEFINED = "UserDefined";
	
	private Map<String,Command> myCommands;
	private Map<String,Integer> myNumArgs;
	private ControlCommandLoader myControlCommandLoader;
	
	public CommandLoader(Actions actions) throws IOException{
		myCommands = new HashMap<>();
		myNumArgs = new HashMap<>();
		load(getTypes(), actions);
	}
	
	
	public Command getCommand(String name, List<Command> args) throws ParseFormatException{
		if(!myCommands.containsKey(name))
			throw new ParseFormatException("\""+name+"\" does not exist");
		Command[] arguments = args.toArray(new Command[args.size()]);
		return (c)->myCommands.get(name).evaluate(arguments);
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
		return myControlCommandLoader.getVariable(name);
	}
	
	/**
	 * Get an empty command with merely a concrete name
	 * @param name name of the command
	 * @return command
	 */
	public Command getEmptyCommand(String name){
		return new Command(){
			public double evaluate(Command... args) {
				throw new RuntimeException("This command should never be executed");
			}
			public String toString() {
				return name;
			}
			public String name(){
				return name;
			}
		};
	}
	
	/**
	 * Get the number of arguments needed for a command
	 * @param name name of the command
	 * @return number of arguments 
	 * @throws ParseFormatException when the command name is not found
	 */
	public int getNumArgs(String name) throws ParseFormatException{
		if(!myNumArgs.containsKey(name))
			throw new ParseFormatException("\""+name+"\" does not exist");
		return myNumArgs.get(name);
	}

	@Override
	public boolean add(String name, Command cmd, int numArgs) {
		if(myCommands.containsKey(name))
			return false;
		myCommands.put(name, cmd);
		myNumArgs.put(name, numArgs);
		return true;
	}
	
	private Map<String, List<String>> getTypes() throws IOException {
		Map<String, List<String>> types = new HashMap<>();
		Properties prop = (new PropertyLoader()).load("Commands");
		prop.forEach((k, v) -> {
			String[] s = v.toString().split(",");
			int numArgs = Integer.parseInt(s[0]);
			myNumArgs.put(k.toString(), numArgs < 0 ? Integer.MAX_VALUE : numArgs);
			if (!types.containsKey(s[1]))
				types.put(s[1], new ArrayList<>());
			types.get(s[1]).add(k.toString());
		});
		return types;
	}
	
	private void load(Map<String, List<String>> types, Actions actions) {
		myCommands = BooleanCommandLoader.load(types.get(BOOLEAN));
		myCommands.putAll(MathCommandLoader.load(types.get(MATH)));
		myCommands.putAll(DisplayCommandLoader.load(types.get(DISPLAY), actions));
		Map<String, Boolean> map = types.get(TURTLE_COMMAND).stream().collect(Collectors.toMap(s -> s, s -> true));
		map.putAll(types.get(TURTLE_QUERY).stream().collect(Collectors.toMap(s -> s, s -> false)));
		myCommands.putAll(TurtleCommandLoader.load(map, actions));
		myControlCommandLoader = new ControlCommandLoader(this);
		myCommands.putAll(myControlCommandLoader.load(types.get(CONTROL)));
	}
	
}
