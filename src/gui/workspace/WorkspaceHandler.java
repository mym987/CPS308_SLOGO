package gui.workspace;

import gui.init.ButtonFactory;
import gui.init.ColorPickerFactory;
import gui.init.ListViewFactory;
import gui.init.canvas.TurtleCanvas;
import gui.init.combo.LanguageCombo;
import gui.init.textfield.CommandField;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class WorkspaceHandler {
	private int WORKSPACE_NUMBER=0;
	private TabPane tabPane;
	private HBox topNav;
	private ColorPickerFactory colorPickerFactory = new ColorPickerFactory();
	private ButtonFactory buttonFactory;
	private ListViewFactory listViewFactory;

	public WorkspaceHandler(){
		tabPane = new TabPane();
		buttonFactory = new ButtonFactory(this);
		listViewFactory = new ListViewFactory();
		createWorkspace();
	}
	/**
	 * @param tabPane
	 */
	public void createWorkspace() {
		Tab tab = new Tab();
		tab.setText("Workspace " + String.valueOf(WORKSPACE_NUMBER+1));
		
		BorderPane borderPane = new BorderPane();
		HBox navBar = createNavBar();
		TextField commandField = new CommandField();
		borderPane.setTop(navBar);
		borderPane.setBottom(commandField);
		
		Node historyView = listViewFactory.createObject("history_view");
		
		borderPane.setRight(historyView);
		
		Pane turtlePane = new Pane();
		TurtleCanvas turtleCanvas = new TurtleCanvas();
		turtleCanvas.widthProperty().bind(turtlePane.widthProperty());
		turtleCanvas.heightProperty().bind(turtlePane.heightProperty());
		turtlePane.getChildren().add(turtleCanvas);
		borderPane.setCenter(turtlePane);
		
		tab.setContent(borderPane);
		tabPane.getTabs().add(tab);
		
		WORKSPACE_NUMBER++;
	}
	
	private HBox createNavBar(){
		topNav = new HBox();
		Node[] navBarNodes = {colorPickerFactory.createObject("background_color"),
							  colorPickerFactory.createObject("pen_color"),
							  new LanguageCombo(),
							  buttonFactory.createObject("turtle_image"),
							  buttonFactory.createObject("help"),
							  buttonFactory.createObject("reset_turtle"),
							  buttonFactory.createObject("open"),
							  buttonFactory.createObject("save"),
							  buttonFactory.createObject("grid"),
							  buttonFactory.createObject("add_workspace")
							  };
		topNav.getChildren().addAll(navBarNodes);
		return topNav;
	}
	
	public TabPane getTabPane(){
		return tabPane;
	}
}
