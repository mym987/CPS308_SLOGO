package command;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
public class CommandList extends LinkedList<Command> implements Command{

	private static final long serialVersionUID = 4591734858712715728L;
	
	public CommandList(){
		super();
	}
	
	public CommandList(List<Command> list){
		super(list);
	}

	@Override
	public double evaluate(Command... args) {
		double result = 0;
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
