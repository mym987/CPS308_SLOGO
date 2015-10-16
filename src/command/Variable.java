package command;

public class Variable implements Command {
	
	private final String myName;
	
	private double myValue;
	
	public Variable(String name){
		this(name,0);
	}
	
	public Variable(String name,double value){
		myName = name;
		myValue = value;
	}        
	
	public void setValue(double value){
		myValue = value;
	}

	@Override
	public double evaluate(Command... args) {
		return myValue;
	}
	
	@Override
	public String toString() {
		return myName;
	}

}
