package at.itb13.oculustests.exceptioncatcher;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;

/**
 * With the static method {@link #assertThrown(ExceptionThrower)} one can test if an
 * exception was thrown and then can inspect the returned value with the provided 
 * methods.
 * Examples: {@link at.itb13.oculustests.application.patient.NewAppointment_UnitTests}
 * <br>
 * Modified version of http://blog.codeleak.pl/2014/07/junit-testing-exception-with-java-8-and-lambda-expressions.html
 * 
 * @author Daniel Scheffknecht
 * @date 30 May 2015
 */
public class ThrowableAssertion {
	private final Throwable _caught;
	
	public ThrowableAssertion(Throwable caught) {
		_caught = caught;
	}
	
	public static ThrowableAssertion assertThrown(ExceptionThrower exceptionThrower) {
		try {
			exceptionThrower.throwException();
		} catch (Throwable caught) {
			return new ThrowableAssertion(caught);
		}
		throw new ExceptionNotThrownAssertionError();
	}
	
	@SuppressWarnings("unchecked")
	public ThrowableAssertion isInstanceOf(Class<? extends Throwable> exceptionClass) {
		Assert.assertThat(_caught, isA((Class<Throwable>)exceptionClass));
		return this;
	}
	
	public ThrowableAssertion hasMessage(String expectedMessage) {
		Assert.assertThat(_caught.getMessage(), equalTo(expectedMessage));
		return this;
	}
	
	public ThrowableAssertion hasNoCause() {
		Assert.assertThat(_caught.getCause(), is(nullValue()));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public ThrowableAssertion hasCauseInstanceOf(Class<? extends Throwable> exceptionClass) {
		Assert.assertThat(_caught.getCause(), is(notNullValue()));
		Assert.assertThat(_caught.getCause(), isA((Class<Throwable>) exceptionClass));
		return this;
	}
}
