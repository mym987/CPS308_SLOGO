package command.factory;

import java.util.List;
import java.util.Set;

import command.Command;
import model.Actions;
import parser.ParseFormatException;

class TurtleCommands {
	public static Command getCommand(Actions actions, String name, List<Command> args) throws ParseFormatException {
		Command body = getCommandHelper(actions, name, args);
		return (c)->{
			Set<Integer> activeTurtles = actions.getFollowers();
			double value = 0;
			for(int idx:activeTurtles){
				actions.setActive(idx);
				value = body.evaluate();
			}
			return value;
		};
	}
	
	private static Command getCommandHelper(Actions actions, String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "Forward":
			return (c)->{
				double value = 0;
				for(Command cmd:args)
					value = actions.forward(cmd.evaluate());
				return value;
			};
		case "Backward":
			return (c)->{
				double value = 0;
				for(Command cmd:args)
					value = actions.backward(cmd.evaluate());
				return value;
			};
		case "Left":
			return (c)->{
				double value = 0;
				for(Command cmd:args)
					value = actions.left(cmd.evaluate());
				return value;
			};
		case "Right":
			return (c)->{
				double value = 0;
				for(Command cmd:args)
					value = actions.right(cmd.evaluate());
				return value;
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
