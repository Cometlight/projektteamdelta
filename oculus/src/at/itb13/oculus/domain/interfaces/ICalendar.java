package at.itb13.oculus.domain.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import at.itb13.oculus.domain.CalendarWorkingHours.WeekDayKey;

/**
 * This Interface defines the required methodes of the Calendar class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface ICalendar {

	/**
	 * Creates a list of Calendar Event for a chosen timespan.
	 * 
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of CalendarEvent.
	 */
	public abstract List<? extends ICalendarEvent> getCalendarEventsForTimespan(LocalDateTime start, 
																	 LocalDateTime end);
	/**
	 * Returns the Working Hours of a chosen day of the week.
	 * 
	 * @param weekDay is an Enum of the days of the week.
	 * @return A IWorkingHours.
	 */
	public abstract IWorkingHours getWorkingHoursOfWeekDay(WeekDayKey weekDay);
	
	public abstract String getTitle();
}
