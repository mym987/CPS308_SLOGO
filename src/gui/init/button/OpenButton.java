package gui.init.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.Parser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OpenButton extends Button implements ButtonInterface{
	public OpenButton(Parser parser,Properties properties){
		this.setText(properties.getProperty("open"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Load Image From File");
				
				 File selectedFile = fileChooser.showOpenDialog(new Stage());
				 if (selectedFile != null) {
						try {
							parser.read(selectedFile);
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
