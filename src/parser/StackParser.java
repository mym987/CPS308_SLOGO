package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;

import command.Command;
import command.CommandList;
import command.factory.CommandFactory;
import model.Actions;
import util.LanguageLoader;

public class StackParser implements Parser {
	
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
	
	public StackParser(Actions actions) throws ParseFormatException {
		try {
			myFactory = new CommandFactory(actions);
			myLoader = new LanguageLoader();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
	}

	@Override
	public CommandList parse(String input, String language) throws ParseFormatException{
		init(input,language);
		while (myTokenizer.hasNext()) {
			String token = myTokenizer.next();
			if (token.matches(mySyntaxRules.get("Command"))) {
				token = commandDelocalize(token);
				if(!myTokenStack.empty() && myTokenStack.peek().myName.equals("(")){
					myTokenStack.peek().myName+=token;
				}else{
					myTokenStack.push(new Token(token, myFactory.getNumArgs(token)));
				}
				if(token.equals("MakeUserInstruction"))
					pushUserCommandName();
			} else if (token.matches(mySyntaxRules.get("Constant"))) {
				parseConstant(token);
			} else if (token.matches(mySyntaxRules.get("Variable"))) {
				parseVariable(token);
			} else if (token.matches(mySyntaxRules.get("ListStart"))){
				myTokenStack.push(new Token("List",Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("GroupStart"))){
				myTokenStack.push(new Token("(",Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("ListEnd"))){
				closeList();
			} else if (token.matches(mySyntaxRules.get("GroupEnd"))){
				closeGroup();
			}
			popStack();
		}
		myTokenizer.close();
		//Check if there're any remaining commands in the stack, and throw errors accordingly
		if(!myTokenStack.isEmpty()){
			if(myTokenStack.peek().myName.matches("List|Group"))
				throw new ParseFormatException("List/Group did not close properly");
			else
				throw new ParseFormatException("Insufficient arguments for \""+ myTokenStack.peek().myName +"\"");
		}	
		return myCommandList;
	}
	
	private void pushUserCommandName() throws ParseFormatException{
		if(!myTokenizer.hasNext())
			throw new ParseFormatException("Insufficient args for making user command");
		final String name = myTokenizer.next();
		myTokenStack.peek().addCommand(new Command() {
			public double evaluate(Command... args) {
				throw new RuntimeException("This command should never be executed");
			}
			public String toString() {
				return name;
			}
		});	
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
			//System.out.println(token.myName);
			Command c = myFactory.getCommand(token.myName,token.myCommands);
			if(myTokenStack.isEmpty()){
				myCommandList.add(c);
			}else{
				myTokenStack.peek().addCommand(c);
			}
		}
	}
	
	private void closeList() throws ParseFormatException{
		if(myTokenStack.isEmpty())
			throw new ParseFormatException("List mismatch");
		Token t = myTokenStack.peek();
		if(!t.myName.equals("List"))
			throw new ParseFormatException(t.myName + " missing arguments");
		t.myNumArgs = t.myCommands.size();
	}
	
	private void closeGroup() throws ParseFormatException{
		if(myTokenStack.isEmpty())
			throw new ParseFormatException("Group mismatch");
		Token t = myTokenStack.peek();
		if(!t.myName.startsWith("("))
			throw new ParseFormatException("Group mismatch");
		t.myName = t.myName.substring(1);
		t.myNumArgs = t.myCommands.size();
		if(t.myNumArgs<myFactory.getNumArgs(t.myName))
			throw new ParseFormatException("Insufficient args for \""+t.myName+"\"");
	}
	
	private void parseVariable(String token) throws ParseFormatException{
		Command c = myFactory.getVarable(token);
		if(myTokenStack.isEmpty())
			myCommandList.add(c);
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
	
	public static void main(String[] args) throws ParseFormatException, FileNotFoundException{
		StackParser p = new StackParser(null);
		Scanner s = new Scanner(new FileInputStream("test.in"));
		Command c = p.parse(s.useDelimiter("\\Z").next(), "English");
		System.out.println(c.evaluate());
		s.close();
	}
}
