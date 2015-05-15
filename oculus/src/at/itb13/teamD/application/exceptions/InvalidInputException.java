package at.itb13.teamD.application.exceptions;

/**
 * The input parameter is null or has another wrong value.
 *
 * @author Florin Metzler
 * @since 08.04.2015
 */
public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidInputException() { }
	
	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException(Throwable cause) {
		super(cause);
	}
	
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}
}
