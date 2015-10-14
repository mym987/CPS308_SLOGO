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
	
	private Actions myActions;
	private Map<String,Integer> myNumArgsRules;
	
	public CommandFactory(Actions actions) throws IOException{
		myActions = actions;
		myNumArgsRules = getArgumentsRules();
	}
	
	private Map<String,Integer> getArgumentsRules() throws IOException{
		Map<String,Integer> argsMap = new HashMap<>();
		Properties prop = (new PropertyLoader()).load("Commands");
		prop.forEach((k,v)->{
			argsMap.put(k.toString(), Integer.parseInt(v.toString()));
		});
		return argsMap;
	}
	
	public Command getCommand(String name,Command...args){
		//TODO
		return null;
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
