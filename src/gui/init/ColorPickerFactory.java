package gui.init;

import java.util.Properties;

import gui.init.colorpicker.BackgroundColorPicker;
import gui.init.colorpicker.ColorChangeInterface;
import gui.init.colorpicker.PenColorPicker;
import javafx.scene.control.Control;

public class ColorPickerFactory extends Factory{
	private ColorChangeInterface colorChangeInterface;
	private Properties properties;
	public ColorPickerFactory(ColorChangeInterface colorChange, Properties prop){
		colorChangeInterface = colorChange;
		properties = prop;
	}
	@Override
	public Control createObject(String id) {
		switch(id){
		
		case "background_picker": return new BackgroundColorPicker(colorChangeInterface, properties);
		case "pen_picker"	   : return new PenColorPicker(colorChangeInterface, properties);
		default: 
			
		}
		return null;
	}
}

