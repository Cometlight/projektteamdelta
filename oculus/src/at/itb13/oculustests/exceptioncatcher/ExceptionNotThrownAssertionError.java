package at.itb13.oculustests.exceptioncatcher;

/**
 * @see ThrowableAssertion
 * 
 * @author Daniel Scheffknecht
 * @date 30 May 2015
 */
public class ExceptionNotThrownAssertionError extends AssertionError {
	private static final long serialVersionUID = 8947212828304114611L;

	public ExceptionNotThrownAssertionError() {
        super("Expected exception was not thrown.");
    }
}
