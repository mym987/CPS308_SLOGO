package model;

import gui.init.canvas.IReset;
import gui.turtle.IChangeImage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle implements IChangeImage, IReset{
	
	public final IntegerProperty move = new SimpleIntegerProperty();
	public final DoubleProperty initX = new SimpleDoubleProperty();
	public final DoubleProperty initY = new SimpleDoubleProperty();
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
		x = initX.get();
		y = initY.get();
		move.set(0);
		image.setX(x + initX.get() - image.getBoundsInLocal().getWidth()/2);
		image.setY(y + initY.get() - image.getBoundsInLocal().getHeight()/2);
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
		image.setX(x + initX.get() - image.getBoundsInLocal().getWidth()/2);
	}
	
	public void setY(double y) {
		this.y = y;
		image.setY(y + initY.get() - image.getBoundsInLocal().getHeight()/2);
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
