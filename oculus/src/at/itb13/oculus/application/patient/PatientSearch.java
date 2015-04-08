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
	
	public List<Patient> searchPatientByName(String firstName, String lastName) throws InvalidInputException {
		List<Patient> patients = null;
		PatientDao patientDao = new PatientDao();
		if(firstName != null && lastName != null){
			patients = patientDao.findByFullName(firstName, lastName);
			return patients;
		}else if(firstName != null){
			//TODO
			return patients;
		}else if(lastName != null){
			//TODO
			return patients;
		}else{
			throw new InvalidInputException();
		}
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
	 * TODO
	 * @param socialInsuranceNr
	 * @return
	 */
	private boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		boolean isValid;
		isValid = socialInsuranceNr != null && socialInsuranceNr.length() == SOCIAL_INSURANCE_NR_LENGTH;
		// TODO: RegEx-Check, if valid?
		return isValid;
	}
}
