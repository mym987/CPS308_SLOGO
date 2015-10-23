package gui.workspace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Properties;
import java.util.Set;

import action.Actions;
import action.SimpleActions;
import gui.init.ButtonFactory;
import gui.init.ColorPickerFactory;
import gui.init.ListViewFactory;
import gui.init.canvas.IReset;
import gui.init.canvas.TurtleCanvas;
import gui.init.colorpicker.ColorChangeInterface;
import gui.init.listview.HistoryList;
import gui.init.textfield.CommandField;
import gui.turtle.IChangeImage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.Turtle;
import parser.ParseFormatException;
import parser.StackParser;
import turtlepath.Trail;

public class WorkspaceHandler implements ICreateWorkspace {
	private int WORKSPACE_NUMBER=0;
	private TabPane tabPane;
	private Pane turtlePane;
	private HBox topNav;
	//private PropertyLoader propertyLoader = new PropertyLoader();
	private Properties properties;
	private ButtonFactory buttonFactory;
	private ColorPickerFactory colorPickerFactory;
	private ColorPickerFactory penColorPickerFactory;
	private ListViewFactory listViewFactory;
	private String language;
	private ICreateWorkspace createWorkspaceInterface;
	private TextArea commandField;

	public WorkspaceHandler(String lang, Properties prop){
		language = lang;
		tabPane = new TabPane();
		createWorkspaceInterface = this; 
		properties = prop;
		createWorkspace();
	}
	/**
	 * @param tabPane
	 */
	@Override
	public void createWorkspace() {
		// Any object that changes between workspaces must be created new.
		// Factories must be redefined for new inputs. 

		Turtle turtle = new Turtle(0, 0);
		Map<Integer, Turtle> turtles = new HashMap<Integer, Turtle>();
		Set<Integer> activeTurtles = new HashSet<Integer>();
		turtles.put(1, turtle);
		activeTurtles.add(1);

		IChangeImage turtleImageInterface = turtle;
		IReset resetInterface = turtle;
		
		TurtleCanvas turtleCanvas = new TurtleCanvas();
		ColorChangeInterface colorChangeInterface = turtleCanvas;
		
		Trail turtleTrail = new Trail(turtle);
		ColorChangeInterface penColorChangeInterface = turtleTrail;
		
		Actions simpleActions = new SimpleActions(turtle, turtles, activeTurtles);
		
		commandField = new CommandField(simpleActions, language, properties);
		HistoryList historyList = new HistoryList();
		try {
			buttonFactory = new ButtonFactory(createWorkspaceInterface, turtleImageInterface, resetInterface, commandField, new StackParser(simpleActions), language, properties, historyList);
		} catch (ParseFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		colorPickerFactory = new ColorPickerFactory(colorChangeInterface, properties);
		penColorPickerFactory = new ColorPickerFactory(penColorChangeInterface, properties);
		listViewFactory = new ListViewFactory(properties);

		Tab tab = new Tab();
		tab.setText( properties.getProperty("workspace")+ " " + String.valueOf(WORKSPACE_NUMBER+1));
		
		BorderPane borderPane = new BorderPane();
	
		turtlePane = new Pane();
		
		turtleCanvas.widthProperty().bind(turtlePane.widthProperty());
		turtleCanvas.heightProperty().bind(turtlePane.heightProperty());
		turtleTrail.widthProperty().bind(turtlePane.widthProperty());
		turtleTrail.heightProperty().bind(turtlePane.heightProperty());
		
		ChangeListener<? super Number> widthListener = new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
	           turtle.initX.set(newVal.doubleValue() / 2);
	           turtle.setX(turtle.getX());
	        }
		};
		ChangeListener<? super Number> heightListener = new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
	           turtle.initY.set(newVal.doubleValue() / 2);
	           turtle.setY(turtle.getY());
	           
	        }
		};
		turtlePane.widthProperty().addListener(widthListener);
		turtlePane.heightProperty().addListener(heightListener);	
		
		turtle.getImage().setOnMouseClicked(e -> turtle.setX(turtle.initX.get()));

		turtlePane.getChildren().add(turtleCanvas);
		turtlePane.getChildren().add(turtleTrail);
		turtlePane.getChildren().add(turtle.getImage());
		borderPane.setCenter(turtlePane);
		
		HBox navBar = createNavBar();
		borderPane.setTop(navBar);

		HBox bottomBar = createConsoleBar();
		borderPane.setBottom(bottomBar);
		
		Node historyView = listViewFactory.createObject("history_view");
		historyList.addObserver((Observer) historyView);
		borderPane.setRight(historyView);

		tab.setContent(borderPane);
		tabPane.getTabs().add(tab);
		
		WORKSPACE_NUMBER++;
	}
	/**
	 * @param borderPane
	 */
	private HBox createConsoleBar() {
		HBox bottomBar = new HBox();
		HBox.setHgrow(commandField, Priority.ALWAYS);
		bottomBar.getChildren().addAll(commandField, buttonFactory.createObject("enter_command"));
		return bottomBar;
	}
	
	// Keep this method private to prevent ButtonFactory/ColorPickerFactory from being called if createWorkspace() is not run.
	private HBox createNavBar(){
		topNav = new HBox();
		Node[] navBarNodes = {colorPickerFactory.createObject("background_picker"),
							  penColorPickerFactory.createObject("pen_picker"),
							  buttonFactory.createObject("change_turtle_image"),
							  buttonFactory.createObject("help_page"),
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

	public Pane getTurtlePane() {
		return turtlePane;
	}
}