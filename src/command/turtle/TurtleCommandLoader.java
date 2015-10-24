package command.turtle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import action.Actions;
import command.Command;

public class TurtleCommandLoader {

	public Map<String, Command> load(Map<String,Boolean> names, Actions actions) {
		String prefix = getClass().getPackage().getName() + ".";
		Map<String, Command> map = new HashMap<>();
		names.forEach((name,wrap) -> {
			try {
				Command c = (Command) Class.forName(prefix + name).getDeclaredConstructor(Actions.class).newInstance(actions);
				if(wrap)
					c = wrap(c,actions);
				map.put(name, c);
			} catch (Exception e) {
				System.err.println(name + " not found");
			}
		});
		return map;
	}
	
	private Command wrap(Command body, Actions actions){
		return new Command() {
			@Override
			public double evaluate(Command... args) {
				Set<Integer> activeTurtles = actions.getFollowers();
				double value = 0;
				for (int idx : activeTurtles) {
					actions.setActive(idx);
					value = body.evaluate();
				}
				return value;
			}
			
			@Override
			public String name() {
				return body.name();
			}
		};
	}

	public static void main(String[] args) {
		TurtleCommandLoader l = new TurtleCommandLoader();
		Map<String,Boolean> list = new HashMap<>();
		list.put("Forward",false);
		list.put("Backward",true);
		l.load(list, null).forEach((k,v)->System.out.println(k+" "+v.name()));
	}

}
