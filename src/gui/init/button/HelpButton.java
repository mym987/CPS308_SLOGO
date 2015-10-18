package gui.init.button;

import java.util.Properties;

import gui.webhelp.WebHelpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpButton extends Button implements ButtonInterface {
	private String URL;
	
	public HelpButton(Properties properties){
		URL = properties.getProperty("help_URL");
		this.setText(properties.getProperty("help_page"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				WebHelpView webHelpView = new WebHelpView(URL);
				try {
					webHelpView.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void retrieveText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button setText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button setAction(EventHandler<?> event) {
		// TODO Auto-generated method stub
		return null;
	}

}
