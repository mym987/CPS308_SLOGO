package gui.init.dialog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;

public class LanguageDialog extends ChoiceDialog<String> {
	public String selected;
	public LanguageDialog(){
		String[] arrayData = {"English",
							  "Chinese",
							  "French",
							  "German",
							  "Italian",
							  "Portuguese",
							  "Russian",
							  "Spanish"
		 };
		
		List<String> dialogData = Arrays.asList(arrayData);
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(dialogData.get(0), dialogData);
		// MOVE TO RESOURCE FILE
		dialog.setTitle("Welcome to SLogo!");
		dialog.setHeaderText("Choose your language:");
		Optional<String> result = dialog.showAndWait();
		// Default - MOVE TO RESOURCE FILE
		selected = "English";
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
