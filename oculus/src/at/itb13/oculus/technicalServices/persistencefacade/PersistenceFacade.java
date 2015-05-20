package at.itb13.oculus.technicalServices.persistencefacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.IDoctor;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamD.domain.interfaces.IUser;
import at.itb13.teamD.domain.interfaces.IWorkingHours;
import at.itb13.teamD.technicalServices.exceptions.PersistenceFacadeException;
import at.itb13.teamD.technicalServices.persistenceFacade.IPersistenceFacade;
import at.itb13.teamF.adapter.DiagnosisAdapter;
import at.itb13.teamF.adapter.ExaminationProtocolAdapter;
import at.itb13.teamF.adapter.PrescriptionAdapter;
import at.itb13.teamF.adapter.PrescriptionEntryAdapter;
import at.itb13.teamF.adapter.VisualAidAdapter;
import at.oculus.teamf.domain.entity.interfaces.*;
import at.itb13.oculus.technicalServices.dao.*;
import at.itb13.oculus.domain.*;

/**
 * Enables access to the persistence layer TODO: other Interfaces have to be
 * added
 * 
 * @author Andrew Sparr
 * @date 9 May 2015
 */
public class PersistenceFacade implements IPersistenceFacade {

	private static final Logger _logger = LogManager
			.getLogger(PersistenceFacade.class.getName());

	/**
	 * 
	 * 
	 * @param id
	 *            the ID of the Entity searched for
	 * @param clazz
	 *            the class, esp. the Interface of the entity searched for
	 * @return an instance of the required class or null
	 */
	@SuppressWarnings("unchecked")
	public <T> T getById(Integer id, Class<T> clazz) {

		_logger.info("' Trying to retrieve from database " + clazz.getName()
				+ "' with ID '" + id + "'");

		if (IPatient.class.isAssignableFrom(clazz)) {
			return (T) PatientDao.getInstance().findById(id);
		}

		if (IDoctor.class.isAssignableFrom(clazz)) {
			return (T) DoctorDao.getInstance().findById(id);
		}

		if (ICalendar.class.isAssignableFrom(clazz)) {
			return (T) CalendarDao.getInstance().findById(id);
		}

		if (IEventType.class.isAssignableFrom(clazz)) {
			return (T) EventTypeDao.getInstance().findById(id);
		}

		if (ICalendarEvent.class.isAssignableFrom(clazz)) {
			return (T) CalendarEventDao.getInstance().findById(id);
		}

		// Checks if Interface class of Team F

		if (IDiagnosis.class.isAssignableFrom(clazz)) {
			return (T) DiagnosisDao.getInstance().findById(id);
		}

		if (IExaminationProtocol.class.isAssignableFrom(clazz)) {
			return (T) ExaminationProtocolDao.getInstance().findById(id);
		}

		if (IPrescription.class.isAssignableFrom(clazz)) {
			return (T) CalendarDao.getInstance().findById(id);
		}

		if (IPrescriptionEntry.class.isAssignableFrom(clazz)) {
			return (T) EventTypeDao.getInstance().findById(id);
		}

		if (IVisualAid.class.isAssignableFrom(clazz)) {
			return (T) CalendarEventDao.getInstance().findById(id);
		}

		return null;
	}

