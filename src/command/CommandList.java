package command;

import java.util.LinkedList;
import java.util.List;

import parser.ParseFormatException;

/**
 * A command object wrapping around a list of commands, backed by a linkedlist.
 * 
 * Since the most common operations are appending at the last and execution in
 * sequence. It is more efficient to use a linkedlist.
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandList extends LinkedList<Command>implements Command {

	private static final long serialVersionUID = 4591734858712715728L;

	/**
	 * Construct an empty CommandList
	 */
	public CommandList() {
		super();
	}

	/**
	 * Construct a CommandList from a list of commands
	 * 
	 * @param list
	 */
	public CommandList(List<Command> list) {
		super(list);
	}

	/**
	 * Evaluate every command in the commandlist. Leave args empty!
	 * @throws ParseFormatException 
	 * 
	 */
	@Override
	public double evaluate(Command... args) throws ParseFormatException {
		double result = 0;
		for (Command c : this) {
			result = c.evaluate();
		}
		return result;
	}

	/**
	 * Parsing variable relies on this
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		forEach(c -> {
			sb.append(c.name() + "\n");
		});
		return sb.toString();
	}

}
