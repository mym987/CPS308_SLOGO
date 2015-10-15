package gui.init;

import javafx.scene.Node;
import javafx.scene.control.Control;

public abstract class Factory {
		public abstract Node createObject(String id);
}
