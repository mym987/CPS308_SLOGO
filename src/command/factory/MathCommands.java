package command.factory;

import java.util.List;
import java.util.Random;

import command.Command;
import parser.ParseFormatException;

class MathCommands {
	public static Command get(String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "Sum":
			return (c) -> {
				return args.get(0).evaluate() + args.get(1).evaluate();
			};
		case "Difference":
			return (c) -> {
				return args.get(0).evaluate() - args.get(1).evaluate();
			};
		case "Product":
			return (c) -> {
				return args.get(0).evaluate() * args.get(1).evaluate();
			};
		case "Quotient":
			return (c) -> {
				return args.get(0).evaluate() / args.get(1).evaluate();
			};
		case "Remainder":
			return (c) -> {
				return args.get(0).evaluate() % args.get(1).evaluate();
			};
		case "Minus":
			return (c) -> {
				return -args.get(0).evaluate();
			};
		case "Random":
			return (c) -> {
				return (new Random()).nextInt((int) Math.ceil(args.get(0).evaluate()));
			};
		case "Sine":
			return (c) -> {
				return Math.sin(Math.toRadians(args.get(0).evaluate()));
			};
		case "Cosine":
			return (c) -> {
				return Math.cos(Math.toRadians(args.get(0).evaluate()));
			};
		case "Tangent":
			return (c) -> {
				return Math.tan(Math.toRadians(args.get(0).evaluate()));
			};
		case "ArcTangent":
			return (c) -> {
				return Math.atan(Math.toRadians(args.get(0).evaluate()));
			};
		case "NaturalLog":
			return (c) -> {
				return Math.log(Math.toRadians(args.get(0).evaluate()));
			};
		case "Power":
			return (c) -> {
				return Math.pow(args.get(0).evaluate(), args.get(1).evaluate());
			};
		case "Pi":
			return (c) -> {
				return Math.PI;
			};
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}
}
