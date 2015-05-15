package at.itb13.teamD.technicalServices.exceptions;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 11.05.2015
 */
public class PersistenceFacadeException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistenceFacadeException() { }
	
	public PersistenceFacadeException(String message) {
		super(message);
	}
	
	public PersistenceFacadeException(Throwable cause) {
		super(cause);
	}
	
	public PersistenceFacadeException(String message, Throwable cause) {
		super(message, cause);
	}
}
