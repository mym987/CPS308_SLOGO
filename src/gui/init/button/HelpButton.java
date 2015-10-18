package gui.init.button;

import gui.webhelp.WebHelpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpButton extends Button implements ButtonInterface {
	// MOVE TO RESOURCE FILE
	private String URL = "http://www.cs.duke.edu/courses/compsci308/fall15/assign/03_slogo/commands.php";
	
	public HelpButton(){
		this.setText("HELP FILLER");
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
