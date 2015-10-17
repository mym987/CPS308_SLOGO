package command.commands;

import java.util.List;

import command.Command;
import parser.ParseFormatException;

public class BooleanCommands {
	public static Command get(String name, List<Command> args) throws ParseFormatException {
		if((name.equals("Not") && args.size()!=1) || (!name.equals("Not") && args.size()!=2))
			throw new ParseFormatException("Args Length Mismatch!");
		switch (name) {
		case "LessThan":
			return (c)->{
				return args.get(0).evaluate() < args.get(1).evaluate()?1:0;
			};
		case "GreaterThan":
			return (c)->{
				return args.get(0).evaluate() > args.get(1).evaluate()?1:0;
			};
		case "Equal":
			return (c)->{
				return args.get(0).evaluate() == args.get(1).evaluate()?1:0;
			};
		case "NotEqual":
			return (c)->{
				return args.get(0).evaluate() != args.get(1).evaluate()?1:0;
			};
		case "And":
			return (c)->{
				return (args.get(0).evaluate()!=0 && args.get(1).evaluate()!=0)?1:0;
			};
		case "Or":
			return (c)->{
				return (args.get(0).evaluate()!=0 || args.get(1).evaluate()!=0)?1:0;
			};
		case "Not":
			return (c)->{
				return (args.get(0).evaluate()==0)?1:0;
			};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
}
