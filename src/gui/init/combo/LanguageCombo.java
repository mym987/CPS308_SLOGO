package gui.init.combo;

import javafx.scene.control.ComboBox;

public class LanguageCombo extends ComboBox<Object>{
	public LanguageCombo(){
		addOptions();
	}
	public ComboBox<Object> addOptions(){
		this.getItems().addAll("Chinese",
								"English",
								"French",
								"German",
								"Italian",
								"Portuguese",
								"Russian",
								"Spanish"
								);
								
		return this;
	}
}
