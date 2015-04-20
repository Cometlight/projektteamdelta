package at.itb13.oculus.application.patient;

import java.util.List;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 08.04.2015
 */
public class PatientSearch {
	
//	/**
//	 * Load the patient with the wanted name. It can search only the fistName, lastName or both.
//	 * 
//	 * @param firstName The patient's firstName.
//	 * @param lastName The patient's lastName.
//	 * @return List<Patient> The patient with the supplied name. Maybe null, if no patient has been found.
//	 * @throws InvalidInputException thrown if the provided name is not in an appropriate format.
//	 */
//	public List<? extends PatientRO> searchPatientByName(String firstName, String lastName) throws InvalidInputException {
//		List<Patient> patients = null;
//		if(!firstName.isEmpty() && !lastName.isEmpty()){
//			patients = PatientDao.getInstance().findByFullName(firstName, lastName);
//		}else if(lastName.isEmpty()){
//			patients = PatientDao.getInstance().findByFirstName(firstName);
//		}else if(firstName.isEmpty()){
//			patients = PatientDao.getInstance().findByLastName(lastName);
//		}else{
//			throw new InvalidInputException();
//		}
//		return patients;
//	}
//	
//	/**
//	 * Loads the patient with the provided social insurance number from the database.
//	 * 
//	 * @param socialInsuranceNr The patient's social insurance number.
//	 * @return The patient with the supplied social insurance number. May be null, if no patient has been found.
//	 * @throws InvalidInputException thrown if the provided socialInsuranceNr is not in an appropriate format.
//	 */
//	public PatientRO searchPatientBySocialInsuranceNr(String socialInsuranceNr) throws InvalidInputException {
//		Patient patient = null;
//		if(Patient.isSocialInsuranceNrValid(socialInsuranceNr)) {
//			patient = PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNr);
//		} else {
//			throw new InvalidInputException();
//		}
//		
//		return patient;
//	}
}
