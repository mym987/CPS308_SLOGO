package gui.init.button;

import java.util.Properties;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class OpenButton extends Button implements ButtonInterface{
	public OpenButton(Properties properties){
		this.setText(properties.getProperty("open"));
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
