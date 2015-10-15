package gui.init;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Init {
	private int WORKSPACE_NUMBER = 3;
	private int X_DIMENSION = 800;
	private int Y_DIMENSION = 600;
	
	private Scene scene;
	private ColorPickerFactory colorPickerFactory = new ColorPickerFactory();
	private ButtonFactory buttonFactory = new ButtonFactory();
	
	private HBox topNav;
	
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
//			ColorPicker colorPicker = new ColorPicker();
//			colorPicker.setTooltip(new Tooltip("FILLER"));
			
			borderPane.setTop(createNavBar());
			
			tab.setContent(borderPane);
			tabPane.getTabs().add(tab);
		}
	
	}
	
	private HBox createNavBar(){
		topNav = new HBox();
		
		Node[] navBarNodes = {colorPickerFactory.createObject("background_color"),
							  colorPickerFactory.createObject("pen_color"),
							  buttonFactory.createObject("turtle_image"),
							  buttonFactory.createObject("help"),
							  buttonFactory.createObject("reset_turtle"),
							  buttonFactory.createObject("open"),
							  buttonFactory.createObject("save"),
							  buttonFactory.createObject("grid")
							  };

		topNav.getChildren().addAll(navBarNodes);
		return topNav;
	}
	public Scene returnScene(){
		return scene;
	}
}
