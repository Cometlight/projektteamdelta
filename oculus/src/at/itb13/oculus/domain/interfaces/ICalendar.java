package at.itb13.oculus.domain.interfaces;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

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
	 * @param dayOfWeek is an Enum of the days of the week.
	 * @return A IWorkingHours.
	 */
	public abstract IWorkingHours getWorkingHoursOfWeekDay(DayOfWeek dayOfWeek);
	
	public abstract String getTitle();
	
	public abstract Integer getCalendarId();
}
