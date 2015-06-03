package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.teamD.domain.interfaces.IEventType;



/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 27, 2015
 */
public class NewAppointment {
	private static final Logger _logger = LogManager
			.getLogger(NewAppointment.class.getName());

	/**
	 * Checks if a patient with the given email exists, and if the password is
	 * correct. In addition, it selects the patient in the ControllerFacade.
	 * 
	 * @param email
	 *            the patient's email
	 * @param password
	 *            the password that corresponds to the given email
	 * @return true if the login credentials are valid
	 */
	public at.itb13.oculus.presentation.gwt.shared.Patient isLoginCredentialsValid(String email, String password) {
		if(email == null || password == null) {
			throw new NullPointerException();
		}
		
		Patient patient = PatientDao.getInstance().findByEmail(email);
		at.itb13.oculus.presentation.gwt.shared.Patient patShared = null;

		if (patient != null && patient.isEqualPassword(password)) {
			ControllerFacade.setPatientSelected(patient);
			patShared = new at.itb13.oculus.presentation.gwt.shared.Patient();
			patShared.setName(patient.getFirstName() + " " + patient.getLastName());
			patShared.setSin(patient.getSocialInsuranceNr());
			if(patient.getDoctor() != null) {
				patShared.setDoctor(patient.getDoctor().getUser().getFirstName() + " " + patient.getDoctor().getUser().getLastName());
			}
		}

		return patShared;
	}
	
	/**
	 * In reference to the selected patient in the ControllerFacade.
	 * 
	 * @return true, if the selected patient has a future appointment.
	 */
	public Boolean hasFutureAppointment() {
		CalendarEvent calEv = ((Patient)(ControllerFacade.getPatientSelected())).getNextAppointment();
		return calEv != null;
	}
	
	@SuppressWarnings("static-access")
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPossibleAppointment(LocalDateTime startTime, LocalDateTime endTime, 
																						Date start, Date end, String appointmentType,
																						boolean isSameDay1, boolean isSameDay2){
		
		int appointmentDuration = getAppointmentDuration(appointmentType);
		Patient patient = (Patient) ControllerFacade.getInstance().getPatientSelected();
		Calendar calendar = patient.getDoctor().getCalendar();
		LocalDateTime eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration);
		if(isSameDay1){
			while(startTime.isBefore(eventTime)){
				startTime = startTime.plusDays(7);
				endTime = endTime.plusDays(7);
			}
			eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration);
		}
		if(isSameDay2){
			while(startTime.isBefore(eventTime)){
				startTime = startTime.plusDays(7);
				endTime = endTime.plusDays(7);
			}
			eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration);
		}
		if(true){
			
		}
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent event = new at.itb13.oculus.presentation.gwt.shared.CalendarEvent(); 
		event.setDate(eventTime.toString());
		event.setDoctor(patient.getDoctor().getUser().getTitle() + patient.getDoctor().getUser().getFirstName() + patient.getDoctor().getUser().getLastName());
		event.setType(appointmentType);
		return event;
	}
	
	private int getAppointmentDuration(String appointmentType){
		List<IEventType> list = ControllerFacade.getInstance().getAllEventTypes();
		int duration = 0;
		for(IEventType type : list){
			if(type.getEventTypeName().equals(appointmentType)){
				duration = type.getEstimatedTime();
				break;
			}
		}
		return duration;
	}
	
	// TODO: DELETE
	public String[] getPatientData(String email){
		String[] patientdata = new String[3];
		Patient patient = PatientDao.getInstance().findByEmail(email);
		patientdata[0] = patient.getFirstName()+" "+patient.getLastName();
		patientdata[1] = patient.getSocialInsuranceNr();
		patientdata[2] = patient.getDoctor().getUser().getFirstName()+" "+
				 patient.getDoctor().getUser().getLastName();
		return patientdata;
	}
	
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPatientAppointment(at.itb13.oculus.presentation.gwt.shared.Patient pa) {
		//String[] patientAppointment = new String[4];
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent event = new at.itb13.oculus.presentation.gwt.shared.CalendarEvent(); 
		Patient patient = PatientDao.getInstance().findBySocialInsuranceNr(pa.getSin());
		CalendarEvent ce = patient.getNextAppointment();
		if (ce != null) {
			event.setId(ce.getCalendarEventId());
			event.setDate(ce.getEventStart().toString());
			if (ce.getCalendar().getDoctor() != null) {
				event.setDoctor(ce.getCalendar().getDoctor().getUser()
						.getFirstName()
						+ " "
						+ ce.getCalendar().getDoctor().getUser().getLastName());
			} else if (ce.getCalendar().getOrthoptist() != null) {
				event.setDoctor(ce.getCalendar().getOrthoptist()
						.getUser().getFirstName()
						+ " "
						+ ce.getCalendar().getOrthoptist().getUser()
								.getLastName());
			}
			event.setType(ce.getEventType().getEventTypeName());
			event.setReason(ce.getDescription());
		}
		return event;
	}

	/**
	 * @param calEventId
	 * @return
	 */
	public boolean deleteAppointment(int calEventId) {
		CalendarEvent cal = CalendarEventDao.getInstance().findById(calEventId);
		
		return CalendarEventDao.getInstance().makeTransient(cal);
	}
	
	public boolean addAppointment(at.itb13.oculus.presentation.gwt.shared.Patient patient, at.itb13.oculus.presentation.gwt.shared.CalendarEvent calendarEvent){
		CalendarEvent domainEvent = new CalendarEvent();
		Patient pa = PatientDao.getInstance().findBySocialInsuranceNr(patient.getSin());
		EventType eventType = new EventType();
		if (calendarEvent.getType()!=null){
			eventType = EventTypeDao.getInstance().findByName(calendarEvent.getType());
		}
		domainEvent.setCalendar(pa.getDoctor().getCalendar());
		domainEvent.setDescription(calendarEvent.getReason());
		
		domainEvent.setEventStart(LocalDateTime.parse(calendarEvent.getDate()));
		if (eventType != null){
			domainEvent.setEventEnd(domainEvent.getEventStart().plusMinutes(eventType.getEstimatedTime()));
		} else {
			domainEvent.setEventEnd(domainEvent.getEventStart().plusMinutes(15));
		}
		
		domainEvent.setPatient(pa);
		domainEvent.setEventType(eventType);
		return CalendarEventDao.getInstance().makePersistent(domainEvent);
	}
	
	public boolean isInWorkingHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return true;
	}
}
