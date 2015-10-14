package model;

public interface Actions {
	//---------------------------------
	//
	//         Turtle Commands
	//
	//---------------------------------
	public double forward(double distance);
	
	public double backward(double distance);
	
	public double left(double degree);
	
	public double right(double degree);
	
	public double setHeading(double degree);
	
	public double setTowards(double x,double y);
	
	public double setPosition(double x,double y);
	
	public double penDown();
	
	public double penUp();
	
	public double showTurtle();
	
	public double hideTurtle();
	
	public double home();
	
	public double clearScreen();
	
	//---------------------------------
	//
	//         Turtle Queries
	//
	//---------------------------------
	public double xCoordinate();
	
	public double yCoordinate();
	
	public double heading();
	
	public double isPenDown();
	
	public double isShowing();
	
	//---------------------------------
	//
	//         Display Commands
	//
	//---------------------------------
	
	public double setBackground();
	
	public double setPenColor();
	
	public double setPenSize();
	
	public double setShape();
	
	public double setPalette();
	
	public double getPenColor();
	
	public double getShape();
	
	public double stamp();
	
	public double clearStamp();
	
	//---------------------------------
	//
	//    Multiple Turtle Commands
	//
	//---------------------------------
	
	public double id();
}
