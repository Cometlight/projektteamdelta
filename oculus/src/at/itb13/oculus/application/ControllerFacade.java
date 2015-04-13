

package at.itb13.oculus.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.calendar.CalendarController;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 12 Apr 2015
 */
public class ControllerFacade {
	
	private static final Logger _logger = LogManager.getLogger(ControllerFacade.class.getName());
	private static ControllerFacade _instance;
	
	static {
		_instance = new ControllerFacade();
	}
	
	private ControllerFacade() { }
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	public <T> T getController(Class<T> controllerClass) {
		T controller = null;
		
//		if(controllerClass == CalendarController.class) {
//			TODO
//		} else {
			try {
				controller = controllerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				_logger.error(e);
			}
//		}
		
		return controller;
	}

}

