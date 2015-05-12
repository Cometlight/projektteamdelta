package at.itb13.oculus.domain.factories;

import java.time.LocalDateTime;
import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarEvent;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.domain.interfaces.IPatient;

/**
 * Includes the methods to create or get an object from the CalendarEvent class.
 *
 * @author Florin Metzler
 * @since 04.05.2015
 */
public class CalendarEventFactory extends AppointmentFactory{
	
	private CalendarEventFactory(){
		_factory = this;
	}
	
	/**
	 * to create an object of the private CalendarEvent Constructor
	 * 
	 * @return an object of the CalendarEvent
	 */
	public ICalendarEvent createCalendarEvent(){
		ICalendarEvent event = CalendarEvent.getInstance();
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
	public ICalendarEvent createCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start, 
										LocalDateTime end, String description, String patientName){
		
		ICalendarEvent event = CalendarEvent.getInstance(calendar, eventType, start, end, description, patientName);
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
	public ICalendarEvent createCalendarEvent(ICalendar calendar, IEventType eventType, 
										LocalDateTime start, LocalDateTime end, String description, IPatient patient){
		
		ICalendarEvent event = CalendarEvent.getInstance(calendar, eventType, start, end, 
												description, patient);
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
	public ICalendarEvent getCalendarEvent(Calendar calendar, int calendarEventId){
		return calendar.getCalendarEventById(calendarEventId);
	}
	
	
}