package at.itb13.oculus.application.receptionist;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.teamD.application.exceptions.InvalidInputException;

/**
 * Use Case Controller for "Add new patient".
 * 
 * @author Daniel Scheffknecht
 * @date 22.04.2015
 */
public class NewPatient {
	private static final Logger _logger = LogManager.getLogger(NewPatient.class.getName());
	
	/**
	 * A new patient is created and saved in the database.
	 * 
	 * @param doctor
	 * @param socialInsuranceNr
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 * @param gender
	 * @param street
	 * @param postalCode
	 * @param city
	 * @param countryIsoCode
	 * @param phone
	 * @param email
	 * @return null only if not able to save patient. or if patient with socialInsuranceNr already exists. TODO
	 * @throws InvalidInputException doctorRO, firstName, lastName and gender must not be null
	 */
	public PatientRO createPatient(DoctorRO doctorRO, String socialInsuranceNr,
			String firstName, String lastName, LocalDate birthday, Gender gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) throws InvalidInputException {
		
		if(doctorRO == null || firstName == null || lastName == null || gender == null) {
			throw new InvalidInputException("doctorRO, firstName, lastName and gender must not be null");
		}
		
		PatientRO patROReturn = null;
		
		// Check if patient with social insurance nr already exists; don't insert otherwise!
		if (PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNr) == null) {
			Doctor doctor = DoctorDao.getInstance().findById(doctorRO.getDoctorId());
			
			Patient patient = new Patient(doctor, socialInsuranceNr, firstName, lastName,
					birthday, gender, street, postalCode, city, countryIsoCode,
					phone, email);
			
			if(PatientDao.getInstance().makePersistent(patient)) {
				_logger.info("A new patient with the ID '" + patient.getPatientId() + "' has been saved.");
				patROReturn = patient;
			} else {
				_logger.error("Failed to save patient '" + firstName + " " + lastName + "' to the database.");
			}
		} else {
			_logger.warn("A patient with the social insurance number '" + socialInsuranceNr + "' already exists in the database! "
					+ "Thus, a new patient has not been saved.");
		}
		
		return patROReturn;
	}

}
