package model;

public class SimpleActions implements Actions {
	
	private Turtle turtle;

	public SimpleActions(Turtle turtle) {
		this.turtle = turtle;
	}
	
	// Turtle commands:
	
	@Override
	public double forward(double distance) {
		//double theta = turtle.getDirection();
		double theta = turtle.getImage().getRotate();
		System.out.println("The current direction is = " + theta);
		turtle.setX(turtle.getX() + distance * Math.sin(Math.toRadians(theta)));
		turtle.setY(turtle.getY() - distance * Math.cos(Math.toRadians(theta)));
		return distance;
	}

	@Override
	public double backward(double distance) {	
		distance = -1 * distance;
		return -1 * forward(distance);
	}

	@Override
	public double left(double degree) {
		degree = -1 * degree;
		return -1 * right(degree);
	}

	@Override
	public double right(double degree) {
		turtle.rotate(degree);
		return degree;
	}

	@Override
	public double setHeading(double degree) {
		double theta = turtle.getDirection();
		theta = degree - theta;
		return right(theta);
	}

	@Override
	public double setTowards(double x, double y) {
		double delta_x = x - turtle.getX();
		double delta_y = y - turtle.getY();
		
		if (delta_x == 0) {
			if (delta_y > 0) {
				return setHeading(180);
			}
			else {
				return setHeading(0);
			}
		}
		
		if (delta_y == 0) {
			if (delta_x < 0) {
				return setHeading(-90);
			}
			else {
				return setHeading(90);
			}
		}
		
		double angle_rad = Math.atan(delta_x / delta_y);
		double angle_deg = Math.toDegrees(angle_rad);
		
		if (delta_y < 0) {
			return setHeading(-1 * angle_deg);
		}
		if (delta_x < 0) {
			return setHeading(angle_deg + 180);
		}
		else {
			return setHeading(180 - angle_deg);
		}
		
	}

	@Override
	public double setPosition(double x, double y) {
		turtle.setX(x);
		turtle.setY(y);
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public int penDown() {
		turtle.setPenDown();
		return 1;
	}

	@Override
	public int penUp() {
		turtle.setPenUp();
		return 0;
	}

	@Override
	public int showTurtle() {
		turtle.setVisible();
		return 1;
	}

	@Override
	public int hideTurtle() {
		turtle.setInvisible();
		return 0;
	}

	@Override
	public double home() {
		return setPosition(0,0);
	}

	@Override
	public double clearScreen() {
		// here need to add code to erase turtle's trails
		return home();
	}
	
	// Turtle Queries:

	@Override
	public double xCoordinate() {
		return turtle.getX();
	}

	@Override
	public double yCoordinate() {
		return turtle.getY();
	}

	@Override
	public double heading() {
		return turtle.getDirection();
	}

	@Override
	public int isPenDown() {
		return turtle.getIsPenDown();
	}

	@Override
	public int isShowing() {
		return turtle.getIsVisible();
	}
	
	// Display commands:

	@Override
	public double setBackground() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double setPenColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPenSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setShape() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPalette() {
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
	public double id() {
		// TODO Auto-generated method stub
		return 0;
	}

}

