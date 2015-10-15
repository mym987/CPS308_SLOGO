package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import model.Actions;
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
	
	private final static String TURTLE_COMMAND = "TurtleCommand";
	private final static String TURTLE_QUERY = "TurtleQuery";
	private final static String MATH = "Math";
	private final static String BOOLEAN = "Boolean";
	private final static String CONTROL = "Control";
	private final static String DISPLAY = "Display";
	private final static String MULTIPLE = "Multiple";
	private final static String USER_DEFINED = "UserDefined";
	
	
	private Actions myActions;
	private Map<String,Integer> myNumArgsRules;
	private Map<String,String> myCommandCatalog;
	private Map<String,Command> myUserDefined;
	
	public CommandFactory(Actions actions) throws IOException{
		myActions = actions;
		myUserDefined = new HashMap<>();
		myCommandCatalog = new HashMap<>();
		myNumArgsRules = new HashMap<>();
		Properties prop = (new PropertyLoader()).load("Commands");
		prop.forEach((k,v)->{
			String[] s = v.toString().split(",");
			myNumArgsRules.put(k.toString(), Integer.parseInt(s[0]));
			myCommandCatalog.put(k.toString(), s[1]);
		});
	}
	
	public Command getCommand(String name,Command...args) throws ParseFormatException{
		switch (myCommandCatalog.get(name)) {
		case TURTLE_COMMAND:
			//TODO
			return (c)->{return args[0].evaluate();};
		case TURTLE_QUERY:
			//TODO
			return (c)->{return 0;};
		case MATH:
			return MathCommands.get(name, args);
		case BOOLEAN:
			return BooleanCommands.get(name, args);
		case CONTROL:
			//TODO
			return (c)->{return 0;};
		case DISPLAY:
			//TODO
			return (c)->{return 0;};
		case MULTIPLE:
			//TODO
			return (c)->{return 0;};
		case USER_DEFINED:
			//TODO
			return (c)->{return 0;};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
	
	public Command getConstant(double value){
		return (args)->{return value;};
	}
	
	public Command getVarable(String name, double value){
		//TODO
		return null;
	}
	
	public Command getUserFunction(String name){
		//TODO
		return null;
	}
	
	public int getNumArgs(String name) throws ParseFormatException{
		if(myNumArgsRules==null)
			throw new ParseFormatException("Command factory has not been initialized!");
		if(!myNumArgsRules.containsKey(name))
			throw new ParseFormatException(name+" does not exist!");
		return myNumArgsRules.get(name);
	}
}
