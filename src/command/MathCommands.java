package command;

import java.util.List;
import java.util.Random;

import parser.ParseFormatException;

public class MathCommands {
	public static Command get(String name, List<Command> args) throws ParseFormatException {
		switch (name) {
		case "Sum":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args.get(0).evaluate() + args.get(1).evaluate();
			};
		case "Difference":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args.get(0).evaluate() - args.get(1).evaluate();
			};
		case "Product":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args.get(0).evaluate() * args.get(1).evaluate();
			};
		case "Quotient":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args.get(0).evaluate() / args.get(1).evaluate();
			};
		case "Remainder":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return args.get(0).evaluate() % args.get(1).evaluate();
			};
		case "Minus":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return -args.get(0).evaluate();
			};
		case "Random":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return (new Random()).nextInt((int) Math.ceil(args.get(0).evaluate()));
			};
		case "Sine":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.sin(Math.toRadians(args.get(0).evaluate()));
			};
		case "Cosine":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.cos(Math.toRadians(args.get(0).evaluate()));
			};
		case "Tangent":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.tan(Math.toRadians(args.get(0).evaluate()));
			};
		case "ArcTangent":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.atan(Math.toRadians(args.get(0).evaluate()));
			};
		case "NaturalLog":
			if (args.size() != 1)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.log(Math.toRadians(args.get(0).evaluate()));
			};
		case "Power":
			if (args.size() != 2)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.pow(args.get(0).evaluate(), args.get(1).evaluate());
			};
		case "Pi":
			if (args.size() > 0)
				throw new ParseFormatException("Args Length Mismatch!");
			return (c) -> {
				return Math.PI;
			};
		default:
			throw new ParseFormatException(name + " does not exist!");
		}
	}
}
