package main;

import gui.View;
import gui.init.Init;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SLogo extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Init init = new Init();
		Scene scene = init.returnScene();		
		new View(stage, scene);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
