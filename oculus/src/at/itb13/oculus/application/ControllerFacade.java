package at.itb13.oculus.application;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.queue.QueueController;

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
		_instance = new ControllerFacade();
	}
	
	private ControllerFacade() { }
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	
	/**
	 * This method returns, depending on the parameter 'controllerClass', the needed usecase-controller.  
	 * 
	 * @param controllerClass This parameter determines which controller is going to be returned.
	 * @return returns instantiated controller
	 */
	public <T> T getController(Class<T> controllerClass) {
		T controller = null;
		
		if(controllerClass == QueueController.class) {
			
		} else {
			try {
				controller = controllerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				_logger.error(e);
			}
		}
		
		return controller;
	}
	
	public QueueController getQueueController(Integer doctorId, Integer orthoptistId) {
		QueueController controller = null;
		
		for(QueueController qC : _listQueueController) {
			
		}
		
		return controller;
	}
	
	public List<QueueController> getAllQueueController() {
		return _listQueueController;
	}

}

