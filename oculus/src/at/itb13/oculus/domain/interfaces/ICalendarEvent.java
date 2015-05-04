package at.itb13.oculus.domain.interfaces;

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
}
