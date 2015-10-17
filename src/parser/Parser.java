
package parser;

import command.CommandList;

public interface Parser {
	
	public CommandList parse(String input,String language) throws ParseFormatException;
	
}
