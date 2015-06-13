package at.itb13.oculus.application.patient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.teamD.application.exceptions.InvalidInputException;
import at.itb13.teamD.domain.interfaces.IEventType;

/**
 * 
 * @author Daniel Scheffknecht
 * @date May 27, 2015
 */
public class NewAppointment {
	@SuppressWarnings("unused")
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
		} else if (email.isEmpty() || password.isEmpty()) {
			throw new IllegalArgumentException();
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
	
	/**
	 * searches a free Time in the Calendar of the Doctor, which is responsible for the logged in patient, 
	 * where it is possible to create a new appointment.
	 * 
	 * @param startTime is the chosen start date where the patient has time for an appointment.
	 * @param endTime is the chosen end date where the patient has time for an appointment.
	 * @param start is the chosen start date where the patient has NO time for an appointment. (optional)
	 * @param end is the chosen end date where the patient has NO time for an appointment. (optional)
	 * @param appointmentType the type of the appointment the patient needs.
	 * @param lastAppointment is the date from where on the new appointment should be searched.
	 * @return a CalendarEvent in the chosen date.
	 * @throws InvalidInputException when the input is null or wrong.
	 */
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPossibleAppointment(LocalDateTime startTime, LocalDateTime endTime, 
																						Date start, Date end, String appointmentType,
																						LocalDateTime lastAppointment) throws InvalidInputException{
		
		if(startTime == null || endTime == null || lastAppointment == null || startTime.isAfter(endTime)){
			throw new IllegalArgumentException();
		}
		
		int appointmentDuration;
		if(appointmentType.isEmpty() || appointmentType == null){
			appointmentDuration = getAppointmentDuration("Standard treatment");
		} else{
			appointmentDuration = getAppointmentDuration(appointmentType);
		}
		
		while(startTime.getDayOfYear() <= lastAppointment.getDayOfYear()){
			System.out.println("getDay:" + startTime.getDayOfYear() +" , " + lastAppointment.getDayOfYear());
			startTime = startTime.plusDays(7);
			endTime = endTime.plusDays(7);
		}
		
		Patient patient = (Patient) ControllerFacade.getPatientSelected();
		Calendar calendar = patient.getDoctor().getCalendar();
		
		LocalDateTime eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration, false);
		Instant instant = eventTime.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		
		if((start != null && end != null) || !start.after(end)){
			if(date.after(start) && date.before(end) || date.equals(start) || date.equals(end)){
				while(date.before(end) || date.equals(end)){
					date = addDaysToDate(date, 7);
					startTime = startTime.plusDays(7);
					endTime = endTime.plusDays(7);
				}
				eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration, false);
			}
		}
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent event = new at.itb13.oculus.presentation.gwt.shared.CalendarEvent(); 
		event.setDate(eventTime.toString());
		event.setDoctorOrthoptist(patient.getDoctor().getUser().getFirstName() + " " + patient.getDoctor().getUser().getLastName());
		event.setType(appointmentType);
		return event;
	}
	
	/**
	 * with this method you can add a chosen number of days to your Date.
	 * 
	 * @param date where you want to add some days.
	 * @param noOfDays number of days you want do add.
	 * @return the new date.
	 */
	public static Date addDaysToDate(Date date, int noOfDays) {
		java.util.Calendar c = java.util.Calendar.getInstance(); 
		c.setTime(date); 
		c.add(java.util.Calendar.DATE, noOfDays);
		date = c.getTime();
		return date;
	}
	
	/**
	 * get the appointment duration out of a string which includes the name of the appointment type.
	 * 
	 * @param appointmentType the type of the examination where you want to know the duration
	 * @return the duration how long the appointment takes.
	 */
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
	
	
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPatientAppointment(at.itb13.oculus.presentation.gwt.shared.Patient pa) {
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent sharedEvent = null;
		Patient patient = PatientDao.getInstance().findBySocialInsuranceNr(
				pa.getSin());
		CalendarEvent ce = patient.getNextAppointment();
		if (ce != null) {
			sharedEvent = new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
			sharedEvent.setId(ce.getCalendarEventId());
			sharedEvent.setDate(ce.getEventStart().toString());
			if (ce.getCalendar().getDoctor() != null) {
				sharedEvent.setDoctorOrthoptist(ce.getCalendar().getDoctor()
						.getUser().getFirstName()
						+ " "
						+ ce.getCalendar().getDoctor().getUser().getLastName());
			} else if (ce.getCalendar().getOrthoptist() != null) {
				sharedEvent.setDoctorOrthoptist(ce.getCalendar()
						.getOrthoptist().getUser().getFirstName()
						+ " "
						+ ce.getCalendar().getOrthoptist().getUser()
								.getLastName());
			}
			sharedEvent.setType(ce.getEventType().getEventTypeName());
			sharedEvent.setReason(ce.getDescription());
		}
		return sharedEvent;
	}

	/**
	 * @param calEventId
	 * @return
	 */
	public boolean deleteAppointment(int calEventId) {
		CalendarEvent cal = CalendarEventDao.getInstance().findById(calEventId);
		if (cal!=null){
			return CalendarEventDao.getInstance().makeTransient(cal);
		} else {
			throw new IllegalArgumentException("Failed to find a calendarevent with id = " + calEventId );
		}
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
			domainEvent.setPatient(pa);
			domainEvent.setEventType(eventType);
			return CalendarEventDao.getInstance().makePersistent(domainEvent);
		} else {
			return false;
		}
		
		
	}
	
	/**
	 * 
	 * @param startDateTime the start of the event
	 * @param endDateTime the end of the event
	 * @return true if within the patient's doctor's calendar's working hours.
	 */
	public boolean isInWorkingHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		if(startDateTime == null || endDateTime == null || endDateTime.isBefore(startDateTime)) {
			throw new IllegalArgumentException();
		}
		
		Calendar calendar = ((Patient)(ControllerFacade.getPatientSelected())).getDoctor().getCalendar();
		WorkingHours wH = calendar.getWorkingHoursOfWeekDay(startDateTime.getDayOfWeek());
		if(wH != null) {
			return wH.isDateInWorkingHours(
					startDateTime.toLocalTime(), 
					endDateTime.toLocalTime()
			);
		} else {
			return false;
		}
		
	}

	/**
	 * with this method you get the earliest three possible appointments. 
	 * 
	 * @return three appointments
	 * @throws InvalidInputException when a call of a method returns InvalidInputException.
	 * 
	 */
	@SuppressWarnings("static-access")
	public List<at.itb13.oculus.presentation.gwt.shared.CalendarEvent> getNextAppointments() throws InvalidInputException {
		int appointmentDuration;
		String appointmentType = "Standard treatment";
		appointmentDuration = getAppointmentDuration(appointmentType);
		List<at.itb13.oculus.presentation.gwt.shared.CalendarEvent> list = new LinkedList<>();
		Patient patient = (Patient) ControllerFacade.getPatientSelected();
		Calendar calendar = patient.getDoctor().getCalendar();
		LocalDateTime eventTime = LocalDateTime.now();
		eventTime = eventTime.plusDays(1);
		eventTime = eventTime.of(eventTime.getYear(), eventTime.getMonth(), eventTime.getDayOfMonth(), 0, 0);
		for(int i = 0; i < 3; i++){
			LocalDateTime startTime = eventTime.plusMinutes(appointmentDuration);
			LocalDateTime endTime = eventTime.of(eventTime.getYear(), eventTime.getMonth(), eventTime.getDayOfMonth(), 23, 59);
			eventTime = calendar.findPossibleAppointment(startTime, endTime, appointmentDuration, true);
			at.itb13.oculus.presentation.gwt.shared.CalendarEvent event = new at.itb13.oculus.presentation.gwt.shared.CalendarEvent(); 
			event.setDate(eventTime.toString());
			event.setDoctorOrthoptist(patient.getDoctor().getUser().getFirstName() + " " + patient.getDoctor().getUser().getLastName());
			event.setType(appointmentType);
			list.add(event);
		}
		return list;
	}
}
