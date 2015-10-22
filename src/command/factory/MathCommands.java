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
				double sum = 0;
				for(Command cmd:args)
					sum+=cmd.evaluate();
				return sum;
			};
		case "Difference":
			return (c) -> {
				double diff = args.get(0).evaluate();
				for(int i = 1;i<args.size();i++)
					diff-=args.get(i).evaluate();
				return diff;
			};
		case "Product":
			return (c) -> {
				double product = 1;
				for(Command cmd:args)
					product*=cmd.evaluate();
				return product;
			};
		case "Quotient":
			return (c) -> {
				double q = args.get(0).evaluate();
				for(int i = 1;i<args.size();i++)
					q/=args.get(i).evaluate();
				return q;
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
