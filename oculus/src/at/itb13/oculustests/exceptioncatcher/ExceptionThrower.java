package at.itb13.oculustests.exceptioncatcher;

@FunctionalInterface
public interface ExceptionThrower {
	void throwException() throws Throwable;
}
