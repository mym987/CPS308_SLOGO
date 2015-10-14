package command;

import java.util.LinkedList;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandList extends LinkedList<Command> implements Command{

	private static final long serialVersionUID = 4591734858712715728L;

	@Override
	public Number evaluate(Command... args) {
		Number result = -1;
		for(Command c:this){
			result = c.evaluate();
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		forEach(c->{
			sb.append(c+"\n");
		});
		return sb.toString();
	}

}
