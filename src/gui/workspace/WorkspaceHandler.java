package gui.workspace;

import gui.init.ButtonFactory;
import gui.init.ColorPickerFactory;
import gui.init.ListViewFactory;
import gui.init.canvas.TurtleCanvas;
import gui.init.colorpicker.ColorChangeInterface;
import gui.init.textfield.CommandField;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Actions;
import model.SimpleActions;
import model.Turtle;
import parser.ParseFormatException;
import parser.Parser;
import parser.StackParser;

public class WorkspaceHandler implements ICreateWorkspace {
	private int WORKSPACE_NUMBER=0;
	private TabPane tabPane;
	private HBox topNav;
	private ButtonFactory buttonFactory;
	private ColorPickerFactory colorPickerFactory;
	private ListViewFactory listViewFactory;
	private String language;
	private ICreateWorkspace createWorkspaceInterface;
	private TextField commandField;

	public WorkspaceHandler(String lang){
		language = lang;
		tabPane = new TabPane();
		createWorkspaceInterface = this; 
		

		createWorkspace();
	}
	/**
	 * @param tabPane
	 */
	@Override
	public void createWorkspace() {
		// Any object that changes between workspaces must be created new.
		// Factories must be redefined for new inputs. 
		
		ImageView image = new ImageView();
		
		Turtle turtle = new Turtle(image, WORKSPACE_NUMBER, WORKSPACE_NUMBER);
		Actions simpleActions = new SimpleActions(turtle);

		
		TurtleCanvas turtleCanvas = new TurtleCanvas();
		ColorChangeInterface colorChangeInterface = turtleCanvas;
		commandField = new CommandField(simpleActions, language);

		try {
			buttonFactory = new ButtonFactory(createWorkspaceInterface, commandField, new StackParser(simpleActions), language );
		} catch (ParseFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		colorPickerFactory = new ColorPickerFactory(colorChangeInterface);
		listViewFactory = new ListViewFactory();


		
		
		Tab tab = new Tab();
		tab.setText("Workspace " + String.valueOf(WORKSPACE_NUMBER+1));
		
		BorderPane borderPane = new BorderPane();
	
		Pane turtlePane = new Pane();
		turtleCanvas.widthProperty().bind(turtlePane.widthProperty());
		turtleCanvas.heightProperty().bind(turtlePane.heightProperty());
		
		turtlePane.getChildren().add(turtleCanvas);
		borderPane.setCenter(turtlePane);
		
		HBox navBar = createNavBar();
		borderPane.setTop(navBar);
		HBox bottomBar = new HBox();
		bottomBar.getChildren().addAll(commandField, buttonFactory.createObject("enter_command"));
		borderPane.setBottom(bottomBar);
		
		Node historyView = listViewFactory.createObject("history_view");
		
		borderPane.setRight(historyView);

		tab.setContent(borderPane);
		tabPane.getTabs().add(tab);
		
		WORKSPACE_NUMBER++;
	}
	
	// Keep this method private to prevent ButtonFactory/ColorPickerFactory from being called if createWorkspace() is not run.
	private HBox createNavBar(){
		topNav = new HBox();
		Node[] navBarNodes = {colorPickerFactory.createObject("background_color"),
							  colorPickerFactory.createObject("pen_color"),
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