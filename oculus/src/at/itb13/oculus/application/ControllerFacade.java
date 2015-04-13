package at.itb13.oculus.application;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import at.itb13.oculus.application.calendar.CalendarController;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 12 Apr 2015
 */
public class ControllerFacade {
	
	private static ControllerFacade instance;
	
	public static ControllerFacade getInstance() {
		if(instance == null) {
			instance = new ControllerFacade();
		}
		return instance;
	}

	//TODO Controller einfach als Classparameter? Oder Interface? Irgendwie zusammenfassen? 
	
//	public IController getController(Class controllerClass){
//		if (controllerClass instanceof CalendarController){
//			return new CalendarController();
//		}
//	}
}
