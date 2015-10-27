package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;

import command.Command;
import command.CommandList;
import command.CommandFactory;
import action.Actions;
import action.TestActions;
import util.LanguageLoader;

public class StackParser implements Parser {

	private final CommandFactory myFactory;
	private final LanguageLoader myLoader;

	private Map<String, String> myLanguageRules;
	private Map<String, String> mySyntaxRules;

	private Stack<Token> myTokenStack;
	private CommandList myCommandList;
	private Tokenizer myTokenizer;

	private FileLoader myFileLoader;
	private StringBuilder myHistory;
	private List<String> myUsrCmds;

	public StackParser(Actions actions) throws ParseFormatException {
		try {
			myFactory = new CommandFactory(actions);
			myLoader = new LanguageLoader();
			myFileLoader = new FileLoader();
			myUsrCmds = new ArrayList<>();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
	}

	@Override
	public CommandList parse(String input, String language) throws ParseFormatException {
		init(input, language);
		while (myTokenizer.hasNext()) {
			String token = commandDelocalize(myTokenizer.next());
			myHistory.append(token+" ");
			if (token.matches(mySyntaxRules.get("Command"))) {
				if (!myTokenStack.empty() && myTokenStack.peek().myName.equals("(")) {
					myTokenStack.peek().myName += token;
				} else {
					myTokenStack.push(new Token(token, myFactory.getNumArgs(token)));
				}
				if (token.equals("MakeUserInstruction"))
					pushUserCommandName();
			} else if (token.matches(mySyntaxRules.get("Constant"))) {
				parseConstant(token);
			} else if (token.matches(mySyntaxRules.get("Variable"))) {
				parseVariable(token);
			} else if (token.matches(mySyntaxRules.get("ListStart"))) {
				myTokenStack.push(new Token("List", Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("GroupStart"))) {
				myTokenStack.push(new Token("(", Integer.MAX_VALUE));
			} else if (token.matches(mySyntaxRules.get("ListEnd"))) {
				closeList();
			} else if (token.matches(mySyntaxRules.get("GroupEnd"))) {
				closeGroup();
			} else {
				throw new ParseFormatException("What is \"" + token + "\"");
			}
			popStack();
		}
		myTokenizer.close();
		// Check if there're any remaining commands in the stack, and throw
		// errors accordingly
		if (!myTokenStack.isEmpty()) {
			if (myTokenStack.peek().myName.matches("List|\\(.*"))
				throw new ParseFormatException("List/Group did not close properly");
			else
				throw new ParseFormatException("Insufficient arguments for \"" + myTokenStack.peek().myName + "\"");
		}
		myFactory.clearTempNameSpace();
		return myCommandList;
	}

	private void pushUserCommandName() throws ParseFormatException {
		try {
			String name = myTokenizer.next();
			myHistory = new StringBuilder("MakeUserInstruction " + name + " [");
			if (!name.matches(mySyntaxRules.get("Command"))
					|| !myTokenizer.next().matches(mySyntaxRules.get("ListStart")))
				throw new ParseFormatException();
			CommandList list = new CommandList();
			String var;
			while (!(var = myTokenizer.next()).matches(mySyntaxRules.get("ListEnd"))) {
				list.add(myFactory.getVarable(var));
			}
			myHistory.append(list.toString() + " ]");
			myTokenStack.peek().addCommand(myFactory.getEmptyCommand(name));
			myTokenStack.peek().addCommand(list);
			myFactory.reserveNameSpace(name, list.size());
		} catch (Exception e) {
			throw new ParseFormatException("Format error for making user command");
		}
	}

	private String commandDelocalize(String token) {
		for (String s : myLanguageRules.keySet()) {
			if (token.matches(s)) {
				return myLanguageRules.get(s);
			}
		}
		return token;
	}

	private void popStack() throws ParseFormatException {
		while (!myTokenStack.isEmpty() && myTokenStack.peek().satisfied()) {
			Token token = myTokenStack.pop();
			// System.out.println(token.myName);
			Command c = myFactory.getCommand(token.myName, token.myCommands);
			if (myTokenStack.isEmpty()) {
				myCommandList.add(c);
			} else {
				myTokenStack.peek().addCommand(c);
			}
			if (token.myName.equals("MakeUserInstruction")) {
				myFileLoader.add(myHistory.toString());
				myUsrCmds.add(token.myCommands.get(0).name());
				myHistory = new StringBuilder();
			}
		}
	}

	private void closeList() throws ParseFormatException {
		if (myTokenStack.isEmpty())
			throw new ParseFormatException("List mismatch");
		Token t = myTokenStack.peek();
		if (!t.myName.equals("List"))
			throw new ParseFormatException(t.myName + " missing arguments");
		t.myNumArgs = t.myCommands.size();
	}

	private void closeGroup() throws ParseFormatException {
		if (myTokenStack.isEmpty())
			throw new ParseFormatException("Group mismatch");
		Token t = myTokenStack.peek();
		if (!t.myName.startsWith("("))
			throw new ParseFormatException("Group mismatch");
		t.myName = t.myName.substring(1);
		t.myNumArgs = t.myCommands.size();
		if (t.myNumArgs < myFactory.getNumArgs(t.myName))
			throw new ParseFormatException("Insufficient args for \"" + t.myName + "\"");
	}

	private void parseVariable(String token) throws ParseFormatException {
		Command c = myFactory.getVarable(token);
		if (myTokenStack.isEmpty())
			myCommandList.add(c);
		else
			myTokenStack.peek().addCommand(c);
	}

	private void parseConstant(String token) throws ParseFormatException {
		try {
			double d = Double.parseDouble(token);
			Command c = myFactory.getConstant(d);
			if (myTokenStack.isEmpty())
				throw new ParseFormatException("Stand-alone constant: " + d);
			myTokenStack.peek().addCommand(c);
		} catch (Exception e) { // There may be more exceptions than
								// ParseFormatException
			throw new ParseFormatException(e.getMessage());
		}
	}

	private void init(String input, String language) throws ParseFormatException {
		loadRules(language);
		myTokenizer = new SyntaxTokenizer(input);
		myCommandList = new CommandList();
		myTokenStack = new Stack<>();
		myHistory = new StringBuilder();
	}

	private void loadRules(String language) throws ParseFormatException {
		try {
			myLanguageRules = getLanguageRules(language);
			mySyntaxRules = getSyntaxRules();
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
	}

	private Map<String, String> getLanguageRules(String language) throws IOException {
		return myLoader.getLocalizedSyntax(language);
	}

	private Map<String, String> getSyntaxRules() throws IOException {
		Map<String, String> syntaxMap = new HashMap<>();
		Properties prop = myLoader.load("Syntax");
		prop.forEach((k, v) -> {
			syntaxMap.put(k.toString(), v.toString());
		});
		return syntaxMap;
	}

	@Override
	public void save(File file) throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			myFactory.outputVar().forEach((k, v) -> sb.append("MakeVariable " + k + " " + v + "\n"));
			myFileLoader.add(sb.toString());
			myFileLoader.save(file);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public void read(File file) throws IOException {
		try {
			parse(myFileLoader.read(file), "English").evaluate();
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

	}
	
	@Override
	public List<String> getUserCommand() {
		return Collections.unmodifiableList(myUsrCmds);
	}

	@Override
	public Map<String, Double> getVars() {
		return myFactory.outputVar();
	}

	public static void main(String[] args) throws ParseFormatException, IOException {
		StackParser p = new StackParser(new TestActions(new ArrayList<>()));
		//p.myFactory.setCaseSensitivite(false);
		Scanner s = new Scanner(new FileInputStream("testcontrol.in"));
		Command c = p.parse(s.useDelimiter("\\Z").next(), "English");
		//p.read(new File("tmp.txt"));
		//Command c = p.parse("Print :sum Print :abc Print foo 100 Print fo 10 ", "English");
		c.evaluate();
		s.close();
		p.save(new File("tmp.txt"));
	}

}
