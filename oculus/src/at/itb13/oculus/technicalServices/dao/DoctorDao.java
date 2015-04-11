package at.itb13.oculus.technicalServices.dao;

import java.util.Collections;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.HibernateUtil;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.Doctor}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class DoctorDao extends GenericDao<Doctor> {

	private static final Logger _logger = LogManager.getLogger(DoctorDao.class.getName());
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	public DoctorDao() {
		super(Doctor.class);
	}
	
	/**
	 * Loads the collection from the database into the entity.
	 * No changes are made to the database.
	 * <p>
	 * Example:
	 * 		loadPatients(doctor);
	 * 		patients = doctor.getPatients();
	 * 
	 * @param doctor The Doctor whose patients should be loaded. It must not be in a transient state!
	 * @return The Patients that have been loaded. Returns {@link java.util.Collections#emptySet()} in case of failure.
	 * @see GenericDao#loadCollection(T entity, Collection<?> collection)
	 */
//	public Set<Patient> loadPatients(Doctor doctor) {
//		try {
//			loadCollection(doctor, doctor.getPatients());
//		} catch (Exception e) {
//			_logger.error(e);
//			return Collections.emptySet();
//		}
//		
//		return doctor.getPatients();
//	}
	
	/**
	 * Loads the collection from the database into the entity.
	 * No changes are made to the database.
	 * <p>
	 * Example:
	 * 		loadDiagnoses(doctor);
	 * 		diagnoses = doctor.getDiagnoses();
	 * 
	 * @param doctor The Doctor whose diagnoses should be loaded. It must not be in a transient state!
	 * @return The Patients that have been loaded. Returns {@link java.util.Collections#emptySet()} in case of failure.
	 * @see GenericDao#loadCollection(T entity, Collection<?> collection)
	 */
//	public Set<Diagnosis> loadDiagnoses(Doctor doctor) {// TODO: rename getDiagnosises to getDiagnoses ??
//		try {
//			loadCollection(doctor, doctor.getDiagnoses());
//		} catch (Exception e) {
//			_logger.error(e);
//			return Collections.emptySet();
//		}
//		
//		return doctor.getDiagnoses();
//	}
	
	// TODO: Why does a doctor contain a list of "doctors"?
	/* TODO: Why does a doctor can contain several queues and not only one? --> I, Daniel, have asked D. Griesa
	 * Re: In fact the doctor does _not_ contain several queues. In the database there is nothing like a queue; only
	 * queue entries in the table "queue". --> Program the queue manually.
	 */
}
