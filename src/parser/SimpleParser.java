package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

import command.Command;
import command.CommandFactory;
import command.CommandList;
import model.Actions;
import util.LanguageLoader;

public class SimpleParser implements Parser {
	
	private final CommandFactory myFactory;
	private final LanguageLoader myLoader;
	
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
	public CommandList parse(String input, String language) throws ParseFormatException{
		init(input,language);
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
			popStack();
		}
		myTokenizer.close();
		//Check if there're any remaining commands in the stack, and throw errors accordingly
		if(!myTokenStack.isEmpty()){
			if(myTokenStack.peek().myName.matches("List|Group"))
				throw new ParseFormatException("List/Group did not close properly!");
			else
				throw new ParseFormatException("Insufficient Arguments for "+ myTokenStack.peek().myName +"!");
		}	
		return myCommandList;
	}
	
	private String commandDelocalize(String token){
		for(String s:myLanguageRules.keySet()){
			if(token.matches(s)){
				return myLanguageRules.get(s);
			}
		}
		return token;
	}
	
	private void popStack() throws ParseFormatException{
		while(!myTokenStack.isEmpty() && myTokenStack.peek().satisfied()){
			Token token = myTokenStack.pop();
			Command c = myFactory.getCommand(token.myName,token.myCommands);
			if(myTokenStack.isEmpty()){
				myCommandList.add(c);
			}else{
				myTokenStack.peek().addCommand(c);
			}
		}
	}
	
	private void closeCluster(String listName) throws ParseFormatException{
		if(myTokenStack.isEmpty())
			throw new ParseFormatException(listName+" mismatch!");
		String str = myTokenStack.peek().myName;
		if(!str.equals(listName))
			throw new ParseFormatException(str + " missing arguments!");
		myTokenStack.peek().myNumArgs = myTokenStack.peek().myCommands.size();
		
	}
	
	private void parseVariable(String token) throws ParseFormatException{
		Command c = myFactory.getVarable(token);
		if(myTokenStack.isEmpty())
			myCommandList.add(c);
			//throw new ParseFormatException("Stand-alone variable: "+token.substring(1));
		else myTokenStack.peek().addCommand(c);
	}
	
	private void parseConstant(String token) throws ParseFormatException{
		try{
			double d = Double.parseDouble(token);
			Command c = myFactory.getConstant(d);
			if(myTokenStack.isEmpty())
				throw new ParseFormatException("Stand-alone constant: "+d);
			myTokenStack.peek().addCommand(c);
		}catch(Exception e){ //There may be more exceptions than ParseFormatException
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
	
	public static void main(String[] args) throws ParseFormatException{
		SimpleParser p = new SimpleParser(null);
		//Command c = p.parse("[()][][difference product sum [[](30 20)] 40 10 1000]","English");
		//Command c = p.parse("make :a 10.5 sum ifelse equal? :a 10.5 50 15 20.5","English");
		Command c = p.parse("make :a 0 for [:i 40 100 10] "
				+ "[make :a sum :a :i]"
				+ "a","English");
		System.out.println(c.evaluate());
		//p.printMap(p.myLanguageRules);
		//p.printMap(p.mySyntaxRules);
		//p.commandDelocalize("zs");
	}

}
