package gui.init.colorpicker;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tooltip;

// Think about converting individual GUI elements into implementing interfaces
public class BackgroundColorPicker extends ColorPicker {
	public BackgroundColorPicker(){
		this.setTooltip(new Tooltip("FILLER"));
	}
}
