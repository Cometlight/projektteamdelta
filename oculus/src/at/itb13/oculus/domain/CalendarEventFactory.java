package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Includes the methods to create or get an object from the CalendarEvent class.
 *
 * @author Florin Metzler
 * @since 04.05.2015
 */
public class CalendarEventFactory {
	
	/**
	 * to create an object of the private CalendarEvent Constructor
	 * 
	 * @return an object of the CalendarEvent
	 */
	public CalendarEvent createCalendarEvent(){
		CalendarEvent event = CalendarEvent.getInstance();
		return event;
	}
	
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
	public CalendarEvent createCalendarEvent(Calendar calendar, EventType eventType, LocalDateTime start, 
										LocalDateTime end, boolean isOpen, String patientName){
		
		CalendarEvent event = CalendarEvent.getInstance(calendar, eventType, start, end, isOpen, patientName);
		return event;
	}
	
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
	public CalendarEvent createCalendarEvent(Calendar calendar, EventType eventType, Patient patient, 
										LocalDateTime start, LocalDateTime end, String description, 
										boolean isOpen){
		
		CalendarEvent event = CalendarEvent.getInstance(calendar, eventType, patient, start, end, 
												description, isOpen);
		return event;
	}
	
	/**
	 * get a set of all CallendarEvent for a chosen calendar.
	 * 
	 * @param calendar is the chosen calendar.
	 * @return a set of CallendarEvents.
	 */
	public Set<CalendarEvent> getAllCalendarEvent(Calendar calendar){
		return calendar.getCalendarEvents();
	}
	
	/**
	 * get a CallendarEvent by ID.
	 * 
	 * @param calendar is the chosen calendar.
	 * @param calendarEventId the ID of the wanted CalendarEvent.
	 * @return a CallendarEvent
	 */
	public CalendarEvent getCalendarEvent(Calendar calendar, int calendarEventId){
		return calendar.getCalendarEventById(calendarEventId);
	}
	
	
}
