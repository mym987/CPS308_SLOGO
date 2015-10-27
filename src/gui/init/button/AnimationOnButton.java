package gui.init.button;

import java.util.Properties;

import gui.animation.AnimationControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class AnimationOnButton extends Button implements ButtonInterface {
	public AnimationOnButton(AnimationControl animControl, Properties properties){
		this.setText(properties.getProperty("animation_on"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				animControl.resetAnimation();		
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
