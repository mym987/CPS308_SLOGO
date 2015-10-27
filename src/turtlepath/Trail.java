package turtlepath;

import java.io.Serializable;
import java.util.LinkedList;
import gui.init.colorpicker.ColorChangeInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Turtle;
/**
 * @author Susan Lang (sml59)
 */
public class Trail extends Canvas implements ColorChangeInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060832315824592272L;
	private GraphicsContext context = getGraphicsContext2D();
	private LinkedList<Moment> history = new LinkedList<Moment>();
	private static Color penColor = Color.BLACK;
	private static String penColorString = penColor.toString();
	/**
	 * @return the penColorString
	 */
	public static String getPenColorString() {
		return penColorString;
	}

	/**
	 * @param penColorString the penColorString to set
	 */
	public static void setPenColorString(String penColorString) {
		penColor = Color.valueOf(penColorString);
	}

	private Turtle turtle;
//  This looks terrible:
//	private ChangeListener<? super Number> listener = new ChangeListener<Number>() {
//        @Override
//        public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
//           System.out.println(" oldVal " + oldVal + " newVal " + newVal);
//           update();
//        }
//	};
	
	public Trail(Turtle t) {
		turtle = t;
		//turtle.move.addListener(listener); -> This looks terrible
		turtle.move.addListener((ob,oV,nV)->update());  //This looks fantastic
		update();
	}
	
	public void update() {
		history.add(new Moment(turtle.getX(), turtle.getY(), turtle.isPenDown(), penColor));
		draw();
	}
	
	public void draw() {
		context.clearRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 1; i < history.size(); i++) {
			Moment current = history.get(i);
			Moment prev = history.get(i-1);
			context.setStroke(current.getColor());
			if (current.getIsPenDown()) {
				context.strokeLine(prev.getX() + turtle.screenWidth.get()/2, prev.getY() + turtle.screenHeight.get()/2, current.getX() + turtle.screenWidth.get()/2, current.getY() + turtle.screenHeight.get()/2);
			}
		}	
	}

	@Override
	public void changeColor(Color color) {
		penColor = color;
	}
}
