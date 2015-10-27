
package parser;

import java.io.File;
import java.io.IOException;

import command.CommandList;

public interface Parser {
	
	public CommandList parse(String input,String language) throws ParseFormatException;
	
	public void save(File file) throws IOException;
	
	public void read(File file) throws IOException;
}
