package command;

import java.util.List;

import model.Actions;
import parser.ParseFormatException;

public class TurtleCommands {
	public static Command getCommand(Actions actions, String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "Forward":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.forward(args.get(0).evaluate());
			};
		case "Backward":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.backward(args.get(0).evaluate());
			};
		case "Left":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.left(args.get(0).evaluate());
			};
		case "Right":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.right(args.get(0).evaluate());
			};
		case "SetHeading":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.setHeading(args.get(0).evaluate());
			};
		case "SetTowards":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				double x = args.get(0).evaluate(), y = args.get(1).evaluate();
				return actions.setTowards(x, y);
			};
		case "SetPosition":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				double x = args.get(0).evaluate(), y = args.get(1).evaluate();
				return actions.setPosition(x, y);
			};
		case "PenDown":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.penDown();
			};
		case "PenUp":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.penUp();
			};
		case "ShowTurtle":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.showTurtle();
			};
		case "HideTurtle":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.hideTurtle();
			};
		case "Home":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.home();
			};
		case "ClearScreen":
			if (args.size() != 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c)->{
				return actions.clearScreen();
			};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
	
	public static Command getQuery(Actions actions, String name, List<Command> args) throws ParseFormatException {
		if(args.size()!=0)
			throw new ParseFormatException("Args Length Mismatch!");
		switch (name) {
		case "XCoordinate":
			return (c)->{
				return actions.xCoordinate();
			};
		case "YCoordinate":
			return (c)->{
				return actions.yCoordinate();
			};
		case "Heading":
			return (c)->{
				return actions.heading();
			};
		case "IsPenDown":
			return (c)->{
				return actions.isPenDown();
			};
		case "IsShowing":
			return (c)->{
				return actions.isShowing();
			};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
}
