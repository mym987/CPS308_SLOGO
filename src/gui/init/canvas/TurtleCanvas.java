package gui.init.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TurtleCanvas extends Canvas {
	private GraphicsContext backgroundContext;
	public TurtleCanvas(){
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
		}
	
	 private void draw() {
		             double width = getWidth();
		             double height = getHeight();
		             GraphicsContext gc = getGraphicsContext2D();
		             gc.clearRect(0, 0, width, height);
		             gc.setStroke(Color.RED);
	
		             gc.strokeLine(0, 0, width, height);
		 
		             gc.strokeLine(0, height, width, 0);
		    }

	
	
	public void setBackgroundColor(Color c){
		backgroundContext.setFill(c);
		backgroundContext.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
