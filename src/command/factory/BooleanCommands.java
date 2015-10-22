
package command.factory;

import java.util.List;

import command.Command;
import parser.ParseFormatException;

class BooleanCommands {
	public static Command get(String name, List<Command> args) throws ParseFormatException {
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
				for(Command cmd:args){
					if(cmd.evaluate()==0)
						return 0;
				}
				return 1;
			};
		case "Or":
			return (c)->{
				for(Command cmd:args){
					if(cmd.evaluate()!=0)
						return 1;
				}
				return 0;
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
