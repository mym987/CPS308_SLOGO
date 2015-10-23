package gui.init.textfield;

import java.util.Properties;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import action.Actions;
import parser.ParseFormatException;
import parser.Parser;
import parser.StackParser;

public class CommandField extends TextArea{
	private TextArea commandField = new TextArea();
	private Actions simpleAction;
	private String textInput;
	private String language;
	public CommandField(Actions action, String lang, Properties properties){
		simpleAction = action;
		language = lang;
		commandField.setPromptText(properties.getProperty("textarea_prompt"));
		//commandField.setPrefRowCount();
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