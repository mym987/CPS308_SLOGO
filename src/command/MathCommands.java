package command;

import java.util.Random;

import parser.ParseFormatException;

public class MathCommands {
	public static Command get(String name, Command... args) throws ParseFormatException {
		switch (name) {
		case "Sum":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args[0].evaluate() + args[1].evaluate();
			};
		case "Difference":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args[0].evaluate() - args[1].evaluate();
			};
		case "Product":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args[0].evaluate() * args[1].evaluate();
			};
		case "Quotient":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args[0].evaluate() / args[1].evaluate();
			};
		case "Remainder":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args[0].evaluate() % args[1].evaluate();
			};
		case "Minus":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return -args[0].evaluate();
			};
		case "Random":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return (new Random()).nextInt((int) Math.ceil(args[0].evaluate()));
			};
		case "Sine":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.sin(Math.toRadians(args[0].evaluate()));
			};
		case "Cosine":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.cos(Math.toRadians(args[0].evaluate()));
			};
		case "Tangent":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.tan(Math.toRadians(args[0].evaluate()));
			};
		case "ArcTangent":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.atan(Math.toRadians(args[0].evaluate()));
			};
		case "NaturalLog":
			if (args.length != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.log(Math.toRadians(args[0].evaluate()));
			};
		case "Power":
			if (args.length != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.pow(args[0].evaluate(), args[1].evaluate());
			};
		case "Pi":
			if (args.length > 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.PI;
			};
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}
}
