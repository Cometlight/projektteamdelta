package at.itb13.oculus.application.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.oculus.application.interfaces.INewAppointmentController;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarEvent;
import at.itb13.oculus.domain.interfaces.IPatient;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 03.05.2015
 */
public class NewAppointmentController implements INewAppointmentController {

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
	@Override
	public void newCalendarEvent(ICalendar calendar, LocalDateTime start,
			LocalDateTime end, String description, IPatient patient)
			throws SaveException {
		// TODO Auto-generated method stub
		
	}

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
	@Override
	public void newCalendarEvent(ICalendar calendar, LocalDateTime start,
			LocalDateTime end, String description, String patient)
			throws SaveException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creates a list of ICalendarEvent in a chosen timespan and for a chosen calendar.
	 * 
	 * @param calendar for this calendar the search for ICalendarEvent is made.
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of ICalendarEvent.
	 * @throws InvalidInputException InvalidInputException throws when the end date is before the start date.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ICalendarEvent> getCalendarEventsInTimespan(ICalendar calendar,
			LocalDateTime start, LocalDateTime end) throws InvalidInputException {
		if(start.isBefore(end)){
			return (List<ICalendarEvent>) calendar.getCalendarEventsForTimespan(start, end);			
		}else{
			throw new InvalidInputException();
		}
	}

	/**
	 * Creates a list of all ICalendar.
	 * 
	 * @return A list of all ICalendar.
	 */
	@Override
	public List<ICalendar> getAllCalendars() {
		List<ICalendar> calendar = new ArrayList<>();
		ControllerFacade facade = ControllerFacade.getInstance();
		List<CalendarController> allController = facade.getAllCalendarController();	// TODO: Die andren haben ja gar nicht unseren "CalendarController"; können wir das vielleicht anders lösen?
		for(CalendarController controller : allController ){
			calendar.add((ICalendar) controller.getCalendar());
		}
		return calendar;
	}

}
