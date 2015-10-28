package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 * Simple tokenizer backed by a scanner, able to remove commands and extra black lines
 * 
 * @author Mike Ma (ym67)
 */
class SyntaxTokenizer implements Tokenizer {
	
	private Scanner myCommands;
	
	SyntaxTokenizer(String commands){
		//A more robust version
		commands = commands.replaceAll("#.*+", "").replace("[", " [ ").replace("]", " ] ")
					.replace("(", " ( ").replace(")", " ) ");
		myCommands = new Scanner(commands);
		myCommands.useDelimiter("\\s+");
	}

	@Override
	public boolean hasNext() {
		return myCommands.hasNext();
	}

	@Override
	public String next() {
		return myCommands.next();
	}

	@Override
	public void close() {
		myCommands.close();
	}
	
	public static void main(String[] args) throws IOException{
		Scanner s = new Scanner(new FileInputStream("test.in"));
		s.useDelimiter("\\Z");
		SyntaxTokenizer tokenizer = new SyntaxTokenizer(s.next());
		s.close();
		while(tokenizer.hasNext()){
			System.out.println(tokenizer.next());
		}
		tokenizer.close();
	}

}
