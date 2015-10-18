package gui.webhelp;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BrowserWindow extends Region {

	public BrowserWindow(String url){
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load(url);
		this.getChildren().add(webView);
	}
}
