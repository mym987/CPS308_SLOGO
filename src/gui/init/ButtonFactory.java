package gui.init;

import gui.init.button.AddWorkspaceButton;
import gui.init.button.EnterCommandButton;
import gui.init.button.GridButton;
import gui.init.button.HelpButton;
import gui.init.button.OpenButton;
import gui.init.button.ResetTurtleButton;
import gui.init.button.SaveButton;
import gui.init.button.TurtleImageButton;
import gui.workspace.ICreateWorkspace;
import gui.workspace.WorkspaceHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import parser.Parser;

public class ButtonFactory extends Factory{
	private ICreateWorkspace createWorkspace;
	private TextArea commandField;
	private Parser parser;
	private String language;
	public ButtonFactory(ICreateWorkspace createInterface, TextArea field, Parser p, String lang){
		createWorkspace = createInterface;
		commandField = field;
		parser = p;
		language = lang;
	}
	@Override
	public Node createObject(String id) {
		switch(id){
		case "help"		   		: return new HelpButton();
		case "reset_turtle"		: return new ResetTurtleButton();
		case "open"		   		: return new OpenButton();
		case "save"		   		: return new SaveButton();
		case "grid"		   		: return new GridButton();
		case "turtle_image"		: return new TurtleImageButton();
		case "add_workspace"	: return new AddWorkspaceButton(createWorkspace);
		case "enter_command"	: return new EnterCommandButton(commandField, parser, language);

		default: 
		}
		return null;
	}
}