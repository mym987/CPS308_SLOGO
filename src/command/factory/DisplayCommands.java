package command.factory;

import java.util.List;

import command.Command;
import model.Actions;
import parser.ParseFormatException;

class DisplayCommands {
	public static Command get(Actions actions, String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "SetBackground":
			return (c) -> {
				return actions.setBackground(args.get(0).evaluate());
			};
		case "SetPenColor":
			return (c) -> {
				return actions.setPenColor(args.get(0).evaluate());
			};
		case "SetPenSize":
			return (c) -> {
				return actions.setPenSize(args.get(0).evaluate());
			};
		case "SetShape":
			return (c) -> {
				return actions.setShape(args.get(0).evaluate());
			};
		case "SetPalette":
			return (c) -> {
				return actions.setPalette(args.get(0).evaluate(), args.get(1).evaluate(), args.get(2).evaluate(),
						args.get(3).evaluate());
			};
		case "GetPenColor":
			return (c) -> {
				return actions.getPenColor();
			};
		case "GetShape":
			return (c) -> {
				return actions.getShape();
			};
		case "Stamp":
			return (c) -> {
				return actions.stamp();
			};
		case "ClearStamps":
			return (c) -> {
				return actions.clearStamp();
			};
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}
}
