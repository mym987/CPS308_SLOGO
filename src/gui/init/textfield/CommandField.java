package gui.init.textfield;

import javafx.scene.control.TextField;

public class CommandField extends TextField{
	private TextField commandField = new TextField();
	public CommandField(){
		commandField.setPromptText("Enter your command");
		//onEnter();
	}

//	private void onEnter() {
//		commandField.setOnKeyPressed(new EventHandler<KeyEvent>()
//	    {
//	        @Override
//	        public void handle(KeyEvent ke)
//	        {
//	            if (ke.getCode().equals(KeyCode.ENTER))
//	            {
//	                notifyObservers();
//	            }
//	        }
//	    });
//	}
	
}
