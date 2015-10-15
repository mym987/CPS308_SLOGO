package gui.init;

import gui.init.colorpicker.BackgroundColorPicker;
import gui.init.colorpicker.PenColorPicker;
import javafx.scene.control.Control;

public class ColorPickerFactory extends Factory{
	
	@Override
	public Control createObject(String id) {
		switch(id){
		
		case "background_color": return new BackgroundColorPicker();
		case "pen_color"	   : return new PenColorPicker();
		default: 
			
		}
		return null;
	}
}