	/**
	 * 
	 * 
	 * @param clazz
	 *            the class, esp. the Interface of the entity searched for
	 * @return a List of all entities of the required class
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> clazz) {

		_logger.info("' Trying to retrieve all from database "
				+ clazz.getName() + "'");

		if (IPatient.class.isAssignableFrom(clazz)) {
			return (List<T>) PatientDao.getInstance().findAll();
		} else

		if (IDoctor.class.isAssignableFrom(clazz)) {
			return (List<T>) DoctorDao.getInstance().findAll();
		} else

		if (ICalendar.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarDao.getInstance().findAll();
		} else

		if (IEventType.class.isAssignableFrom(clazz)) {
			return (List<T>) EventTypeDao.getInstance().findAll();
		} else if (ICalendarEvent.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarEventDao.getInstance().findAll();
		}

		// Checks if Interface class of Team F

		else if (IDiagnosis.class.isAssignableFrom(clazz)) {
			return (List<T>) DiagnosisDao.getInstance().findAll();
		}

		else if (IExaminationProtocol.class.isAssignableFrom(clazz)) {
			return (List<T>) ExaminationProtocolDao.getInstance().findAll();
		}

		else if (IPrescription.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarDao.getInstance().findAll();
		}

		else if (IPrescriptionEntry.class.isAssignableFrom(clazz)) {
			return (List<T>) EventTypeDao.getInstance().findAll();
		}

		else if (IVisualAid.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarEventDao.getInstance().findAll();
		}

		return null;
	}

	public boolean makePersistent(Object obj) {

		_logger.info("' Trying to make persistent "
				+ obj.getClass().getName() + "'");
		
		if (obj instanceof IPatient) {
			return PatientDao.getInstance().makePersistent((Patient) obj);
		}

		if (obj instanceof IEventType) {
			return EventTypeDao.getInstance().makePersistent((EventType) obj);
		}

		if (obj instanceof IDoctor) {
			return DoctorDao.getInstance().makePersistent((Doctor) obj);
		}

		if (obj instanceof ICalendar) {
			return CalendarDao.getInstance().makePersistent((Calendar) obj);
		}

		if (obj instanceof ICalendarEvent) {
			return CalendarEventDao.getInstance().makePersistent(
					(CalendarEvent) obj);
		}

		// Checks if Interface class of Team F

		if (obj instanceof IDiagnosis) {
			DiagnosisAdapter diagAda = (DiagnosisAdapter) obj;
			return DiagnosisDao.getInstance().makePersistent(
					(Diagnosis) (diagAda.getDomainObject()));
		}

		if (obj instanceof IExaminationProtocol) {
			ExaminationProtocolAdapter exaAda = (ExaminationProtocolAdapter) obj;
			return ExaminationProtocolDao.getInstance().makePersistent(
					(ExaminationProtocol) exaAda.getDomainObject());
		}

		if (obj instanceof IPrescription) {
			PrescriptionAdapter presAda = (PrescriptionAdapter) obj;
			return PrescriptionDao.getInstance().makePersistent(
					(Prescription) presAda.getDomainObject());
		}

		if (obj instanceof IPrescriptionEntry) {
			PrescriptionEntryAdapter presAda = (PrescriptionEntryAdapter) obj;
			return PrescriptionEntryDao.getInstance().makePersistent(
					(PrescriptionEntry) presAda.getDomainObject());
		}

		if (obj instanceof IVisualAid) {
			VisualAidAdapter visAda = (VisualAidAdapter) obj;
			return VisualAidDao.getInstance().makePersistent(
					(VisualAid) visAda.getDomainObject());
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> Collection<T> searchFor(Class<T> clazz, String searchString)
			throws PersistenceFacadeException {

		if (clazz == null) {
			throw new PersistenceFacadeException();
		}
		
		_logger.info("' Trying to searchFor '"
				+ searchString + "'");

		String[] subStrings = searchString.split(" ");

		if (IPatient.class.isAssignableFrom(clazz)) {
			Collection<IPatient> collection = new LinkedList<>();

			for (int i = 0; i < subStrings.length; i++) {

				List<Patient> firstNameList = PatientDao.getInstance()
						.findByFirstName(subStrings[i]);
				collection.addAll(firstNameList);

				List<Patient> lastNameList = PatientDao.getInstance()
						.findByLastName(subStrings[i]);
				collection.addAll(lastNameList);

				Patient patient = PatientDao.getInstance()
						.findBySocialInsuranceNr(subStrings[i]);

				if (patient != null) {
					collection.add((IPatient) patient);
				}
			}

			 Hashtable<Integer, IPatient> table = new Hashtable<>();
			 for(IPatient pat : collection) {
				 if(!table.containsKey(pat.getPatientId())){
					 table.put(pat.getPatientId(), pat);
				 }
				 else{
					 collection.remove(pat);
				 }
			 }
			return (Collection<T>) collection;
		}
		
		return null;
	}
}
