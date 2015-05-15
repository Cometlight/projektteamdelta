package at.itb13.oculus.technicalServices.persistencefacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;
import at.itb13.oculus.technicalServices.exceptions.PersistenceFacadeException;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.IDoctor;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamD.domain.interfaces.IUser;
import at.itb13.teamD.domain.interfaces.IWorkingHours;

/**
 * Enables access to the persistence layer TODO: other Interfaces have to be
 * added
 * 
 * @author Andrew Sparr
 * @date 9 May 2015
 */
public class PersistenceFacade implements IPersistenceFacade {


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

		return null;
	}

	public boolean makePersistent(Object obj) {

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

		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> Collection<T> searchFor(Class<T> clazz, String searchString) throws PersistenceFacadeException {

		// TODO Suchen nach mehreren Namen

		if (clazz == null) {
			throw new PersistenceFacadeException();
		}

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

			// add elements to al, including duplicates
			
			//Converting to remove possible duplicates 
//			Set<IPatient> hashSet = new HashSet<>();
//			hashSet.addAll(collection);
//			collection.clear();
//			collection.addAll(hashSet);
			
			
			//Remove duplicates
			//Checks if duplicate by comparing social insurance number, if existent
			
			for (Iterator<IPatient> iterator = collection.iterator(); iterator.hasNext();) {
		        IPatient patient = (IPatient) iterator.next();
		        
		        for(Iterator<IPatient> iterator2 = collection.iterator(); iterator.hasNext();){
		        	IPatient patient2 = (IPatient) iterator.next();
		        	
		        	if(patient.getPatientId() != null && patient2.getPatientId() != null){
		        		if(patient.getPatientId().equals(patient2.getPatientId())){
			        		collection.remove(patient2);
			        	}	
		        	}
		        	
//		        	if(patient.getSocialInsuranceNr() != null && patient2.getSocialInsuranceNr() != null){
//		        		if(patient.getSocialInsuranceNr().equals(patient2.getSocialInsuranceNr())){
//			        		collection.remove(patient2);
//			        	}	
//		        	}
		        }

		    }
			
			// (T) PatientDao.getInstance().;
			// wenn nichts gefunden, bleibt col eben leer
			return (Collection<T>) collection;
		}
		return null;
	}
}
