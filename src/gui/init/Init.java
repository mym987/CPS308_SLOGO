package gui.init;
import gui.workspace.WorkspaceHandler;
import javafx.scene.Scene;
public class Init {
	private int X_DIMENSION = 800;
	private int Y_DIMENSION = 600;
	private Scene scene;
	private WorkspaceHandler workspaceHandler = new WorkspaceHandler();
	
	public Init(){
		// Setting up workspaces
		scene = new Scene(workspaceHandler.getTabPane(),X_DIMENSION,Y_DIMENSION);
	}
	public Scene returnScene(){
		return scene;
	}
}
