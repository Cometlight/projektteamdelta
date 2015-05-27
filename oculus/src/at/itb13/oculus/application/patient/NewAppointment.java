package at.itb13.oculus.application.patient;

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
	private static final Logger _logger = LogManager.getLogger(NewAppointment.class.getName());
	
	public Boolean isLoginCredentialsValid(String email, String password) {
		Patient patient = PatientDao.getInstance().findByEmail(email);
		
		if(patient != null) {
			return patient.isEqualPassword(password);
		}
		
		return false;
	}
}
