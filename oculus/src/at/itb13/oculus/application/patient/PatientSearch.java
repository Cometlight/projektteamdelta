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
	
	public Patient searchPatientBySocialInsuranceNr(String socialInsuranceNr) {
		
	}
}
