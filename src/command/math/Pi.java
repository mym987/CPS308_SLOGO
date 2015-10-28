package command.math;

import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
class Pi implements Command {

	@Override
	public double evaluate(Command... args) {
		return Math.PI;
	}

}
