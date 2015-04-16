package at.itb13.oculus.application.calendar;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Patient;

/**
 * TODO
 *
 * @author Florin Metzler
 * @since 09.04.2015
 */
public class CalendarController {
	private Calendar _calendar;
	
	public CalendarController(Calendar calendar) {
		_calendar = calendar;
	}
	
	public Calendar getCalendar() {	// TODO: sichtbarkeit einschränken, wenn möglich
		return _calendar;
	}
	
	/**
	 * Loads a list of CalendarEvent in a chosen timespan.
	 * 
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of CalendarEvent.
	 * @throws InvalidInputException when the startDate is bigger then the endDate.
	 */
	public List<CalendarEvent> getCalendarEventsInTimespan(LocalDateTime startDate, LocalDateTime endDate) throws InvalidInputException {
		List<CalendarEvent> calendarEvents = new LinkedList<>();
		if(startDate.isBefore(endDate)){
			calendarEvents = _calendar.getCalendarEventsInTimespan(startDate, endDate);			
			return calendarEvents;
		}else{
			throw new InvalidInputException();
		}
	}
	
	/**
	 * TODO: @Karin: Insert description! eg. mention that makePersitent is used. Author + Date not needed for methods.
	 * 
	 * @param c CalendarEvent, inserted to Patient p
	 * @param p Patient, inserted to Set<CalendarEvents>
	 */
	public void connectCalendarEventwithPatient (CalendarEvent c, Patient p){
		Set<CalendarEvent> cals= new HashSet<CalendarEvent>();
		boolean issuccessful;
		c.setPatient(p);
		issuccessful = cals.add(c);
		if (issuccessful) {
			p.setCalendarevents(cals);
		}
		// FIXME makePersistent missing?
	}
}
