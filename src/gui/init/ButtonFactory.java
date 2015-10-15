package gui.init;

import gui.init.button.AddWorkspaceButton;
import gui.init.button.GridButton;
import gui.init.button.HelpButton;
import gui.init.button.OpenButton;
import gui.init.button.ResetTurtleButton;
import gui.init.button.SaveButton;
import gui.init.button.TurtleImageButton;
import gui.workspace.WorkspaceHandler;
import javafx.scene.Node;

public class ButtonFactory extends Factory{
	private WorkspaceHandler workspaceHandler;
	public ButtonFactory(WorkspaceHandler handler){
		workspaceHandler = handler;
	}
	@Override
	public Node createObject(String id) {
		switch(id){
		case "help"		   		: return new HelpButton();
		case "reset_turtle"		: return new ResetTurtleButton();
		case "open"		   		: return new OpenButton();
		case "save"		   		: return new SaveButton();
		case "grid"		   		: return new GridButton();
		case "turtle_image"		: return new TurtleImageButton();
		case "add_workspace"	: return new AddWorkspaceButton(workspaceHandler);

		default: 
		}
		return null;
	}

}