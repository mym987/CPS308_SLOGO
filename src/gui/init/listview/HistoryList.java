package gui.init.listview;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class HistoryList extends Observable implements AddToHistory{
	public List<String> history;
	public String newestAddition;
	public HistoryList(){
		history = new ArrayList<String>();
	}
	@Override
	public void addToHistory(String string) {
		if(isNullOrWhitespace(string)){
			history.add(string);
			newestAddition=string;
			setChanged();
			notifyObservers();
		}
	}
	/**
	 * @param string
	 * @return
	 */
	private boolean isNullOrWhitespace(String string) {
		return string != null && !string.isEmpty() && !string.trim().isEmpty();
	}
	/**
	 * @return the newestAddition
	 */
	public String getNewestAddition() {
		return newestAddition;
	}
	
	
	

}
