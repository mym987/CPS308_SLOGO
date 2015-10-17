package gui.workspace;

import gui.init.ButtonFactory;
import gui.init.ColorPickerFactory;
import gui.init.ListViewFactory;
import gui.init.combo.LanguageCombo;
import gui.init.textfield.CommandField;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
		borderPane.setTop(createNavBar());
		borderPane.setBottom(new CommandField());
		borderPane.setLeft(listViewFactory.createObject("history_table"));
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
