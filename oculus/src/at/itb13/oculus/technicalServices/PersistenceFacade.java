package at.itb13.oculus.technicalServices;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarEvent;
import at.itb13.oculus.domain.interfaces.IDoctor;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.domain.interfaces.IPatient;
import at.itb13.oculus.domain.interfaces.IWorkingHours;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;
import at.itb13.oculus.technicalServices.exceptions.PersistenceFacadeException;

/**
 * TODO: other Interfaces have to be added
 * 
 * @author Andrew Sparr
 * @date 9 May 2015
 */
public class PersistenceFacade implements IPersistenceFacade {

	private static PersistenceFacade _instance;

	private PersistenceFacade() {
	}

	static {
		if (_instance == null) {
			_instance = new PersistenceFacade();
		}
	}

	public static PersistenceFacade getInstance() {
		return _instance;
	}

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

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> clazz) {
		if (IPatient.class.isAssignableFrom(clazz)) {
			return (List<T>) PatientDao.getInstance().findAll();
		}else

		if (IDoctor.class.isAssignableFrom(clazz)) {
			return (List<T>) DoctorDao.getInstance().findAll();
		}else

		if (ICalendar.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarDao.getInstance().findAll();
		}else

		if (IEventType.class.isAssignableFrom(clazz)) {
			return (List<T>) EventTypeDao.getInstance().findAll();
		}else
		if (ICalendarEvent.class.isAssignableFrom(clazz)) {
			return (List<T>) CalendarEventDao.getInstance().findAll();
		}
		
		return null;
	}

	public boolean makePersistent(Object obj) {

		if (obj instanceof IPatient) {
			return PatientDao.getInstance().makePersistent((Patient) obj);
		}

		// if(IPatient.class.isAssignableFrom(clazz)){
		// return (T) PatientDao.getInstance().findById(id);
		// }
		//
		// if(IDoctor.class.isAssignableFrom(clazz)){
		// return (T) DoctorDao.getInstance().findById(id);
		// }
		//
		// if(ICalendar.class.isAssignableFrom(clazz)){
		// return (T) CalendarDao.getInstance().findById(id);
		// }
		//
		// if(IEventType.class.isAssignableFrom(clazz)){
		// return (T) EventTypeDao.getInstance().findById(id);
		// }
		//
		// if(ICalendarEvent.class.isAssignableFrom(clazz)){
		// return (T) CalendarEventDao.getInstance().findById(id);
		// }

		if (obj instanceof IDoctor) {
			return DoctorDao.getInstance().makePersistent((Doctor) obj);
		}
		
//		if (obj instanceof ICalendar) {
//			return CalendarDao.getInstance().makePersistent((CalendarEvent) obj);
//		}
		
//		if (obj instanceof ICalendarEvent) {
//			return CalendarEventDao.getInstance().makePersistent((CalendarEvent) obj);
//		}

		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> searchFor(Class<T> clazz, String searchString)
			throws PersistenceFacadeException {

		if (clazz == null) {
			throw new PersistenceFacadeException();
		}

		if (IPatient.class.isAssignableFrom(clazz)) {
			Collection<IPatient> collection = new LinkedList<>();
			List<Patient> firstNameList = PatientDao.getInstance()
					.findByFirstName(searchString);
			collection.add((IPatient) firstNameList);
			List<Patient> lastNameList = PatientDao.getInstance()
					.findByLastName(searchString);
			collection.add((IPatient) lastNameList);
			Patient patient = PatientDao.getInstance().findBySocialInsuranceNr(
					searchString);
			collection.add((IPatient) patient);

			// (T) PatientDao.getInstance().;
			// wenn nichts gefunden, bleibt col eben leer
			return (Collection<T>) collection;
		}
		return null;
	}
}
