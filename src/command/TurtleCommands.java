package command;

import java.util.List;

import model.Actions;
import parser.ParseFormatException;

public class TurtleCommands {
	public static Command getCommand(Actions actions, String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "Forward":
			return (c)->{
				return actions.forward(args.get(0).evaluate());
			};
		case "Backward":
			return (c)->{
				return actions.backward(args.get(0).evaluate());
			};
		case "Left":
			return (c)->{
				return actions.left(args.get(0).evaluate());
			};
		case "Right":
			return (c)->{
				return actions.right(args.get(0).evaluate());
			};
		case "SetHeading":
			return (c)->{
				return actions.setHeading(args.get(0).evaluate());
			};
		case "SetTowards":
			return (c)->{
				double x = args.get(0).evaluate(), y = args.get(1).evaluate();
				return actions.setTowards(x, y);
			};
		case "SetPosition":
			return (c)->{
				double x = args.get(0).evaluate(), y = args.get(1).evaluate();
				return actions.setPosition(x, y);
			};
		case "PenDown":
			return (c)->{
				return actions.penDown();
			};
		case "PenUp":
			return (c)->{
				return actions.penUp();
			};
		case "ShowTurtle":
			return (c)->{
				return actions.showTurtle();
			};
		case "HideTurtle":
			return (c)->{
				return actions.hideTurtle();
			};
		case "Home":
			return (c)->{
				return actions.home();
			};
		case "ClearScreen":
			return (c)->{
				return actions.clearScreen();
			};
		default:
			throw new ParseFormatException(name+" does not exist!");
		}
	}
	
	public static Command getQuery(Actions actions, String name, List<Command> args) throws ParseFormatException {
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
