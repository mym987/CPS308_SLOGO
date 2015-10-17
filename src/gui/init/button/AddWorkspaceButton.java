package gui.init.button;

import gui.workspace.WorkspaceHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
// don't pass in a workspaceHandler class, but pass back in an empty workspace, use handler to define this
// Set up a separate class that ONLY manages the workspaces, not the creation
// This handler can be passed around.

// 1) Set up interface that implements only one interface 2) Lambda expression that bundles the handler up and uses only that method
// 3) Pass only what is needed.
public class AddWorkspaceButton extends Button implements ButtonInterface {
	private WorkspaceHandler workspaceHandler;
	public AddWorkspaceButton(WorkspaceHandler handler){
		workspaceHandler = handler;
		setText();
		this.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        workspaceHandler.createWorkspace();
		    }
		});
	}
	@Override
	public void retrieveText() {
		// TODO Auto-generated method stub

	}

	@Override
	public Button setText() {
		// TODO Auto-generated method stub
		this.setText("Add Workspace");
		return this;
	}

	@Override
	public Button setAction(EventHandler<?> event) {
		// TODO Auto-generated method stub
		return null;
	}

}
