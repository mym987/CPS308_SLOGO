package gui.init.button;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.Parser;

public class SaveButton extends Button implements ButtonInterface {

	public SaveButton(Parser parser, Properties properties){
		this.setText(properties.getProperty("save"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Load Image From File");
				
				 File selectedFile = fileChooser.showSaveDialog(new Stage());
				 if (selectedFile != null) {
						try {
							parser.save(selectedFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
	
