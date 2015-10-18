package gui.init;

import gui.init.colorpicker.BackgroundColorPicker;
import gui.init.colorpicker.ColorChangeInterface;
import gui.init.colorpicker.PenColorPicker;
import javafx.scene.control.Control;

public class ColorPickerFactory extends Factory{
	private ColorChangeInterface colorChangeInterface;
	public ColorPickerFactory(ColorChangeInterface colorChange){
		colorChangeInterface = colorChange;
	}
	@Override
	public Control createObject(String id) {
		switch(id){
		
		case "background_color": return new BackgroundColorPicker(colorChangeInterface);
		case "pen_color"	   : return new PenColorPicker(colorChangeInterface);
		default: 
			
		}
		return null;
	}
}

