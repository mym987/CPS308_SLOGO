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
class CommandLoader implements UserCommandManager {

	private final static String TURTLE_COMMAND = "TurtleCommand";
	private final static String TURTLE_QUERY = "TurtleQuery";
	private final static String MATH = "Math";
	private final static String MULTIPLE = "Multiple";
	private final static String BOOLEAN = "Boolean";
	private final static String CONTROL = "Control";
	private final static String DISPLAY = "Display";
	private final static String LIST = "List";

	private Map<String, Command> myCommands;
	private Map<String, Integer> myNumArgs;
	private Map<String, Integer> myTempNameSpace;
	private ControlCommandLoader myControlCommandLoader;
	private boolean isCaseSensitive = true;

	CommandLoader(Actions actions) throws IOException {
		myCommands = new HashMap<>();
		myNumArgs = new HashMap<>();
		myTempNameSpace = new HashMap<>();
		load(getTypes(), actions);
	}

	/**
	 * Default is true
	 * 
	 * @param isSensitive
	 */
	public void setCaseSensitivite(boolean isSensitive) {
		if (isCaseSensitive == isSensitive)
			return;
		isCaseSensitive = isSensitive;
		myNumArgs = myNumArgs.keySet().stream().collect(
				Collectors.toMap(k -> isSensitive ? myCommands.get(k).name() : k.toLowerCase(), k -> myNumArgs.get(k)));
		myCommands = myCommands.keySet().stream().collect(Collectors
				.toMap(k -> isSensitive ? myCommands.get(k).name() : k.toLowerCase(), k -> myCommands.get(k)));
		//myCommands.forEach((k, v) -> System.out.println(k + " " + myNumArgs.get(k) + " " + v.name()));
	}

	public Command getCommand(String cmd, List<Command> args) throws ParseFormatException {
		if (cmd.equals(LIST))
			return new CommandList(args);
		final String name = isCaseSensitive?cmd:cmd.toLowerCase();
		if (myCommands.containsKey(name) || myTempNameSpace.containsKey(name))
			return (c) -> myCommands.get(name).evaluate(args.toArray(new Command[args.size()]));
		throw new ParseFormatException("\"" + name + "\" does not exist");
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
		return myControlCommandLoader.getVariable(name);
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
		if(!isCaseSensitive)
			name = name.toLowerCase();
		if (myTempNameSpace.containsKey(name))
			return myTempNameSpace.get(name);
		if (myNumArgs.containsKey(name))
			return myNumArgs.get(name);
		throw new ParseFormatException("\"" + name + "\" does not exist");
	}

	@Override
	public boolean add(String name, Command cmd, int numArgs) {
		if (myCommands.containsKey(name))
			return false;
		myCommands.put(name, cmd);
		myNumArgs.put(name, numArgs);
		return true;
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
		myTempNameSpace.put(name, numArgs);
	}

	/**
	 * Clear temporary name space
	 */
	public void clearTempNameSpace() {
		myTempNameSpace.clear();
	}
	
	public String outputVar(){
		return myControlCommandLoader.outputVar();
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
		map.putAll(types.get(MULTIPLE).stream().collect(Collectors.toMap(s -> s, s -> false)));
		map.putAll(types.get(TURTLE_QUERY).stream().collect(Collectors.toMap(s -> s, s -> false)));
		myCommands.putAll(TurtleCommandLoader.load(map, actions));
		myControlCommandLoader = new ControlCommandLoader(this);
		myCommands.putAll(myControlCommandLoader.load(types.get(CONTROL)));
	}

}
