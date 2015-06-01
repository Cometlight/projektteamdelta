package at.itb13.oculus.application.patient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	public Object getPossibleAppointment(String weekday, String from, String to, 
											Date start, Date end, String socialInsuranceNumber){
		Patient patient = PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNumber);
		findLocalDateTimeOfString(weekday);
		patient.getDoctor().getCalendar();
		return new Object();
	}
	
	private void findLocalDateTimeOfString(String weekday){
		
	}
	
	public String[] getPatientData(String email){
		String[] patientdata = new String[3];
		Patient patient = PatientDao.getInstance().findByEmail(email);
		patientdata[0] = patient.getFirstName()+" "+patient.getLastName();
		patientdata[1] = patient.getSocialInsuranceNr();
		patientdata[2] = patient.getDoctor().getUser().getFirstName()+" "+
				 patient.getDoctor().getUser().getLastName();
		return patientdata;
	}
	
	public String[] getPatientAppointment(String email){
		String[] patientAppointment = new String[4];
		Patient patient = PatientDao.getInstance().findByEmail(email);
		CalendarEvent ce = patient.getNextAppointment();
		patientAppointment[0] = ce.getEventStart().toString();
		if (ce.getCalendar().getDoctor()!=null){
		patientAppointment[1] = ce.getCalendar().getDoctor().getUser().getFirstName()+" "+
				ce.getCalendar().getDoctor().getUser().getLastName()	;
		}else if (ce.getCalendar().getOrthoptist() != null){
			patientAppointment[1] = ce.getCalendar().getOrthoptist().getUser().getFirstName()+" "+
					ce.getCalendar().getOrthoptist().getUser().getLastName()	;
		}
		patientAppointment[2] = ce.getEventType().getEventTypeName();
		patientAppointment[3] = ce.getDescription();
		return patientAppointment;
	}
	
}
