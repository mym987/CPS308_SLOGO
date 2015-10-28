package parser;
/**
 * Parser interface. Front end call this interface to parse commands
 * 
 * @author Mike Ma (ym67)
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import command.CommandList;

public interface Parser {
	/**
	 * 
	 * @param input
	 * @param language
	 * @return a CommandList
	 * @throws ParseFormatException
	 */
	public CommandList parse(String input,String language) throws ParseFormatException;
	
	/**
	 * Save all user defined commands and global variables to file
	 * @param file
	 * @throws IOException
	 */
	public void save(File file) throws IOException;
	
	/**
	 * Load user defined commands and global variables from file
	 * @param file
	 * @throws IOException
	 */
	public void read(File file) throws IOException;
	
	/**
	 * Get a list of user defined command names
	 * @return List<String>
	 */
	public List<String> getUserCommand();
	
	/**
	 * Get a map of global variables to their values
	 * Caution: Edit this map will change variable values in the back end!
	 * @return Map<String,Double>
	 */
	public Map<String,Double> getVars();
}
