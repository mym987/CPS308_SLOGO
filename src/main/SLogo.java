package main;
import gui.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class SLogo extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		new View(stage);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
