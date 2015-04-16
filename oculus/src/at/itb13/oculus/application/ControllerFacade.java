package at.itb13.oculus.application;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.doctor.DoctorRequest;
import at.itb13.oculus.application.patient.PatientCreation;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.technicalServices.dao.CalendarDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
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
	
	static {
		init();
	}
	
	private ControllerFacade() { }
	
	public static void init() {
		_instance = new ControllerFacade();
		
		_listQueueController = new LinkedList<>();
		QueueDao.getInstance().findAll().forEach(q -> {
			QueueController qC = new QueueController(q);
			_listQueueController.add(qC);
		});
		
		_listCalendarController = new LinkedList<>();
		CalendarDao.getInstance().findAll().forEach(q -> {
			CalendarController cC = new CalendarController(q);
			_listCalendarController.add(cC);
		});
		
	}
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	/**
	 * 
	 * @return instantiated controller
	 */
	public PatientSearch getPatientSearch() {
		return new PatientSearch();
	}
	
	public PatientCreation getPatientCreation() {
		return new PatientCreation();
	}
	
	public DoctorRequest getDoctorRequest() {
		return new DoctorRequest();
	}
	
	public QueueController getQueueController(Integer doctorId, Integer orthoptistId) {
		QueueController controller = null;
		
		for(QueueController qC : _listQueueController) {
			if(qC.getQueue().getDoctor().getDoctorId().equals(doctorId) // FIXME What if null? see getCalendarController
					&& qC.getQueue().getOrthoptist().getOrthoptistId().equals(orthoptistId)) {
				controller = qC;
				break;
			}
		}
		
		return controller;
	}
	
	public List<QueueController> getAllQueueController() {
		return _listQueueController;
	}

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

	public List<CalendarController> getAllCalendarController() {
		return _listCalendarController;
	}
}

