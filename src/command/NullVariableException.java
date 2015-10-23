package command;
/**
 * An exception that can be thrown during the normal operation of the
 * program.
 * 
 * This exception is thrown when the variable is not initialized.
 * 
 * @author Mike Ma (ym67)
 *
 */
public class NullVariableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4504245214189735758L;
	
	public NullVariableException(String msg){
		super(msg);
	}

}
