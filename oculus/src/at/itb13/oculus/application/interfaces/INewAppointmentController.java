package at.itb13.oculus.application.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarEvent;
import at.itb13.oculus.domain.interfaces.IPatient;

/**
 * This Interface defines the used methodes of the NewAppointmentController.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface INewAppointmentController {
	
	/**
	 * Creates a new appointment in a chosen timespan for the wanted calendar and patient.
	 * 
	 * @param calendar for this calendar the appointment is made.
	 * @param start the start Date of the timespan. (inclusive)
	 * @param end the end Date of the timespan. (inclusive)
	 * @param description includes the reason for the appointment.
	 * @param patient is the person who refers to the appointment.
	 * @throws SaveException is throwen when an error occured while saving the new appointment.
	 */
	public abstract void newCalendarEvent(ICalendar calendar, LocalDateTime start, 
										 LocalDateTime end, String description, 
										 IPatient patient) throws SaveException;
	
	/**
	 * Creates a new appointment in a chosen timespan for the wanted calendar and patient.
	 * 
	 * @param calendar for this calendar the appointment is made.
	 * @param start the start Date of the timespan. (inclusive)
	 * @param end the end Date of the timespan. (inclusive)
	 * @param description includes the reason for the appointment.
	 * @param patient is the person who refers to the appointment.
	 * @throws SaveException is throwen when an error occured while saving the new appointment.
	 */
	public abstract void newCalendarEvent(ICalendar calendar, LocalDateTime start, 
			 							 LocalDateTime end, String description, 
			 							 String patient) throws SaveException;
	
	/**
	 * Creates a list of ICalendarEvent in a chosen timespan and for a chosen calendar.
	 * 
	 * @param calendar for this calendar the search for ICalendarEvent is made.
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of ICalendarEvent.
	 */
	public abstract List<ICalendarEvent> getCalendarEventsInTimespan(ICalendar calendar, 
										 LocalDateTime start, LocalDateTime end);
	
	/**
	 * Creates a list of all ICalendar.
	 * 
	 * @return A list of all ICalendar.
	 */
	public abstract List<ICalendar> getAllCalendars();

}
