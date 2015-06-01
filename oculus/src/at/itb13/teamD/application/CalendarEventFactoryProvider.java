package at.itb13.teamD.application;

import at.itb13.teamD.domain.interfaces.ICalendarEventFactory;

/**
 * This class provides the implemented CalendarEventFactory. This provider class
 * MUST be initialized with concrete implementation of the CalendarEventFactory.
 *
 * @author Florin Metzler
 * @since 15.05.2015
 */
public class CalendarEventFactoryProvider {

	private static ICalendarEventFactory _factory;

	private CalendarEventFactoryProvider() {
	}

	/**
	 * Provides the set CalendarEventFactory
	 * @return concrete implementation of CalendarEventFactory. 
	 * The CalendarEventFactory must be set during startup of Oculus.
	 */
	public static ICalendarEventFactory getCalendarEventFactory() {
		return _factory;
	}

	/**
	 * 
	 * @param facade
	 * The CalendarEventFactory MUST be initialised during Oculus
	 * startup. Use the concrete implementation of the
	 * CalendarEventFactory as parameter.
	 */
	public static void setCalendarEventFactory(ICalendarEventFactory facade) {
		_factory = facade;
	}
}
