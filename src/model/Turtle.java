package model;

import gui.animation.AnimationControl;
import gui.init.canvas.IReset;
import gui.turtle.IChangeImage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Turtle implements IChangeImage, IReset, AnimationControl{
	private final int FRAMES_PER_SECOND = 30;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private double MOVEMENT_TIME = 0.5;
	private double NUMBER_OF_CYCLES;
	public final IntegerProperty move = new SimpleIntegerProperty();
	public final DoubleProperty initX = new SimpleDoubleProperty();
	public final DoubleProperty initY = new SimpleDoubleProperty();
	private ImageView image;
	private Image defaultImage = new Image(getClass().getClassLoader().getResourceAsStream("turtle.png"));
	private double x, y;
	private double direction;
	private BooleanProperty isPenDown;
	private BooleanProperty isVisible;
	private Timeline animation;
	
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
		resetAnimation();
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
		double newX = x;
		double oldX = this.x;
		
		final double delta_x = (newX-oldX)/NUMBER_OF_CYCLES;
		KeyFrame mainFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),e->animationXStep(delta_x));
		createAndPlayTimeline(mainFrame);
	}

	
	public void setY(double y) {
		double newY = y;
		double oldY = this.y;
		final double delta_y = (newY-oldY)/NUMBER_OF_CYCLES;

		KeyFrame mainFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),e->animationYStep(delta_y));
		createAndPlayTimeline(mainFrame);
	}
	/**
	 * @param mainFrame
	 */
	private void createAndPlayTimeline(KeyFrame mainFrame) {
		animation = new Timeline();
		animation.setCycleCount((int)NUMBER_OF_CYCLES);
		animation.getKeyFrames().add(mainFrame);
		animation.play();
	}
	public void animationXStep(double delta_x){
		this.x = this.x+delta_x;
		image.setX(this.x + initX.get() - image.getBoundsInLocal().getWidth()/2);
	}
	
	
	public void animationYStep(double delta_y){
		this.y = this.y + delta_y;
		image.setY(this.y + initY.get() - image.getBoundsInLocal().getWidth()/2);
	}
	

	@Override
	public void turnOffAnimation() {
		NUMBER_OF_CYCLES = 1;
	}

	@Override
	public void resetAnimation() {
		NUMBER_OF_CYCLES = MOVEMENT_TIME*FRAMES_PER_SECOND;
	}

	@Override
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		
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
