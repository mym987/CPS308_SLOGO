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
	
	public SimpleParser(Actions actions) throws ParseFormatException {
		try {
			myFactory = new CommandFactory(actions);
			myLoader = new LanguageLoader();
			myUserFunctions = new HashSet<>();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
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
		while (myTokenizer.hasNext()) {
			String token = myTokenizer.next();
			if (token.matches(mySyntaxRules.get("Command"))) {
				token = commandDelocalize(token);
				myTokenStack.push(new Token(token, myFactory.getNumArgs(token)));
			} else if (token.matches(mySyntaxRules.get("Constant"))) {
				parseConstant(token);
			} else if (token.matches(mySyntaxRules.get("Variable"))) {
				parseVariable(token);
			} else if (token.matches(mySyntaxRules.get("ListStart"))){
				myTokenStack.push(new Token("List",Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("GroupStart"))){
				myTokenStack.push(new Token("Group",Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("ListEnd"))){
				closeCluster("List");
			} else if (token.matches(mySyntaxRules.get("GroupEnd"))){
				closeCluster("Group");
			}
			while(myTokenStack.peek().satisfied()){
				popStack();
			}
		}
		myTokenizer.close();
		return myCommandList;
	}
	
	private String commandDelocalize(String token){
		//TODO
		return null;
	}
	
	private void popStack(){
		//TODO
	}
	
	private void closeCluster(String listName) throws ParseFormatException{
		String str = myTokenStack.peek().myName;
		if(!str.equals(listName))
			throw new ParseFormatException(str + " missing arguments or "+listName+" mismatch!");
		popStack();
	}
	
	private void parseVariable(String token) throws ParseFormatException{
		//TODO
	}
	
	private void parseConstant(String token) throws ParseFormatException{
		try{
			double d = Double.parseDouble(token);
			Command c = myFactory.getConstant(d);
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
			mySyntaxRules = getSyntaxRules();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
	}
	
	private Map<String,String> getLanguageRules(String language) throws IOException{
		return myLoader.getLocalizedSyntax(language);
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
		p.printMap(p.mySyntaxRules);
	}

}
