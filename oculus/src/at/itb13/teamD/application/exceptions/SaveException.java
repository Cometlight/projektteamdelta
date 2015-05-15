package at.itb13.teamD.application.exceptions;

/**
 * There was a problem to save the object in the database.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public class SaveException extends Exception{
	private static final long serialVersionUID = 1L;

	public SaveException() { }
	
	public SaveException(String message) {
		super(message);
	}
	
	public SaveException(Throwable cause) {
		super(cause);
	}
	
	public SaveException(String message, Throwable cause) {
		super(message, cause);
	}
}
