package at.itb13.oculus.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.calendar.NewAppointmentController;
import at.itb13.oculus.application.doctor.WelcomePatient;
import at.itb13.oculus.application.interfaces.INewAppointmentController;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.application.receptionist.NewPatient;
import at.itb13.oculus.application.receptionist.PatientSearch;
import at.itb13.oculus.application.receptionist.WelcomeAtReception;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.domain.interfaces.IUser;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
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
	private static List<IEventType> _listEventTypes;
	
	private static PatientRO _patientSelected;
	
	static {
		init();
	}
	
	private ControllerFacade() { }
	
	public static void init() {
		if(_instance == null) {
			_logger.info("Initializing ControllerFacade...");
			
			_instance = new ControllerFacade();
			
			reloadAllQueueController();
			
			reloadAllCalendarController();
			
			loadEventTypes();
			
			_logger.info("ControllerFacade has been initialized.");
		}
	}
	
	/**
	 * Loads all queues from the database and assigns them to _listQueueController.
	 */
	private static void reloadAllQueueController() {
		_logger.info("Loading Queues from database...");
		
		_listQueueController = new LinkedList<>();
		QueueDao.getInstance().findAll().forEach(q -> {
			QueueController qC = new QueueController(q);
			_listQueueController.add(qC);
		});
		
		_listQueueController.sort(new Comparator<QueueController>() {
			@Override
			public int compare(QueueController o1, QueueController o2) {
				String o1Name = getNameOfQueueController(o1);
				String o2Name = getNameOfQueueController(o2);
				return o1Name.compareTo(o2Name);
			}
		});
		
		_logger.info("Queues have been loaded from database.");
	}
	
	private static String getNameOfQueueController(QueueController queueController) {
		if(queueController.getQueue().getDoctor() != null) {
			IUser user = queueController.getQueue().getDoctor().getUser();
			return "Dr " + user.getFirstName() + " " + user.getLastName();
		} else if(queueController.getQueue().getOrthoptist() != null) {
			IUser user = queueController.getQueue().getOrthoptist().getUser();
			return "Orthoptist " + user.getFirstName() + " " + user.getLastName();
		} else {
			return "Orthoptists";	// general orthoptist queue
		}
	}
	
	/**
	 * Loads all calendars from the database and assigns them to _listCalendarController.
	 */
	private static void reloadAllCalendarController() {
		_logger.info("Loading Calendars from database...");
		
		_listCalendarController = new LinkedList<>();
		CalendarDao.getInstance().findAll().forEach(q -> {
			CalendarController cC = new CalendarController(q);
			_listCalendarController.add(cC);
		});
		
		_logger.info("Calendars have been loaded from database.");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void loadEventTypes(){
		_listEventTypes = new LinkedList<>();
		List<T> eventTypes = new ArrayList<>();
		eventTypes = (List<T>) EventTypeDao.getInstance().findAll();
		for(T event : eventTypes){
			_listEventTypes.add((IEventType) event);
		}
	}
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	/* -- NewAppointmentController -- */
	public INewAppointmentController getNewAppointmentController() {
		return new NewAppointmentController();
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
	
	/**
	 * Returns a queue Controller with the same doctorID and orthoptistID.
	 * @param queueRO
	 * @return
	 */
	public QueueController getQueueController(QueueRO queueRO) {
		return getQueueController((queueRO.getDoctor() == null) ? null : queueRO.getDoctor().getDoctorId(), 
				(queueRO.getOrthoptist() == null) ? null : queueRO.getOrthoptist().getOrthoptistId());
	}
	
	public List<QueueController> getAllQueueController() {
		return _listQueueController;
	}
	
	public void refreshQueueController() {
		reloadAllQueueController();
	}

	/* -- CalendarController -- */
	/**
	 * 
	 * @param doctorId
	 * @param orthoptistId
	 * @return null if failed to find calendar with doctorId and orthoptistId
	 */
	public CalendarController getCalendarController(Integer doctorId, Integer orthoptistId) {
		CalendarController controller = null;
		
		for(CalendarController cC : _listCalendarController) {
			Integer calDocId = (cC.getCalendar().getDoctor() == null) ? null : cC.getCalendar().getDoctor().getDoctorId();
			Integer calOrtId = (cC.getCalendar().getOrthoptist() == null) ? null : cC.getCalendar().getOrthoptist().getOrthoptistId();
			
			if(		   ( calDocId != null && doctorId != null && calDocId.equals(doctorId) )
					|| ( calOrtId != null && orthoptistId != null && calOrtId.equals(orthoptistId) )
					|| ( calDocId == null && calOrtId == null && doctorId == null && orthoptistId == null )
					) {
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
	
	public List<IEventType> getAllEventTypes(){
		return _listEventTypes; 
	}
	
	public void refreshCalendarController() {
		reloadAllCalendarController();
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
	public static void setPatientSelected(PatientRO patientSelected) {
		_patientSelected = patientSelected;
	}
	
}

