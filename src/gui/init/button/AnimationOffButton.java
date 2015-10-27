package gui.init.button;

import java.util.Properties;

import gui.animation.AnimationControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class AnimationOffButton extends Button implements ButtonInterface {
	public AnimationOffButton(AnimationControl animControl, Properties properties){
		this.setText(properties.getProperty("animation_off"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				animControl.turnOffAnimation();		
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
