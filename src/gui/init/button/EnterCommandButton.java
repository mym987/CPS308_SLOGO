package gui.init.button;

import java.util.Properties;

import command.Command;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import parser.ParseFormatException;
import parser.Parser;

public class EnterCommandButton extends Button implements ButtonInterface {
	private TextArea commandField;
	private Parser parser;
	private String language;
	public EnterCommandButton(TextArea field, Parser p, String lang, Properties properties){
		commandField = field;
		parser = p;
		language = lang;
		this.setText(properties.getProperty("enter"));
		this.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				try {
					System.out.println(commandField.getText());
					System.out.println(language);
					Command c = parser.parse(commandField.getText(), language);
					c.evaluate();
				} catch (ParseFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		});
		}
	
	@Override
	public void retrieveText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button setText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button setAction(EventHandler<?> event) {
		// TODO Auto-generated method stub
		return null;
	}

}
