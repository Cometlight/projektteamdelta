package at.itb13.oculus.technicalServices.exceptions;

/**
 * Thrown by HibernateUtil in case of failure of database connection.
 * 
 * @author Daniel Scheffknecht
 * @date 25.04.2015
 */
public class NoDatabaseConnectionException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoDatabaseConnectionException() { }
	
	public NoDatabaseConnectionException(String message) {
		super(message);
	}
	
	public NoDatabaseConnectionException(Throwable cause) {
		super(cause);
	}
	
	public NoDatabaseConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
