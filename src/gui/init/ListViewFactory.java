package gui.init;

import gui.init.listview.HistoryView;
import javafx.scene.Node;

public class ListViewFactory extends Factory{

	@Override
	public Node createObject(String id) {
		switch(id){
		case "history_view": return new HistoryView();
		}
		return null;
	}

}
