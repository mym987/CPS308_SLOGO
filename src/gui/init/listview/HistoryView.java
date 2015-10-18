package gui.init.listview;

import java.util.Observable;
import java.util.Observer;

import gui.init.Init;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class HistoryView extends ListView<String> implements Observer{
	private ListView<String> historyView = new ListView<String>();
	public HistoryView(){
		ObservableList<String> items;
		//this.setItems(items);
		historyView.setPrefWidth(Init.getXDimension()/5);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
