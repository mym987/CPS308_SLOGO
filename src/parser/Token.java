package parser;

import java.util.LinkedList;
import java.util.List;

import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 */
class Token {
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
