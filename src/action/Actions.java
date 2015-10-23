package action;

import java.util.Set;

public interface Actions {
	// ---------------------------------
	//
	// Turtle Commands
	//
	// ---------------------------------
	public double forward(double distance);

	public double backward(double distance);

	public double left(double degree);

	public double right(double degree);

	public double setHeading(double degree);

	public double setTowards(double x, double y);

	public double setPosition(double x, double y);

	public int penDown();

	public int penUp();

	public int showTurtle();

	public int hideTurtle();

	public double home();

	public double clearScreen();

	// ---------------------------------
	//
	// Turtle Queries
	//
	// ---------------------------------
	public double xCoordinate();

	public double yCoordinate();

	public double heading();

	public int isPenDown();

	public int isShowing();

	// ---------------------------------
	//
	// Display Commands
	//
	// ---------------------------------

	public double setBackground(double index);

	public double setPenColor(double index);

	public double setPenSize(double pixels);

	public double setShape(double index);

	public double setPalette(double index, double r, double g, double b);

	public double getPenColor();

	public double getShape();

	public double stamp();

	public double clearStamp();

	// ---------------------------------
	//
	// Multiple Turtle Commands
	//
	// ---------------------------------
	/**
	 * ID values typically start at 1 and increase by 1 with each new turtle
	 * created note, there is technically only one "active turtle" at any given
	 * time since each command is run once for each active turtle, i.e., this
	 * value can always be used to identify the current turtle running the
	 * command
	 * 
	 * @return current active turtle's ID number
	 */
	public int id();

	/**
	 * @return number of turtles created so far (i.e., the ID of the last
	 *         turtle)
	 */
	public int turtles();

	/**
	 * sets the index of turtles that will follow commands hereafter note, if
	 * turtle has not previously existed, it is created and placed at the home
	 * location note, if more than one turtle is active, commands run return
	 * value associated with the last active turtle
	 * 
	 * @param activeTurtles
	 */
	public void setFollowers(Set<Integer> activeTurtles);

	/**
	 * Get a set of index of turtles that are following the commands
	 * !!!Important: return an unmodifiable version
	 * 
	 * @return a set of integers representing the indices
	 */
	public Set<Integer> getFollowers();
	
	/**
	 * Set the turtle associated with the index to be active
	 * 
	 * @param index
	 * @return true if the index exists, false otherwise
	 */
	public void setActive(int index);
	
}
