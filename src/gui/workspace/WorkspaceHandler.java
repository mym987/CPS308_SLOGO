package gui.workspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Observer;
import java.util.Properties;

import action.Actions;
import action.SimpleActions;
import command.Command;
import gui.animation.AnimationControl;
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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Turtle;
import parser.ParseFormatException;
import parser.StackParser;
import turtlepath.Trail;

public class WorkspaceHandler implements ICreateWorkspace, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338716571136493141L;
	private int WORKSPACE_NUMBER = 0;
	private final int FRAMES_PER_SECOND = 30;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private double xStep;
	private double yStep;
	private TabPane tabPane;
	private Pane turtlePane;
	private HBox topNav;
	// private PropertyLoader propertyLoader = new PropertyLoader();
	private Properties properties;
	private ButtonFactory buttonFactory;
	private ColorPickerFactory colorPickerFactory;
	private ColorPickerFactory penColorPickerFactory;
	private ListViewFactory listViewFactory;
	private String language;
	private ICreateWorkspace createWorkspaceInterface;
	private TextArea commandField;
	private AnimationControl animControl;
	private ObservableList<Turtle> turtles;
	private TurtleCanvas turtleCanvas;
	private Trail turtleTrail;
	private HistoryList historyList;

	public WorkspaceHandler(String lang, Properties prop) {
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

		turtles = FXCollections.observableArrayList();
		/**
		 * turtles will be passed to simple actions class Sample code start
		 * here:
		 */
		turtles.addListener((ListChangeListener.Change<? extends Turtle> change) -> {
			change.next();
			List<? extends Turtle> addedTurtles = change.getAddedSubList();
			addedTurtles.forEach((turtle) -> {
				// Step 1: add turtle to canvas
				// Step 2: create a trail object for that turtle
			});
		});
		/**
		 * Sample code end here
		 */
		// The two lines below are not really needed after changing all codes to
		// comply with multiple turtles
		Turtle turtle = new Turtle();
		animControl = turtle;

		turtles.add(turtle);
		
	

		IChangeImage turtleImageInterface = turtle;
		IReset resetInterface = turtle;

		turtleCanvas = new TurtleCanvas();
		ColorChangeInterface colorChangeInterface = turtleCanvas;

		turtleTrail = new Trail(turtle);
		ColorChangeInterface penColorChangeInterface = turtleTrail;
		Actions simpleActions = new SimpleActions(turtles,turtleCanvas);
		commandField = new CommandField(simpleActions, language, properties);
		historyList = new HistoryList();
		try {
			buttonFactory = new ButtonFactory(createWorkspaceInterface, turtleImageInterface, resetInterface,
					commandField, new StackParser(simpleActions), language, properties, historyList, animControl);
		} catch (ParseFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		colorPickerFactory = new ColorPickerFactory(colorChangeInterface, properties);
		penColorPickerFactory = new ColorPickerFactory(penColorChangeInterface, properties);
		listViewFactory = new ListViewFactory(properties);

		Tab tab = new Tab();
		tab.setText(properties.getProperty("workspace") + " " + String.valueOf(WORKSPACE_NUMBER + 1));

		BorderPane borderPane = new BorderPane();

		turtlePane = new Pane();

		turtleCanvas.widthProperty().bind(turtlePane.widthProperty());
		turtleCanvas.heightProperty().bind(turtlePane.heightProperty());
		turtleTrail.widthProperty().bind(turtlePane.widthProperty());
		turtleTrail.heightProperty().bind(turtlePane.heightProperty());


		
		ChangeListener<? super Number> widthListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				turtle.screenWidth.set(newVal.doubleValue());
				turtle.setX(turtle.getX());
			}
		};
		ChangeListener<? super Number> heightListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				turtle.screenHeight.set(newVal.doubleValue());
				turtle.setY(turtle.getY());
			}
		};
		turtlePane.widthProperty().addListener(widthListener);
		turtlePane.heightProperty().addListener(heightListener);

//		turtle.getImage().setOnMouseClicked(e -> turtle.setX(turtle.screenWidth.get()));

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

	// Keep this method private to prevent ButtonFactory/ColorPickerFactory from
	// being called if createWorkspace() is not run.
	private HBox createNavBar() {
		topNav = new HBox();
		Button tempSave = new Button();
		tempSave.setText("TEMP WORKING SAVE");
		tempSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SerializedWorkspace serializedWorkspace = new SerializedWorkspace();
				serializedWorkspace.setBackgroundColor(turtleCanvas.getBackgroundColor());
				serializedWorkspace.setTrailColor(turtleTrail.getPenColorString());

				System.out.println(serializedWorkspace.getBackgroundColor() );
		
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save Workspace to File");
					fileChooser.getExtensionFilters().add(new ExtensionFilter("Serializable Output File",".ser"));
					 File selectedFile = fileChooser.showSaveDialog(new Stage());
					 
					 if (selectedFile != null) {
							try {
								FileOutputStream fileOut =
								         new FileOutputStream(selectedFile);
								         ObjectOutputStream out = new ObjectOutputStream(fileOut);
								         out.writeObject(serializedWorkspace);
								         out.close();
								         fileOut.close();
								         System.out.println("Serialized data is saved.");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				
			}
		
		});
		
		Button tempOpen = new Button();
		tempOpen.setText("TEMP WORKING OPEN");
		tempOpen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				SerializedWorkspace workspace = null;
				try
			      {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Load Workspace From File");
					
					 File selectedFile = fileChooser.showOpenDialog(new Stage());
					 if (selectedFile != null) {
						  FileInputStream fileIn = new FileInputStream(selectedFile);
					         ObjectInputStream in = new ObjectInputStream(fileIn);
					         workspace = (SerializedWorkspace) in.readObject();
					         in.close();
					         fileIn.close();
					         System.out.println("Successfully deserial");
					         System.out.println("Background color is " + workspace.getBackgroundColor());
					         turtleCanvas.setBackgroundColor(workspace.getBackgroundColor());
					         turtleTrail.setPenColorString(workspace.getTrailColor());
					 }			
			      }catch(IOException i)
			      {
			         i.printStackTrace();
			         return;
			      }catch(ClassNotFoundException c)
			      {
			         System.out.println("class not found");
			         c.printStackTrace();
			         return;
			      }
			}
		});
		
		
		
		Node[] navBarNodes = { colorPickerFactory.createObject("background_picker"),
				penColorPickerFactory.createObject("pen_picker"), buttonFactory.createObject("change_turtle_image"),
				buttonFactory.createObject("help_page"), buttonFactory.createObject("reset_turtle"),
				buttonFactory.createObject("open"), buttonFactory.createObject("save"),
				buttonFactory.createObject("grid"), buttonFactory.createObject("add_workspace") ,
				buttonFactory.createObject("animation_off"), buttonFactory.createObject("animation_on")
				,tempSave, tempOpen
				};
		topNav.getChildren().addAll(navBarNodes);
		return topNav;
	}

	private void setOnAction(EventHandler<ActionEvent> eventHandler) {
		// TODO Auto-generated method stub
		
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Pane getTurtlePane() {
		return turtlePane;
	}
	
	public void animationStep(){
		
	}
}