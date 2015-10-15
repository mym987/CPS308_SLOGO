package gui.init;

import gui.init.button.TurtleImageButton;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

public class ButtonFactory extends Factory{
	private Button turtleImageButton; 
	
	@Override
	public Control createObject(String id) {
		switch(id){
		case "turtle_image": return new TurtleImageButton();
		
		default: 
		}
		return null;
	}

}
