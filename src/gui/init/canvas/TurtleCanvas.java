package gui.init.canvas;

import gui.init.colorpicker.ColorChangeInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TurtleCanvas extends Canvas implements ColorChangeInterface {
	private GraphicsContext backgroundContext = getGraphicsContext2D();;
	public TurtleCanvas(){
		// For resizing extension.
//		widthProperty().addListener(evt -> draw());
//		heightProperty().addListener(evt -> draw());
//		draw();
		changeColor(Color.AQUA);
		}
	
	// For resizing extension
	 private void draw() {
		             double width = getWidth();
		             double height = getHeight();
		             // DEFAULT SETTINGS
		             backgroundContext.clearRect(0, 0, width, height);
		             backgroundContext.setStroke(Color.RED);
		             backgroundContext.strokeLine(0, 0, width, height);
		             backgroundContext.strokeLine(0, height, width, 0);
		    }

	@Override
	public void changeColor(Color color) {
		// TODO Auto-generated method stub
        backgroundContext.clearRect(0, 0, this.getWidth(), this.getHeight());
		backgroundContext.setFill(color);
		backgroundContext.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
