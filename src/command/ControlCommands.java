package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.ParseFormatException;

public class ControlCommands {
	
	private List<Map<String,Double>> myVariables;
	
	public ControlCommands(){
		myVariables = new ArrayList<>();
	}
	
	//MakeUserInstruction = 3,Control
	public Command get(String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "MakeVariable":
			return makeVar(args);
		case "Repeat":
			return repeat(args);
		case "DoTimes":
			return doTimes(args);
		case "For":
			return forCommand(args);
		case "If":
			return ifCommand(args);
		case "IfElse":
			return ifElse(args);
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}

	private Command makeVar(List<Command> args) throws ParseFormatException {
		if(args.size()!=2 || !(args.get(0) instanceof Variable))
			throw new ParseFormatException("Format Error for Making Variable!");
		//TODO
		return null;
	}
	
	private Command makeVar(String name, double value){
		//TODO
		return null;
	}

	private Command repeat(List<Command> args) {
		return (cmd) -> {
			double times = args.get(0).evaluate(), result = 0;
			Command body = args.get(1);
			for (int i = 0; i < times; i++) {
				result = body.evaluate();
			}
			return result;
		};

	}

	private Command doTimes(List<Command> args) throws ParseFormatException {
		
		try {
			CommandList loopCondition = (CommandList) args.get(0);
			Command loopBody = args.get(1);
			int max = (int) loopCondition.get(1).evaluate();
			String name = ((Variable) loopCondition.get(0)).toString();
			return (cmd) -> {
				double result = 0;
				for (int i = 1; i <= max; i++) {
					makeVar(name, i);
					result = loopBody.evaluate();
				}
				;
				return result;
			};
		} catch (Exception e) {
			throw new ParseFormatException(e.getMessage());
		}
		
	}

	private Command forCommand(List<Command> args) {
		return null;
	}

	private Command ifCommand(List<Command> args) {
		return (cmd) -> {
			if (args.get(0).evaluate() != 0)
				return args.get(1).evaluate();
			else
				return 0;
		};
	}

	private Command ifElse(List<Command> args) {
		return (cmd) -> {
			if (args.get(0).evaluate() != 0)
				return args.get(1).evaluate();
			else
				return args.get(2).evaluate();
		};
	}
	
}
