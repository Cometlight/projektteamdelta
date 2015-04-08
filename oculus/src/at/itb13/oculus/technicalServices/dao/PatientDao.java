package at.itb13.oculus.technicalServices.dao;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.domain.CalenderEvent;
import at.itb13.oculus.domain.ExaminationProtocol;
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

	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	public PatientDao() {
		super(Patient.class);
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
	
	/**
	 * Loads the collection from the database into the entity.
	 * No changes are made to the database.
	 * <p>
	 * Example:
	 * 		loadCalendarevents(patient);
	 * 		calendarevents = patient.getCalendarevents();
	 * 
	 * @param patient The Patient whose calendarevents should be loaded. It must not be in a transient state!
	 * @return The Calendarevents that have been loaded. Returns {@link java.util.Collections#emptySet()} in case of failure.
	 * @see GenericDao#loadCollection(T entity, Collection<?> collection)
	 */
	public Set<CalenderEvent> loadCalendarevents(Patient patient) {
		try {
			loadCollection(patient, patient.getCalendarevents());
		} catch (Exception e) {
			_logger.error(e);
			return Collections.emptySet();
		}
		
		return patient.getCalendarevents();
	}
	
	/**
	 * Loads the collection from the database into the entity.
	 * No changes are made to the database.
	 * <p>
	 * Example:
	 * 		loadExaminationProtocols(patient);
	 * 		examinationProtcols = patient.getExaminationProtocols();
	 * 
	 * @param patient The Patient whose examinationProtocols should be loaded. It must not be in a transient state!
	 * @return The ExaminationProtocols that have been loaded. Returns {@link java.util.Collections#emptySet()} in case of failure.
	 * @see GenericDao#loadCollection(T entity, Collection<?> collection)
	 */
	public Set<ExaminationProtocol> loadExaminationProtocols(Patient patient) {	// TODO: Examinationprotocols vs ExaminationProtocols --> see Trello
		try {
			loadCollection(patient, patient.getExaminationprotocols());
		} catch (Exception e) {
			_logger.error(e);
			return Collections.emptySet();
		}
		
		return patient.getExaminationprotocols();
	}
}
