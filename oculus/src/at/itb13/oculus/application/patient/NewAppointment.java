package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.dao.PatientDao;

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
	 * correct.
	 * 
	 * @param email
	 *            the patient's email
	 * @param password
	 *            the password that corresponds to the given email
	 * @return true if the login credentials are valid
	 */
	public Boolean isLoginCredentialsValid(String email, String password) {
		if(email == null || password == null) {
			throw new NullPointerException();
		}
		
		Patient patient = PatientDao.getInstance().findByEmail(email);

		if (patient != null) {
			return patient.isEqualPassword(password);
		}

		return false;
	}
	
	public String getPossibleAppointment(String weekday, String from, String to, 
											Date start, Date end, String socialInsuranceNumber, String appointmentType){
		Patient patient = PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNumber);
		LocalDateTime ldt = createLocalDateTimeOfStrings(weekday, from);
		int appointmentDuration = getAppointmentDuration(appointmentType);
		Calendar calendar = patient.getDoctor().getCalendar();
		CalendarEvent event = calendar.findPossibleAppointment(ldt, appointmentDuration);
		String s = "test";
		return s;
	}
	
	private LocalDateTime createLocalDateTimeOfStrings(String weekday, String from){
		LocalTime lt = createLocalTimeOfString(from);
		LocalDate ld = createLocalDateOfString(weekday);
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		return ldt;
	}
	
	private LocalTime createLocalTimeOfString(String time){
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]);
		int minute = Integer.parseInt(parts[1]);
		LocalTime lt = LocalTime.of(hour, minute);
		return lt;
	}
	
	private LocalDate createLocalDateOfString(String weekday){
		LocalDate ld = LocalDate.now();
		while(!(weekday.equalsIgnoreCase(ld.getDayOfWeek().name()))){
			ld.plusDays(1);
		}
		return ld;
	}
	
	private int getAppointmentDuration(String appointmentType){
		
		return 0;
	}
	
	public String[] getPatientData(String email){
		String[] patientdata = new String[3];
		Patient patient = PatientDao.getInstance().findByEmail(email);
		patientdata[0] = patient.getFirstName()+" "+patient.getLastName();
		System.out.println(patientdata[0]);
		return patientdata;
	}
}
