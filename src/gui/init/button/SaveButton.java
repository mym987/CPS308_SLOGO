package gui.init.button;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SaveButton extends Button implements ButtonInterface {

	public SaveButton(){
		setText();
	}
	
	@Override
	public void retrieveText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button setText() {
		// TODO Auto-generated method stub
		this.setText("Save (TEMP)");
		return this;
	}

	@Override
	public Button setAction(EventHandler<?> event) {
		// TODO Auto-generated method stub
		return null;
	}

}
