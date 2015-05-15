package at.itb13.oculus.application.receptionist;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.teamD.application.exceptions.InvalidInputException;

/**
 * Use Case Controller for "Search Patient".
 * 
 * @author Daniel Scheffknecht
 * @date 22.04.2015
 */
public class PatientSearch{
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(PatientSearch.class.getName());
	
	/**
	 * Loads the patient with the provided social insurance number or name from the database.
	 * 
	 * @param searchValue The patient's social insurance number or name.
	 * @return List<Patient> The patients with the supplied social insurance number or name. May be null, if no patient has been found.
	 * @throws InvalidInputException thrown if the provided socialInsuranceNr or name is not in an appropriate format.
	 */
	public List<? extends PatientRO> searchPatient(String searchValue) throws InvalidInputException {
		List<? extends PatientRO> patients = null;
		if(!searchValue.isEmpty()){
			if(Patient.isSocialInsuranceNrValid(searchValue)){
				patients = searchPatientBySocialInsuranceNr(searchValue);
			} else {		
				patients = searchPatientByName(searchValue);
			}
		} else {
			throw new InvalidInputException();
		}
		return patients;
	}
	
	/**
	 * Load the patient with the wanted name. It can search only the fistName, lastName or both.
	 * 
	 * @param firstName The patient's firstName.
	 * @param lastName The patient's lastName.
	 * @return List<Patient> The patient with the supplied name. Maybe null, if no patient has been found.
	 */
	private List<? extends PatientRO> searchPatientByName(String name) {
		List<Patient> patients = null;
		patients = PatientDao.getInstance().findByFirstName(name);
		if(patients.size() == 0){
			patients = PatientDao.getInstance().findByLastName(name);
		}
		return patients;
	}

	/**
	 * Loads the patient with the provided social insurance number from the database.
	 * 
	 * @param socialInsuranceNr The patient's social insurance number.
	 * @return The patient with the supplied social insurance number. May be null, if no patient has been found.
	 */
	private List<? extends PatientRO> searchPatientBySocialInsuranceNr(String socialInsuranceNr) {
		List<Patient> patients = new ArrayList<Patient>();
		Patient patient = null;
		patient = PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNr);
		if(patient != null){
			patients.add(patient);
		}
		return patients;
	}
}
