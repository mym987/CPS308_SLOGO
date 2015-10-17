package gui.init;
import gui.workspace.WorkspaceHandler;
import javafx.scene.Scene;
public class Init {
	private static int X_DIMENSION = 800;
	private static int Y_DIMENSION = 600;
	
	public static int getXDimension(){
		return X_DIMENSION;
	}
	
	public static int getYDimension(){
		return Y_DIMENSION;
	}
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