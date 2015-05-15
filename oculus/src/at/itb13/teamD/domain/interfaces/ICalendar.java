package at.itb13.teamD.domain.interfaces;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * This Interface defines the required methods of the Calendar class.
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
	
	/**
	 * Checks if a list of CalendarEvent is in a timespan but also if a CalendarEvent starts befor timespan as long the end date
	 * is in timespan, or a CalendarEvent ends after timespan as long the start date is in timespan.
	 * 
	 * @param startDate the start Date of the timespan.
	 * @param endDate the end Date of the timespan.
	 * @return true when one CalendarEvent of the list is a part of the timespan.
	 */
	public abstract boolean isOneCalendarEventInTimespan(LocalDateTime startDate, LocalDateTime endDate);
	
	public abstract Set<ICalendarEvent> getICalendarEvents();
	
	public abstract void setCalendarEvents(Set<ICalendarEvent> calendarEvents);
	
	/**
	 * if a new CalendarEvent is created this method adds it to the list of all CalendarEvents for a specific calendar.
	 * 
	 * @param newEvent which is to add.
	 */
	public abstract void addCalendarEventToList(ICalendarEvent newEvent);
	
	public abstract ICalendarEvent getCalendarEventById(int calendarEventId);
}
