package gui.init;

import gui.init.listview.HistoryView;
import javafx.scene.Node;

public class ListViewFactory extends Factory{

	@Override
	public Node createObject(String id) {
		// TODO Auto-generated method stub
		
		switch(id){
		case "history_view": new HistoryView();
		}
		return null;
	}

}
