package at.itb13.teamD.domain.interfaces;

import java.time.LocalDateTime;

/**
 * This Interface defines the required methodes of the CalendarEvent class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface ICalendarEvent {
	
	public abstract ICalendar getCalendar();
	
	public abstract IEventType getEventType();
	
	public abstract IPatient getPatient();
	
	public abstract LocalDateTime getEventStart();
	
	public abstract LocalDateTime getEventEnd();
	
	public abstract String getDescription();
	
	public abstract String getPatientName();
	
	public abstract boolean isOpen();	
	
	/**
	 * Checks if the CalendarEvent is in a timespan but also if the CalendarEvent starts befor timespan as long the end date
	 * is in timespan, or the CalendarEvent ends after timespan as long the start date is in timespan.
	 * 
	 * @param startDate the strat Date of the timespan.
	 * @param endDate the end Date of the timespan.
	 * @return true if the CalendarEvent is in the timespan, starts befor timespan or ends after timespan and false if not.
	 */
	public abstract boolean isPartInTimespan(LocalDateTime startDate, LocalDateTime endDate);
	
	/**
	 * Checks if the CalendarEvent is in a timespan.
	 * 
	 * @param startDate the strat Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return true if the CalendarEvent is in the timespan and false if not.
	 */
	public abstract boolean isInTimespan(LocalDateTime startDate, LocalDateTime endDate);
}
