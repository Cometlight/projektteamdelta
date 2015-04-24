package at.itb13.oculus.application;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.doctor.WelcomePatient;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.application.receptionist.NewPatient;
import at.itb13.oculus.application.receptionist.PatientSearch;
import at.itb13.oculus.application.receptionist.WelcomeAtReception;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.QueueDao;

/**
 * This class is responsible for delivering usecase-controller to the application layer. There is only one instance available (Singleton).
 * 
 * @author Andrew Sparr
 * @date 12 Apr 2015
 */
public class ControllerFacade {
	
	private static final Logger _logger = LogManager.getLogger(ControllerFacade.class.getName());
	private static ControllerFacade _instance;
	
	private static List<QueueController> _listQueueController;
	private static List<CalendarController> _listCalendarController;
	
	private static Patient _patientSelected;	// TODO work with this patient instead of _tempPatient of OculusMain ###############################################
	
	static {
		init();
	}
	
	private ControllerFacade() { }
	
	public static void init() {
		_logger.info("Initializing ControllerFacade...");
		
		_instance = new ControllerFacade();
		
		reloadQueueController();
		
		reloadCalendarController();
		
		_logger.info("ControllerFacade has been initialized.");
	}
	
	/**
	 * Loads all queues from the database and assigns them to _listQueueController.
	 */
	private static void reloadQueueController() {
		_listQueueController = new LinkedList<>();
		QueueDao.getInstance().findAll().forEach(q -> {
			QueueController qC = new QueueController(q);
			_listQueueController.add(qC);
		});
	}
	
	/**
	 * Loads all calendars from the database and assigns them to _listCalendarController.
	 */
	private static void reloadCalendarController() {
		_listCalendarController = new LinkedList<>();
		CalendarDao.getInstance().findAll().forEach(q -> {
			CalendarController cC = new CalendarController(q);
			_listCalendarController.add(cC);
		});
	}
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	/* -- PatientSearch -- */
	public PatientSearch getPatientSearch() {
		return new PatientSearch();
	}
	
	/* -- NewPatient -- */
	public NewPatient getNewPatient() {
		return new NewPatient();
	}
	
	/* -- WelcomeAtReception -- */
	public WelcomeAtReception getWelcomeAtReception() {
		return new WelcomeAtReception();
	}
	
	/* -- WelcomePatient -- */
	public WelcomePatient getWelcomePatient() {
		return new WelcomePatient();
	}
	
	/* -- QueueController -- */
	public QueueController getQueueController(Integer doctorId, Integer orthoptistId) {
		QueueController controller = null;
		
		for(QueueController qC : _listQueueController) {
			Integer queueDocId = (qC.getQueue().getDoctor() == null) ? null : qC.getQueue().getDoctor().getDoctorId();
			Integer queueOrtId = (qC.getQueue().getOrthoptist() == null) ? null : qC.getQueue().getOrthoptist().getOrthoptistId();
			if( ( (queueDocId == null && doctorId == null) || queueDocId.equals(doctorId) ) 	// careful: doctorId.equals(queueDocId) might throw a NullPointerException
					&& ( (queueOrtId == null && orthoptistId == null) || queueOrtId.equals(orthoptistId) ) ) {
				controller = qC;
				break;
			}
		}
		
		return controller;
	}
	
	public QueueController getQueueController(QueueRO queueRO) {
		return getQueueController((queueRO.getDoctor() == null) ? null : queueRO.getDoctor().getDoctorId(), 
				(queueRO.getOrthoptist() == null) ? null : queueRO.getOrthoptist().getOrthoptistId());
	}
	
	public List<QueueController> getAllQueueController() {
		return _listQueueController;
	}
	
	public void refreshQueueController() {
		reloadQueueController();
	}

	/* -- CalendarController -- */
	public CalendarController getCalendarController(Integer doctorId, Integer orthoptistId) {
		CalendarController controller = null;
		
		for(CalendarController cC : _listCalendarController) {
			Integer calDocId = (cC.getCalendar().getDoctor() == null) ? null : cC.getCalendar().getDoctor().getDoctorId();
			Integer calOrtId = (cC.getCalendar().getOrthoptist() == null) ? null : cC.getCalendar().getOrthoptist().getOrthoptistId();
			if( ( (calDocId == null && doctorId == null) || calDocId.equals(doctorId) ) 	// careful: doctorId.equals(calDocId) might throw a NullPointerException
					&& ( (calOrtId == null && orthoptistId == null) || calOrtId.equals(orthoptistId) ) ) {
				controller = cC;
				break;
			}
		}
		
		return controller;
	}
	
	public CalendarController getCalendarController(CalendarRO calendarRO) {
		CalendarController controller = null;
		
		for(CalendarController cC : _listCalendarController) {
			if(cC.getCalendar().getCalendarId().equals(calendarRO.getCalendarId())) {
				controller = cC;
				break;
			}
		}
		
		return controller;
	}

	public List<CalendarController> getAllCalendarController() {
		return _listCalendarController;
	}
	
	public void refreshCalendarController() {
		reloadCalendarController();
	}

	/* -- Selected Domain Objects -- */
	/**
	 * @return the patientSelected
	 */
	public static PatientRO getPatientSelected() {
		return _patientSelected;
	}

	/**
	 * @param patientSelected the patientSelected to set
	 */
	public static void setPatientSelected(Patient patientSelected) {
		_patientSelected = patientSelected;
	}
	
}

