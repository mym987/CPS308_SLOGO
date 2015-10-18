package gui.webhelp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WebHelpView extends Application{
	private double WINDOW_WIDTH = 800;
	private double WINDOW_HEIGHT = 600;
	private String URL;
	
	public WebHelpView(String page){
		URL = page;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		try{
			Scene scene = new Scene(new BrowserWindow(URL),WINDOW_WIDTH,WINDOW_HEIGHT);
			arg0.setTitle("Web Help Browser");
			arg0.setScene(scene);
			arg0.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
