package gui.init.canvas;

import java.io.Serializable;

import gui.init.colorpicker.ColorChangeInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TurtleCanvas extends Canvas implements ColorChangeInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3581988817971544489L;
	private GraphicsContext backgroundContext = getGraphicsContext2D();
	private String backgroundColor;
	/**
	 * @return the backgroundColor
	 */
	public String getBackgroundColor() {
		backgroundColor = backgroundContext.getFill().toString();
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
		changeColor(Color.valueOf(backgroundColor));
	}

	public TurtleCanvas(){
		// For resizing extension.
//		widthProperty().addListener(evt -> draw());
//		heightProperty().addListener(evt -> draw());
//		draw();
		}
	
	// For resizing extension
//	 private void draw() {
//		             double width = getWidth();
//		             double height = getHeight();
//		             // DEFAULT SETTINGS
//		             backgroundContext.clearRect(0, 0, width, height);
//		             backgroundContext.setStroke(Color.RED);
//		             backgroundContext.strokeLine(0, 0, width, height);
//		             backgroundContext.strokeLine(0, height, width, 0);
//		    }

	@Override
	public void changeColor(Color color) {
		// TODO Auto-generated method stub
        backgroundContext.clearRect(0, 0, this.getWidth(), this.getHeight());
		backgroundContext.setFill(color);
		backgroundContext.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}