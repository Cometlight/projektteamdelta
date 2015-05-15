package at.itb13.teamD.application.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;

/**
 * This Interface defines the used methods of the NewAppointmentController.
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
	public abstract void newCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start, 
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
	public abstract void newCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start, 
			 							 LocalDateTime end, String description, 
			 							 String patient) throws SaveException;
	
	/**
	 * Creates a list of ICalendarEvent in a chosen timespan and for a chosen calendar.
	 * 
	 * @param calendar for this calendar the search for ICalendarEvent is made.
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of ICalendarEvent.
	 * @throws InvalidInputException throws when the end date is before the start date.
	 */
	public abstract List<ICalendarEvent> getCalendarEventsInTimespan(ICalendar calendar, 
										 LocalDateTime start, LocalDateTime end) throws InvalidInputException;
	
	/**
	 * Creates a list of all ICalendar.
	 * 
	 * @return A list of all ICalendar.
	 */
	public abstract List<ICalendar> getAllCalendars();

	public abstract List<IEventType> getAllEventTypes();
	
	/**
	 * calculates if the start and end date of an CalendarEvent is in the given WrokingHours for a specified day.
	 * 
	 * @param calendar the WorkinHours for the chosen calendar
	 * @param start start date of the CalendarEvent
	 * @param end end date of the CalendarEvent
	 * @return true if the CalendarEvent is in the WorkinHours.
	 */
	public abstract boolean isInWorkingHours(ICalendar calendar, LocalDateTime start, LocalDateTime end);	
	
	/**
	 * looks if the date is already taken by another CalendarEvent.
	 * 
	 * @param calendar in which should be searched.
	 * @param start start date of the CalendarEvent.
	 * @param end end date of the CalendarEvent.
	 * @return true if a CalendarEvent is in the timespan.
	 * @throws InvalidInputException
	 */
	public abstract boolean isDateAlreadyTaken(ICalendar calendar, LocalDateTime start, 
											  LocalDateTime end) throws InvalidInputException;
}
