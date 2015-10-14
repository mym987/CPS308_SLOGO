package gui.init;

import javafx.scene.control.Control;

public abstract class Factory {
		public abstract Control createObject(String id);
}
