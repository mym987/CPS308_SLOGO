package action;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import gui.init.canvas.TurtleCanvas;

import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import model.Turtle;
import turtlepath.Trail;

public class SimpleActions implements Actions {

	private Turtle myTurtle;
	private IntegerProperty myId;
	private List<Turtle> myTurtles;
	private Set<Integer> myActionFollowers;
	private TurtleCanvas turtleCanvas;

	public SimpleActions(List<Turtle> turtles, TurtleCanvas canvas) {
		myTurtles = turtles;
		if(turtles.size()==0)
			turtles.add(new Turtle());
		myActionFollowers = new HashSet<>();
		turtleCanvas = canvas;
		myId = new SimpleIntegerProperty(1);
		myId.addListener((arg, oldV, newV) -> {
			myTurtle = myTurtles.get(newV.intValue());
			
		});
		myId.set(0);
		myActionFollowers.add(0);
	}

	// Turtle commands:

	@Override
	public double forward(double distance) {
		myTurtle.forward(distance);
		return distance;
	}

	@Override
	public double backward(double distance) {
		return -forward(-distance);
	}

	@Override
	public double left(double degree) {
		return -right(-degree);
	}

	@Override
	public double right(double degree) {
		myTurtle.rotate(degree);
		return degree;
	}

	@Override
	public double setHeading(double degree) {
		double theta = myTurtle.getDirection();
		theta = degree - theta;
		return right(theta);
	}

	@Override
	public double setTowards(double x, double y) {
		double delta_x = x - myTurtle.getX();
		double delta_y = y - myTurtle.getY();

		if (delta_x == 0) {
			if (delta_y > 0) {
				return setHeading(180);
			} else {
				return setHeading(0);
			}
		}

		if (delta_y == 0) {
			if (delta_x < 0) {
				return setHeading(-90);
			} else {
				return setHeading(90);
			}
		}

		double angle_rad = Math.atan(delta_x / delta_y);
		double angle_deg = Math.toDegrees(angle_rad);

		if (delta_y < 0) {
			return setHeading(-angle_deg);
		}
		if (delta_x < 0) {
			return setHeading(-angle_deg - 180);
		} else {
			return setHeading(180 - angle_deg);
		}

	}

	@Override
	public double setPosition(double x, double y) {
		myTurtle.setX(x);
		myTurtle.setY(y);
		myTurtle.move.set(myTurtle.move.get() + 1);

		return Math.sqrt(x * x + y * y);
	}

	@Override
	public int penDown() {
		myTurtle.setPenDown();
		return 1;
	}

	@Override
	public int penUp() {
		myTurtle.setPenUp();
		return 0;
	}

	@Override
	public int showTurtle() {
		myTurtle.setVisible();
		return 1;
	}

	@Override
	public int hideTurtle() {
		myTurtle.setInvisible();
		return 0;
	}

	@Override
	public double home() {
		return setPosition(0, 0);
	}

	@Override
	public double clearScreen() {
		// here need to add code to erase turtle's trails
		return home();
	}

	// Turtle Queries:

	@Override
	public double xCoordinate() {
		return myTurtle.getX();
	}

	@Override
	public double yCoordinate() {
		return myTurtle.getY();
	}

	@Override
	public double heading() {
		return myTurtle.getDirection();
	}

	@Override
	public int isPenDown() {
		return myTurtle.isPenDown()?1:0;
	}

	@Override
	public int isShowing() {
		return myTurtle.isVisible()?1:0;
	}

	// Display commands:

	@Override
	public double setBackground(double index) {
		// TODO Auto-generated method stub
		String backgroundColor = Integer.toString((int) index,16);
		turtleCanvas.setBackgroundColor(backgroundColor);
		return 0;
	}

	@Override
	public double setPenColor(double index) {
		// TODO Auto-generated method stub
		String penColor = Integer.toString((int) index,16);
		Trail.setPenColorString(penColor);
		return 0;
	}

	@Override
	public double setPenSize(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setShape(double index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPalette(double index, double r, double g, double b) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public double getPenColor() {
		return Double.valueOf(Trail.getPenColorString());
	}

	@Override
	public double getShape() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double stamp() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public double clearStamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int id() {
		return myId.get()+1;
	}

	@Override
	public int turtles() {
		return myTurtles.size();
	}

	@Override
	public void setFollowers(Set<Integer> activeTurtles) {
		int max = Collections.max(activeTurtles) - 1;
		if (myTurtles.size() < max)
			createTurtles(max);
		myActionFollowers.clear();
		myActionFollowers = activeTurtles.stream()
				.filter(i -> i > 0)
				.map(i -> i - 1)
				.collect(Collectors.toSet());
		setActive(max+1);
	}

	@Override
	public Set<Integer> getFollowers() {
		return myActionFollowers.stream()
				.map(i -> i + 1)
				.collect(Collectors.toSet());
	}

	@Override
	public void setActive(int index) {
		index--;
		if (index < 0) {
			index = 0;
		} else if (myTurtles.size() <= index) {
			createTurtles(index);
		}
		myId.set(index);
	}

	private void createTurtles(int size) {
		for (int i = myTurtles.size(); i <= size; i++)
			myTurtles.add(new Turtle());
	}
}
