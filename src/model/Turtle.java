package model;

import gui.init.canvas.IReset;
import gui.turtle.IChangeImage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle implements IChangeImage, IReset{
	
	public final IntegerProperty move = new SimpleIntegerProperty();
	public final DoubleProperty screenWidth = new SimpleDoubleProperty();
	public final DoubleProperty screenHeight = new SimpleDoubleProperty();
	private ImageView image;
	private Image defaultImage = new Image(getClass().getClassLoader().getResourceAsStream("turtle.png"));
	private double x, y;
	private double direction;
	private BooleanProperty isPenDown;
	private BooleanProperty isVisible;
	
	public Turtle() {
		image = new ImageView(defaultImage);
		image.setFitHeight(50);
		image.setPreserveRatio(true);
		x = 0;
		y = 0;
		move.set(0);
		image.setX(x + screenWidth.get()/2 - image.getBoundsInLocal().getWidth()/2);
		image.setY(y + screenHeight.get()/2 - image.getBoundsInLocal().getHeight()/2);
		direction = 0;
		isPenDown = new SimpleBooleanProperty(true);
		isVisible = new SimpleBooleanProperty(true);
		image.visibleProperty().bind(isVisible);
	}
	
	public Turtle(double x, double y) {
		this();
		setX(x);
		setY(y);
	}
	
	public Turtle(double x, double y, Image img) {
		this(x, y);
		setImage(img);
	}

	// getter methods:
	public ImageView getImage() {
		return image;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public boolean isPenDown() {
		return isPenDown.get();
	}
	
	public boolean isVisible() {
		return isVisible.get();
	}
	
	// setter methods:
	@Override
	public void setImage(Image img) {
		this.image.setImage(img);
	}
	
	public void setImage(ImageView image) {
		this.image = image;
	}

	public void setImage(String s) {
		ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.png")));
		this.image = image;
	}
	
	public void setX(double x) {
		this.x = x;
		image.setX(x + screenWidth.get()/2 - image.getBoundsInLocal().getWidth()/2);
	}
	
	public void setY(double y) {
		this.y = y;
		image.setY(y + screenHeight.get()/2 - image.getBoundsInLocal().getHeight()/2);
	}
	
	public void rotate(double angle) {
		image.setRotate(image.getRotate() + angle);
		direction = image.getRotate();
	}
	
	public void setPenDown() {
		isPenDown.set(true);
	}
	
	public void setPenUp() {
		isPenDown.set(false);
	}
	
	public void setVisible() {
		isVisible.set(true);
	}
	
	public void setInvisible() {
		isVisible.set(false);
	}

	public void forward(double distance) {
		double theta = getDirection();
		double delta_x = distance * Math.sin(Math.toRadians(theta));
		double delta_y = -distance * Math.cos(Math.toRadians(theta));
		// update x coordinate
		if (delta_x >= 0) {
			if (getX() + delta_x <= screenWidth.get() / 2) {
				setX(getX() + delta_x);
			} else {
				setX((getX() + delta_x) % screenWidth.get() - screenWidth.get());
			}
		} else {
			if (getX() + delta_x >= -screenWidth.get() / 2) {
				setX(getX() + delta_x);
			} else {
				setX((getX() + delta_x) % screenWidth.get() + screenWidth.get());
			}
		}
		// update y coordinate
		if (delta_y >= 0) {
			if (getY() + delta_y <= screenHeight.get() / 2) {
				setY(getY() + delta_y);
			} else {
				setY((getY() + delta_y) % screenHeight.get() - screenHeight.get());
			}
		} else {
			if (getY() + delta_y >= -screenHeight.get() / 2) {
				setY(getY() + delta_y);
			} else {
				setY((getY() + delta_y) % screenHeight.get() + screenHeight.get());
			}
		}
		move.set(move.get() + 1);
	}
	
	@Override
	public void reset() {
		boolean save = isPenDown.get();
		setPenUp();
		setX(0);
		setY(0);
		rotate(-this.getDirection());
		if (save) setPenDown();
	}
}
