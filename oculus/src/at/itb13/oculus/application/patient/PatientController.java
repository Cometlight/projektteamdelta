package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.ExaminationProtocolRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 17.04.2015
 */
public class PatientController {
	
	private static final Logger _logger = LogManager.getLogger(PatientController.class.getName());
	
	/**
	 * TODO
	 * 
	 * @param patientRO only the id is used
	 * @param doctorRO
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
	 * @return null only if not able to update patient. or patient doesn't exist yet in the database
	 */
	public PatientRO updatePatient(PatientRO patientRO, DoctorRO doctorRO, String socialInsuranceNr,
			String firstName, String lastName, LocalDate birthday, String gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) {
		
		Patient patient = PatientDao.getInstance().findById(patientRO.getPatientId());
		if (patient != null) {	// Only update if patient already exists
			Doctor doctor = DoctorDao.getInstance().findById(doctorRO.getDoctorId());
			patient.setDoctor(doctor);
			patient.setSocialInsuranceNr(socialInsuranceNr);
			patient.setFirstName(firstName);
			patient.setLastName(lastName);
			patient.setBirthDay(birthday);
			patient.setGender(gender);
			patient.setCountryIsoCode(countryIsoCode);
			patient.setPhone(phone);
			patient.setEmail(email);
			
			return makePersistent(patient);
		} else {
			return null;
		}
	}
	
	/**
	 * A new patient is created and saved in the database. TODO
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
	 */
	public PatientRO createPatient(DoctorRO doctorRO, String socialInsuranceNr,
			String firstName, String lastName, LocalDate birthday, String gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) {
		
		// Check if patient with social insurance nr already exists; don't insert otherwise!
		if (PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNr) != null) {
			Doctor doctor = DoctorDao.getInstance().findById(doctorRO.getDoctorId());
			
			Patient patient = new Patient(doctor, socialInsuranceNr, firstName, lastName,
					birthday, gender, street, postalCode, city, countryIsoCode,
					phone, email);
			
			return makePersistent(patient);
		} else {
			_logger.warn("A patient with the social insurance number '" + socialInsuranceNr + "' already exists in the database! "
					+ "Thus, a new patient has not been saved.");
			return null;
		}
	}

	/**
	 * TODO
	 * 
	 * @param patient
	 * @return
	 */
	private PatientRO makePersistent(Patient patient) {
		PatientRO patientRO;
		
		if(PatientDao.getInstance().makePersistent(patient)) {
			patientRO = patient;
		} else {
			patientRO = null;
		}
		
		return patientRO;
	}
	
	/**
	 * Load the patient with the wanted name. It can search only the fistName, lastName or both.
	 * 
	 * @param firstName The patient's firstName.
	 * @param lastName The patient's lastName.
	 * @return List<Patient> The patient with the supplied name. Maybe null, if no patient has been found.
	 * @throws InvalidInputException thrown if the provided name is not in an appropriate format.
	 */
	public List<? extends PatientRO> searchPatientByName(String firstName, String lastName) throws InvalidInputException {
		List<Patient> patients = null;
		if(!firstName.isEmpty() && !lastName.isEmpty()){
			patients = PatientDao.getInstance().findByFullName(firstName, lastName);
		}else if(lastName.isEmpty()){
			patients = PatientDao.getInstance().findByFirstName(firstName);
		}else if(firstName.isEmpty()){
			patients = PatientDao.getInstance().findByLastName(lastName);
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
	public PatientRO searchPatientBySocialInsuranceNr(String socialInsuranceNr) throws InvalidInputException {
		Patient patient = null;
		if(Patient.isSocialInsuranceNrValid(socialInsuranceNr)) {
			patient = PatientDao.getInstance().findBySocialInsuranceNr(socialInsuranceNr);
		} else {
			throw new InvalidInputException();
		}
		
		return patient;
	}
	
	/**
	 * TODO
	 * 
	 * @param patientRO
	 * @return
	 */
	public List<? extends ExaminationProtocolRO> getAllExaminationProtocolsSorted(PatientRO patientRO) {
		Set<ExaminationProtocol> listExPro = patientRO.getExaminationprotocols();
		return ExaminationProtocol.sortExaminationProtocolsByStartDate(listExPro);
	}
	
	/**
	 * TODO
	 * 
	 * @param socialInsuranceNr
	 * @return
	 */
	public boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		return Patient.isSocialInsuranceNrValid(socialInsuranceNr);
	}
}
