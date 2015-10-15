package gui.init.table;

import javafx.scene.control.TableView;

public interface TableInterface {
	public TableView<?> addEntry(String entry);
	public TableView<?> removeEntry(String entry);
	//public TableView setOnClick();
	public TableView<?> clear();
}
