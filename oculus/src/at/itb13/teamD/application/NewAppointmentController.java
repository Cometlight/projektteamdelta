package at.itb13.teamD.application;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import at.itb13.teamD.domain.factories.CalendarEventFactory;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.ICalendarEventFactory;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamD.domain.interfaces.IWorkingHours;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.oculus.application.interfaces.IPatientSearch;
import at.itb13.oculus.technicalServices.exceptions.PersistenceFacadeException;
import at.itb13.oculus.technicalServices.persistencefacade.IPersistenceFacade;
import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacadeProvider;

/**
 * TODO: provides methodes for the usecase "new appointment"
 *
 * @author Florin Metzler
 * @since 03.05.2015
 */
public class NewAppointmentController implements INewAppointmentController, IPatientSearch{
	
	private ICalendarEventFactory _factory = new CalendarEventFactory();
	
	/**
	 * Creates a new appointment in a chosen timespan for the wanted calendar and patient.
	 * 
	 * @param calendar for this calendar the appointment is made.
	 * @param start the start Date of the timespan. (inclusive)
	 * @param end the end Date of the timespan. (inclusive)
	 * @param description includes the reason for the appointment.
	 * @param patient is the person who refers to the appointment.
	 * @throws SaveException is thrown when an error occured while saving the new appointment.
	 */
	@Override
	public void newCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start,
			LocalDateTime end, String description, IPatient patient)
			throws SaveException {
		ICalendarEvent newEvent = _factory.createCalendarEvent((ICalendar) calendar, (IEventType) eventType, start, end, description, (IPatient) patient);
		IPersistenceFacade facade = PersistenceFacadeProvider.getPersistenceFacade();
		facade.makePersistent(newEvent);
		if(facade.makePersistent(newEvent)){
			return;
		} else {
			throw new SaveException();
		}
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
	public void newCalendarEvent(ICalendar calendar, IEventType eventType, LocalDateTime start,
			LocalDateTime end, String description, String patient)
			throws SaveException {
		ICalendarEvent newEvent = _factory.createCalendarEvent((ICalendar) calendar, (IEventType) eventType, start, end, description, patient);
		Set<ICalendarEvent> set = calendar.getICalendarEvents();
		set.add(newEvent);
		calendar.setCalendarEvents(set);
		IPersistenceFacade facade = PersistenceFacadeProvider.getPersistenceFacade();
		facade.makePersistent(newEvent);
		if(facade.makePersistent(newEvent)){
			return;
		} else {
			throw new SaveException();
		}
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
		ControllerFacade facade = ControllerFacade.getInstance();
		return facade.getAllCalendars();
	}

	/**
	 * returns a list of all EventType.
	 */
	@Override
	public List<IEventType> getAllEventTypes() {
		ControllerFacade facade = ControllerFacade.getInstance();
		return facade.getAllEventTypes();
	}
	
	/**
	 * Loads the patient with the provided social insurance number or name from the database.
	 * 
	 * @param searchValue The patient's social insurance number or name.
	 * @return List<Patient> The patients with the supplied social insurance number or name. May be null, if no patient has been found.
	 * @throws InvalidInputException thrown if the provided socialInsuranceNr or name is not in an appropriate format.
	 * @throws PersistenceFacadeException 
	 */
	public List<IPatient> searchPatient(String searchValue) throws InvalidInputException {
		List<IPatient> patients = new ArrayList<>();
		if(!searchValue.isEmpty()){
			if(IPatient.isSocialInsuranceNrValid(searchValue)){
				IPatient patient = null;
				try {
					patient = (IPatient) PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class, searchValue);
				} catch (PersistenceFacadeException e) {
					//TODO Logger
					e.printStackTrace();
					
				}
				if(patient != null){
					patients.add(patient);
				}
			} else {		
				try {
					patients = (List<IPatient>) PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class, searchValue);
				} catch (PersistenceFacadeException e) {
					// TODO Logger
					e.printStackTrace();
				}
				if(patients.size() == 0){
					try {
						patients = (List<IPatient>)PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class, searchValue);
					} catch (PersistenceFacadeException e) {
						// TODO Logger
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new InvalidInputException();
		}
		return patients;
	}
	
	/**
	 * calculates if the start and end date of an CalendarEvent is in the given WrokingHours for a specified day.
	 * 
	 * @param calendar the WorkinHours for the chosen calendar
	 * @param start start date of the CalendarEvent
	 * @param end end date of the CalendarEvent
	 * @return true if the CalendarEvent is in the WorkinHours.
	 */
	public boolean isInWorkingHours(ICalendar calendar, LocalDateTime startDate, LocalDateTime endDate){		
		IWorkingHours wh = calendar.getWorkingHoursOfWeekDay(startDate.getDayOfWeek());
		LocalTime start = startDate.toLocalTime();
		LocalTime end = endDate.toLocalTime();
		
		if(wh.getMorningFrom() != null && wh.getMorningTo() != null){
			if((start.isAfter(wh.getMorningFrom()) || start.equals(wh.getMorningFrom()))
				&& (end.isBefore(wh.getMorningTo()) || end.equals(wh.getMorningTo()))){
				return true;
			} 	
		}
		if(wh.getAfternoonFrom() != null && wh.getAfternoonTo() != null){
			if((start.isAfter(wh.getAfternoonFrom()) || start.equals(wh.getAfternoonFrom()))
				&& (end.isBefore(wh.getAfternoonTo()) || end.equals(wh.getAfternoonTo()))){
				return true;
			} 	
		}
		return false;		
	}
	
	/**
	 * looks if the date is already taken by another CalendarEvent.
	 * 
	 * @param calendar in which should be searched.
	 * @param start start date of the CalendarEvent.
	 * @param end end date of the CalendarEvent.
	 * @return true if a CalendarEvent is in the timespan.
	 * @throws InvalidInputException
	 */
	public boolean isDateAlreadyTaken(ICalendar calendar, LocalDateTime start, 
									 LocalDateTime end) throws InvalidInputException {
		if(start.isBefore(end)){
			return calendar.isOneCalendarEventInTimespan(start, end);		
		}else{
			throw new InvalidInputException();
		}
	}

}
