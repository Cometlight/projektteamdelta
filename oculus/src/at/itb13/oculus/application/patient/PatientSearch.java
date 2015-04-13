package at.itb13.oculus.application.patient;

import java.util.List;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 08.04.2015
 */
public class PatientSearch {
	
	public static final Integer SOCIAL_INSURANCE_NR_LENGTH = 10;
	
	/**
	 * Load the patient with the wanted name. It can search only the fistName, lastName or both.
	 * 
	 * @param firstName The patient's firstName.
	 * @param lastName The patient's lastName.
	 * @return List<Patient> The patient with the supplied name. Maybe null, if no patient has been found.
	 * @throws InvalidInputException thrown if the provided name is not in an appropriate format.
	 */
	public List<Patient> searchPatientByName(String firstName, String lastName) throws InvalidInputException {
		List<Patient> patients = null;
		PatientDao patientDao = new PatientDao();
		if(!firstName.isEmpty() && !lastName.isEmpty()){
			patients = patientDao.findByFullName(firstName, lastName);
		}else if(lastName.isEmpty()){
			patients = patientDao.findByFirstName(firstName);
		}else if(firstName.isEmpty()){
			patients = patientDao.findByLastName(lastName);
		}else{
			throw new InvalidInputException();
		}
		return patients;
	}
	
	/**
	 * Loads the patient with the provided social insurance number from the database.
	 * 
	 * @param socialInsuranceNr The patient's social insurance number.
	 * @return The patient with the supplied social insurance number. May be null, if no patient has been found.
	 * @throws InvalidInputException thrown if the provided socialInsuranceNr is not in an appropriate format.
	 */
	public Patient searchPatientBySocialInsuranceNr(String socialInsuranceNr) throws InvalidInputException {
		Patient patient = null;
		if(isSocialInsuranceNrValid(socialInsuranceNr)) {
			PatientDao patientDao = new PatientDao();
			patient = patientDao.findBySocialInsuranceNr(socialInsuranceNr);
		} else {
			throw new InvalidInputException();
		}
		
		return patient;
	}
	
	/**
	 * Checks if the provided insurance number is in a valid format.
	 * 
	 * @param socialInsuranceNr The social insurance number that should be checked.
	 * @return true, if the socialInsuranceNr is in a valid format.
	 */
	private boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		boolean isValid;
		isValid = socialInsuranceNr != null && socialInsuranceNr.length() == SOCIAL_INSURANCE_NR_LENGTH;
		// TODO: RegEx-Check, if valid?
		return isValid;
	}
}
