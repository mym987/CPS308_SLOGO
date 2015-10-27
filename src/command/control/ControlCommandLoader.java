package command.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.Command;
import parser.ParseFormatException;

public class ControlCommandLoader {

	private VariableManager myVarMgr;
	private UserCommandManager myUsrMgr;

	public ControlCommandLoader(UserCommandManager mgr) {
		myUsrMgr = mgr;
		myVarMgr = new VariableManager();
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Command> load(List<String> names) {
		String prefix = getClass().getPackage().getName() + ".";
		Class[] types = { VariableManager.class, UserCommandManager.class };
		Map<String, Command> map = new HashMap<>();
		names.forEach((name) -> {
			try {
				map.put(name, (Command) Class.forName(prefix + name).getDeclaredConstructor(types).newInstance(myVarMgr,
						myUsrMgr));
			} catch (Exception e) {
				System.err.println(name + " not found");
			}
		});
		return map;
	}

	public Variable getVariable(String name) throws ParseFormatException {
		return myVarMgr.getVar(name);
	}
	
	public Map<String, Double> outputVar(){
		return myVarMgr.outputVar();
	}

}
