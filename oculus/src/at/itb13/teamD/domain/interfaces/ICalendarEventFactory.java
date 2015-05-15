package at.itb13.teamD.domain.interfaces;

import java.time.LocalDateTime;
import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 13.05.2015
 */
public interface ICalendarEventFactory {
	
	/**
	 * to create an object of the private CalendarEvent Constructor
	 * 
	 * @return an object of the CalendarEvent
	 */
	public abstract ICalendarEvent createCalendarEvent();
	
	/**
	 * to create an object of the private CalendarEvent Constructor
	 * 
	 * @param calendar for this calendar the CalendarEvent is made.
	 * @param eventtype the type of the Calendar Event.
	 * @param eventStart the start Date of the CalendarEvent. (inclusive)
	 * @param eventEnd the end Date of the CalendarEvent. (inclusive)
	 * @param isOpen true if the CalendarEvent is not checked as closed.
	 * @param patientName the name of the patient 
	 * @return an object of the CalendarEvent
	 */
	public abstract ICalendarEvent createCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start, 
										LocalDateTime end, String description, String patientName);
	
	/**
	 * to create an object of the private CalendarEvent Constructor
	 * 
	 * @param calendar for this calendar the CalendarEvent is made.
	 * @param eventtype the type of the Calendar Event.
	 * @param patient an object of the Patient class which is referred to the CalendarEvent.
	 * @param eventStart the start Date of the CalendarEvent. (inclusive)
	 * @param eventEnd the end Date of the CalendarEvent. (inclusive)
	 * @param description includes some special notes about the CalendarEvent.
	 * @param isOpen true if the CalendarEvent is not checked as closed. 
	 * @return an object of the CalendarEvent
	 */
	public abstract ICalendarEvent createCalendarEvent(ICalendar calendar, IEventType eventType, 
										LocalDateTime start, LocalDateTime end, String description, IPatient patient);
	
	/**
	 * get a set of all CallendarEvent for a chosen calendar.
	 * 
	 * @param calendar is the chosen calendar.
	 * @return a set of CallendarEvents.
	 */
	public abstract Set<CalendarEvent> getAllCalendarEvent(Calendar calendar);
	
	/**
	 * get a CallendarEvent by ID.
	 * 
	 * @param calendar is the chosen calendar.
	 * @param calendarEventId the ID of the wanted CalendarEvent.
	 * @return a CallendarEvent
	 */
	public abstract ICalendarEvent getCalendarEvent(Calendar calendar, int calendarEventId);
	
}
