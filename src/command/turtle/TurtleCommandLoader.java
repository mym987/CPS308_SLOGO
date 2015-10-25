package command.turtle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import action.Actions;
import command.Command;
import parser.ParseFormatException;

public class TurtleCommandLoader {

	public static Map<String, Command> load(Map<String,Boolean> names, Actions actions) {
		String prefix = TurtleCommandLoader.class.getPackage().getName() + ".";
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
	
	private static Command wrap(Command body, Actions actions){
		return new Command() {
			@Override
			public double evaluate(Command... args) throws ParseFormatException {
				Set<Integer> activeTurtles = actions.getFollowers();
				double value = 0;
				for (int idx : activeTurtles) {
					actions.setActive(idx);
					value = body.evaluate(args);
				}
				return value;
			}
			
			@Override
			public String name() {
				return body.name();
			}
		};
	}

}
