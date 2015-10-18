package gui.init.button;

import gui.init.canvas.IReset;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ResetTurtleButton extends Button implements ButtonInterface{

	public ResetTurtleButton(IReset reset){
		this.setText("Reset turtle");
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				reset.reset();
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
