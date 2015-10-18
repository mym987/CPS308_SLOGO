package gui.init.textfield;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Actions;
import parser.ParseFormatException;
import parser.Parser;
import parser.StackParser;

public class CommandField extends TextField{
	private TextField commandField = new TextField();
	private Actions simpleAction;
	private String textInput;
	private String language;
	public CommandField(Actions action, String lang){
		simpleAction = action;
		language = lang;
		commandField.setPromptText("Enter your command");
		onEnter();

	}

	private void onEnter() {
		textInput = this.getText();
		commandField.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	
	            	try {
						Parser parser = new StackParser(simpleAction);
						parser.parse(textInput, language);
					} catch (ParseFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            }
	        }
	    });
	}
	

}