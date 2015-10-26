package action;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Turtle;

public class TestActions implements Actions {

	private Turtle myTurtle;
	private IntegerProperty myId;
	private List<Turtle> myTurtles;
	private Set<Integer> myActionFollowers;

	public TestActions(List<Turtle> turtles) {
		myTurtles = turtles;
		if(turtles.size()==0)
			turtles.add(new Turtle());
		myActionFollowers = new HashSet<>();
		myId = new SimpleIntegerProperty(1);
		myId.addListener((arg, oldV, newV) -> {
			myTurtle = myTurtles.get(newV.intValue());
			
		});
		myId.set(0);
		myActionFollowers.add(0);
	}
	private void p(String s){
		System.out.println(s);
	}

	// Turtle commands:

	@Override
	public double forward(double distance) {
		p(myId.get()+": Forward:" +distance);
		return distance;
	}

	@Override
	public double backward(double distance) {
		p(myId.get()+": Backward:" +distance);
		return distance;
	}

	@Override
	public double left(double degree) {
		p(myId.get()+": left:" +degree);
		return degree;
	}

	@Override
	public double right(double degree) {
		p(myId.get()+": right:" +degree);
		return degree;
	}

	@Override
	public double setHeading(double degree) {
		p(myId.get()+": heading:" +degree);
		return degree;
	}

	@Override
	public double setTowards(double x, double y) {
		p(myId.get()+": Tow: "+x+","+y);
		return 0;

	}

	@Override
	public double setPosition(double x, double y) {
		p(myId.get()+": Pos: "+x+","+y);
		return 0;
	}

	@Override
	public int penDown() {
		p(myId.get()+": Pendown");
		return 1;
	}

	@Override
	public int penUp() {
		p(myId.get()+": Penup");
		return 0;
	}

	@Override
	public int showTurtle() {
		p(myId.get()+": Show");
		return 1;
	}

	@Override
	public int hideTurtle() {
		p(myId.get()+": Hide");
		return 0;
	}

	@Override
	public double home() {
		p(myId.get()+": Home");
		return 0;
	}

	@Override
	public double clearScreen() {
		p(myId.get()+": clear");
		return 0;
	}

	// Turtle Queries:

	@Override
	public double xCoordinate() {
		p("xCoordinate");
		return 123;
	}

	@Override
	public double yCoordinate() {
		p("yCoordinate");
		return 321;
	}

	@Override
	public double heading() {
		p("heading");
		return 50;
	}

	@Override
	public int isPenDown() {
		p("isPendown");
		return myTurtle.isPenDown()?1:0;
	}

	@Override
	public int isShowing() {
		p("isshowing");
		return myTurtle.isVisible()?1:0;
	}

	// Display commands:

	@Override
	public double setBackground(double index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPenColor(double index) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
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
