package gui.init;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Init {
	private int WORKSPACE_NUMBER = 3;
	private int X_DIMENSION = 800;
	private int Y_DIMENSION = 600;
	
	private Scene scene;
	
	public Init(){
		// Setting up workspaces
		
		TabPane tabPane = new TabPane();
		for(int i=0;i<WORKSPACE_NUMBER;i++){
			// All workspace content fits in here.
			Tab tab = new Tab();
			tab.setText("Workspace " + String.valueOf(i));
			tabPane.getTabs().add(tab);
		}
		scene = new Scene(tabPane,X_DIMENSION,Y_DIMENSION);
		
	}
	
	public Scene returnScene(){
		return scene;
	}
}
