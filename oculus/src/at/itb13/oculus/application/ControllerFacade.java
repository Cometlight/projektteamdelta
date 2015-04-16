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
			if(qC.getQueue().getDoctor().getDoctorId().equals(doctorId) 
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

	/**
	 * @return
	 */
	public CalendarController getCalendarController() {
		return new CalendarController();
	}

}

