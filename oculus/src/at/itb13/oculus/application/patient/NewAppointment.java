package at.itb13.oculus.application.patient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		System.out.println(patientdata[0]);
		return patientdata;
	}
}
