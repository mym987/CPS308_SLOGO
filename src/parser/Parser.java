package parser;

import java.util.List;

import command.Command;

public interface Parser {
	
	public List<Command> parse(String input,String language) throws ParseFormatException, NameConflictException;
	
}
