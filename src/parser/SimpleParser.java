package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

import command.Command;
import command.CommandFactory;
import command.CommandList;
import model.Actions;
import util.LanguageLoader;

public class SimpleParser implements Parser {
	
	private CommandFactory myFactory;
	private LanguageLoader myLoader;
	private Set<String> myUserFunctions;
	
	private Map<String,String> myLanguageRules;
	private Map<String,Integer> myNumArgsRules;
	private Map<String,String> mySyntaxRules;
	
	private Stack<Token> myTokenStack;
	private CommandList myCommandList;
	private Tokenizer myTokenizer;
	
	private class Token{
		int myNumArgs;
		String myName;
		List<Command> myCommands;
		
		Token(String name,int numArgs){
			myName = name;
			myNumArgs = numArgs;
			myCommands = new LinkedList<>();
		}
		
		void addCommand(Command c) throws ParseFormatException{
			myCommands.add(c);
			if(myCommands.size()>myNumArgs)
				throw new ParseFormatException(myName+" has more args than needed!");
		}
		
		boolean satisfied(){
			return myCommands.size()==myNumArgs;
		}
	}
	
	public SimpleParser(Actions actions){
		myFactory = new CommandFactory(actions);
		myLoader = new LanguageLoader();
		myUserFunctions = new HashSet<>();
		new HashMap<>();
	}
	
	private <K, V> void printMap(Map<K,V> map){
		System.out.println("---------------Printing map------------------");
		map.forEach((k,v)->{
			System.out.println(k+"\t"+v);
		});
	}

	@Override
	public CommandList parse(String input, String language) throws ParseFormatException, NameConflictException{
		init(input,language);
		/**
		 * Possible Tokens:
		 * Constant = -?[0-9]+\\.?[0-9]*
		 * Variable = :[a-zA-Z]+
		 * Command = [a-zA-Z_]+(\\?)?|[*+-/%~]
		 * ListStart = \\[
		 * ListEnd = \\]
		 * GroupStart = \\(
		 * GroupEnd = \\)
		 */
		while(myTokenizer.hasNext()){
			String token = myTokenizer.next();
			if(token.matches(mySyntaxRules.get("Command"))){
				pushCommand(token);
			}else if(token.matches(mySyntaxRules.get("Constant"))){
				parseConstant(token);
			}else if(token.matches(mySyntaxRules.get("Command"))) {
				
			}
				
		}
		myTokenizer.close();
		return myCommandList;
	}
	
	/**
	 * Push the command to the top of the stack
	 * @param token
	 */
	private void pushCommand(String token){
		
	}
	
	private void parseConstant(String token) throws ParseFormatException{
		try{
			double d = Double.parseDouble(token);
			Command c = (args) -> {return d;};
			if(myTokenStack.isEmpty())
				throw new ParseFormatException("Standing alone constant: "+d);
			myTokenStack.peek().addCommand(c);
		}catch(Exception e){
			throw new ParseFormatException(e.getMessage());
		}
	}
	
	private void init(String input, String language) throws ParseFormatException{
		loadRules(language);
		myTokenizer = new SyntaxTokenizer(input);
		myCommandList = new CommandList();
		myTokenStack = new Stack<>();
	}
	
	private void loadRules(String language) throws ParseFormatException{
		try {
			myLanguageRules = getLanguageRules(language);
			myNumArgsRules = getArgumentsRules();
			mySyntaxRules = getSyntaxRules();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
	}
	
	private Map<String,String> getLanguageRules(String language) throws IOException{
		return myLoader.getLocalizedSyntax(language);
	}
	
	private Map<String,Integer> getArgumentsRules() throws IOException{
		Map<String,Integer> argsMap = new HashMap<>();
		Properties prop = myLoader.load("Commands");
		prop.forEach((k,v)->{
			argsMap.put(k.toString(), Integer.parseInt(v.toString()));
		});
		return argsMap;
	}
	
	private Map<String,String> getSyntaxRules() throws IOException{
		Map<String,String> syntaxMap = new HashMap<>();
		Properties prop = myLoader.load("Syntax");
		prop.forEach((k,v)->{
			syntaxMap.put(k.toString(), v.toString());
		});
		return syntaxMap;
	}
	
	public static void main(String[] args) throws ParseFormatException, NameConflictException{
		SimpleParser p = new SimpleParser(null);
		p.parse("","Chinese");
		p.printMap(p.myLanguageRules);
		p.printMap(p.myNumArgsRules);
		p.printMap(p.mySyntaxRules);
	}

}
