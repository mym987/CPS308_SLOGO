package gui.init.colorpicker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tooltip;

// Think about converting individual GUI elements into implementing interfaces
public class BackgroundColorPicker extends ColorPicker {
	private BackgroundColorPicker backgroundColorPicker = this;
	
	public BackgroundColorPicker(ColorChangeInterface colorChange){
	this.setTooltip(new Tooltip("BACKGROUND COLOR FILLER"));
	
	this.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent arg0) {
			colorChange.changeColor(backgroundColorPicker.getValue());
		}
		
	});
	}	
}

