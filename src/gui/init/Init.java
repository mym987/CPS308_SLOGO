package gui.init;

import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class Init {
	private int WORKSPACE_NUMBER = 3;
	private int X_DIMENSION = 800;
	private int Y_DIMENSION = 600;
	
	private Scene scene;
	
	public Init(){
		// Setting up workspaces
		
		TabPane tabPane = new TabPane();
		scene = new Scene(tabPane,X_DIMENSION,Y_DIMENSION);
		for(int i=0;i<WORKSPACE_NUMBER;i++){
			// All workspace content fits in here.
			Tab tab = new Tab();
			tab.setText("Workspace " + String.valueOf(i));
			
			BorderPane borderPane = new BorderPane();
			
			// DEBUGGING: ColorPicker successfully included
			ColorPicker colorPicker = new ColorPicker();
			borderPane.setTop(colorPicker);
			
			tab.setContent(borderPane);
			tabPane.getTabs().add(tab);
		}
	
	}
	
	public Scene returnScene(){
		return scene;
	}
}
