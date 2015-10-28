package command.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.Actions;
import command.Command;
/**
 * 
 * @author Mike Ma (ym67)
 *
 */
public class DisplayCommandLoader {

	public static Map<String, Command> load(List<String> names, Actions actions) {
		String prefix = DisplayCommandLoader.class.getPackage().getName() + ".";
		Map<String, Command> map = new HashMap<>();
		names.forEach((name) -> {
			try {
				map.put(name, (Command) Class.forName(prefix + name).getDeclaredConstructor(Actions.class)
						.newInstance(actions));
			} catch (Exception e) {
				System.err.println(name + " not found");
			}
		});
		return map;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("ClearStamps");
		list.add("Stamp");
		System.out.println(load(list, null));
	}

}
