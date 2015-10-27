package gui.init;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Properties;

import gui.init.dialog.LanguageDialog;
import gui.workspace.WorkspaceHandler;
import javafx.scene.Scene;
import util.PropertyLoader;
public class Init {
	private static double X_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static double Y_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static double getXDimension(){
		return X_DIMENSION;
	}
	
	public static double getYDimension(){
		return Y_DIMENSION;
	}
	private Scene scene;
	private WorkspaceHandler workspaceHandler;
	
	public Init(){
		PropertyLoader propertyLoader = new PropertyLoader();
		LanguageDialog languageDialog;
		try {
			Properties properties = propertyLoader.load("GUI");
			languageDialog = new LanguageDialog(properties);
			workspaceHandler = new WorkspaceHandler(languageDialog.getSelected(),properties);
			scene = new Scene(workspaceHandler.getTabPane(),X_DIMENSION,Y_DIMENSION);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Scene returnScene(){
		return scene;
	}
}