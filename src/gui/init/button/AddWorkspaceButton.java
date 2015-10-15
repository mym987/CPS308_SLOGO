package gui.init.button;

import gui.workspace.WorkspaceHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

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
