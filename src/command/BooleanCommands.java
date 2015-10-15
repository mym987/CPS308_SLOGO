package command;

import parser.ParseFormatException;

public class BooleanCommands {
	public static Command get(String name, Command... args) throws ParseFormatException {
		if((name.equals("Not") && args.length!=1) || (!name.equals("Not") && args.length!=2))
			throw new ParseFormatException("Args Length Mismatch!");
		switch (name) {
		case "LessThan":
			return (c)->{
				return args[0].evaluate() < args[1].evaluate()?1:0;
			};
		case "GreaterThan":
			return (c)->{
				return args[0].evaluate() > args[1].evaluate()?1:0;
			};
		case "Equal":
			return (c)->{
				return args[0].evaluate() == args[1].evaluate()?1:0;
			};
		case "NotEqual":
			return (c)->{
				return args[0].evaluate() != args[1].evaluate()?1:0;
			};
		case "And":
			return (c)->{
				return (args[0].evaluate()!=0 && args[1].evaluate()!=0)?1:0;
			};
		case "Or":
			return (c)->{
				return (args[0].evaluate()!=0 || args[1].evaluate()!=0)?1:0;
			};
		case "Not":
			return (c)->{
				return (args[0].evaluate()==0)?1:0;
			};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
}
