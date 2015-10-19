package turtlepath;

import javafx.scene.paint.Color;
/**
 * @author Susan Lang (sml59)
 */
public class Moment {
	private double x;
	private double y;
	private int isPenDown;
	private Color color;
	
	public Moment (double setX, double setY, int setPenDown, Color setColor) {
		x = setX;
		y = setY;
		isPenDown = setPenDown;
		color = setColor;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getIsPenDown() {
		return isPenDown;
	}

	public Color getColor() {
		return color;
	}
}
