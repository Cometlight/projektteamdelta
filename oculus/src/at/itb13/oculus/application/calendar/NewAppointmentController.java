package at.itb13.oculus.application.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.oculus.application.interfaces.INewAppointmentController;
import at.itb13.oculus.application.interfaces.IPatientSearch;
import at.itb13.oculus.domain.CalendarEventFactory;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarEvent;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.domain.interfaces.IPatient;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 03.05.2015
 */
public class NewAppointmentController implements INewAppointmentController, IPatientSearch{
	
	private CalendarEventFactory _factory = CalendarEventFactory.getInstance();

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
			LocalDateTime end, String description, IPatient patient)
			throws SaveException {
		_factory.createCalendarEvent((ICalendar) calendar, (IEventType) eventType, start, end, description, (IPatient) patient);
		
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
		_factory.createCalendarEvent((ICalendar) calendar, (IEventType) eventType, start, end, description, patient);
		
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
	 */
	@SuppressWarnings("unchecked")
	public List<IPatient> searchPatient(String searchValue) throws InvalidInputException {
		List<IPatient> patients = new ArrayList<>();
		if(!searchValue.isEmpty()){
			if(Patient.isSocialInsuranceNrValid(searchValue)){
				IPatient patient = null;
				patient = PatientDao.getInstance().findBySocialInsuranceNr(searchValue);
				if(patient != null){
					patients.add(patient);
				}
			} else {		
				patients = (List<IPatient>)(Object) PatientDao.getInstance().findByFirstName(searchValue);
				if(patients.size() == 0){
					patients = (List<IPatient>)(Object) PatientDao.getInstance().findByLastName(searchValue);
				}
			}
		} else {
			throw new InvalidInputException();
		}
		return patients;
	}
	
	//Nach Ordinationszeit prüfen!
	/**
	 * TODO
	 */

}
