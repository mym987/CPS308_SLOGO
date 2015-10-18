package gui.init.listview;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import gui.init.Init;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class HistoryView extends ListView<String> implements Observer{
	private ListView<String> historyView = new ListView<String>();
	//private ObservableList<String> items;
	public HistoryView(Properties properties){
	//	this.getChildren().add(items);
		
		historyView.setPrefWidth(Init.getXDimension()/5);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
