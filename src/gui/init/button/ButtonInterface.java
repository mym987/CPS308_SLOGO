package gui.init.button;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface ButtonInterface {
	public void retrieveText();
	public Button setText();
	public Button setAction(EventHandler<?> event);
}
