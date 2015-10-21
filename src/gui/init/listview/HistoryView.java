package gui.init.listview;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import gui.init.Init;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class HistoryView extends ListView<String> implements Observer{
	private List<String> history = new ArrayList<String>();

	public HistoryView(Properties properties){
		this.setPrefWidth(Init.getXDimension()/5);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub\
		history.add(((HistoryList) o).getNewestAddition());
		System.out.println(FXCollections.observableList(history));
		this.setItems(FXCollections.observableList(history));
	}
}
