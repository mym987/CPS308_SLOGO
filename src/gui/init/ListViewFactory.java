package gui.init;

import java.util.Properties;

import gui.init.listview.HistoryView;
import javafx.scene.Node;

public class ListViewFactory extends Factory{
	private Properties properties;
	public ListViewFactory(Properties prop) {
		// TODO Auto-generated constructor stub
		properties = prop;
	}

	@Override
	public Node createObject(String id) {
		switch(id){
		case "history_view": return new HistoryView(properties);
		}
		return null;
	}

}
