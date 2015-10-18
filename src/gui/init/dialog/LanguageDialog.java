package gui.init.dialog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javafx.scene.control.ChoiceDialog;

public class LanguageDialog extends ChoiceDialog<String> {
	public String selected;
	public LanguageDialog(Properties properties){
		String[] arrayData = {properties.getProperty("english"),
								properties.getProperty("chinese"),
								properties.getProperty("french"),
								properties.getProperty("german"),
								properties.getProperty("italian"),
								properties.getProperty("portuguese"),
								properties.getProperty("russian"),
								properties.getProperty("spanish")
		 };
		
		List<String> dialogData = Arrays.asList(arrayData);
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(dialogData.get(0), dialogData);
		// MOVE TO RESOURCE FILE
		dialog.setTitle(properties.getProperty("dialog_title"));
		dialog.setHeaderText(properties.getProperty("dialog_header"));
		Optional<String> result = dialog.showAndWait();
		// Default - MOVE TO RESOURCE FILE
		selected = properties.getProperty("default_dialog_lang");
		if(result.isPresent()) selected = result.get();
		System.out.println(selected);
	}
	/**
	 * @return the selected
	 */
	public String getSelected() {
		return selected;
	}
	
}
