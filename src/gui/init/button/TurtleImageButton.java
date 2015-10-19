package gui.init.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import gui.turtle.IChangeImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TurtleImageButton extends Button implements ButtonInterface{
	
	public TurtleImageButton(IChangeImage changeImage, Properties properties){
		this.setText(properties.getProperty("change_turtle_image"));
		this.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Load Image From File");
				fileChooser.getExtensionFilters().addAll(
				         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
				         new ExtensionFilter("All Files", "*.*"));
				 File selectedFile = fileChooser.showOpenDialog(new Stage());
				 if (selectedFile != null) {
					try {
						Image img = new Image(new FileInputStream(selectedFile));
						changeImage.setImage(img);
					} catch (FileNotFoundException e) {
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