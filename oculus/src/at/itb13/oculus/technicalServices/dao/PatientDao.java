package at.itb13.oculus.technicalServices.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.Patient}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 07.04.2015
 */
public class PatientDao extends GenericDao<Patient> {
	
	private static final Logger _logger = LogManager.getLogger(PatientDao.class.getName());
	
	private static PatientDao _patientDao;
	
	static {
		_patientDao = new PatientDao();
	}

	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private PatientDao() {
		super(Patient.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static PatientDao getInstance() {
		return _patientDao;
	}

	/**
	 * Load the Patients with the specified firstName and lastName from the database.
	 * 
	 * @param firstName The first name of the Patient that should be loaded from the database.
	 * @param lastName The last name of the Patient that should be loaded from the database.
	 * @return A list of all patients that fit the supplied data. May be empty.
	 */
	public List<Patient> findByFullName(String firstName, String lastName) {
		List<Patient> patients = null;
		
		patients = findByCriteria(Restrictions.eq("firstName", firstName), Restrictions.eq("lastName", lastName));
		
		return patients;
	}
	
	/**
	 * Load the Patients with the specified firstName from the database.
	 * 
	 * @param firstName The first name of the Patient that should be loaded from the database.
	 * @return A list of all patients that fit the supplied data. May be empty.
	 */
	public List<Patient> findByFirstName(String firstName) {
		List<Patient> patients = null;
		
		patients = findByCriteria(Restrictions.eq("firstName", firstName));
		
		return patients;
	}
	
	/**
	 * Load the Patients with the specified firstName from the database.
	 * 
	 * @param lastName The last name of the Patient that should be loaded from the database.
	 * @return A list of all patients that fit the supplied data. May be empty.
	 */
	public List<Patient> findByLastName(String lastName) {
		List<Patient> patients = null;
		
		patients = findByCriteria(Restrictions.eq("lastName", lastName));
		
		return patients;
	}
	
	/**
	 * Load the Patient with the specified socialInsuranceNr from the database.
	 * 
	 * @param socialInsuranceNr The socialInsuranceNr of the Patient that should be loaded from the database.
	 * @return the desired Patient, or null, if no Patient with the supplied socialInsuranceNr was found.
	 */
	public Patient findBySocialInsuranceNr(String socialInsuranceNr) {
		Patient patient = null;
		
		List<Patient>patients = findByCriteria(Restrictions.eq("socialInsuranceNr", socialInsuranceNr));
		
		if(patients.size() > 1) {
			_logger.warn("More than 1 Patient with the socialInsuranceNr'" + socialInsuranceNr + "' has been found!");
		}

		if(!patients.isEmpty()) {
			patient = patients.get(0);
		}
		
		return patient;
	}
}
