package at.itb13.teamD.application;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacadeProvider;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.IEventType;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 13.05.2015
 */
public class ControllerFacade {

	private static final Logger _logger = LogManager.getLogger(ControllerFacade.class.getName());
	private static ControllerFacade _instance;
	
	private static List<IEventType> _listEventTypes;
	private static List<ICalendar> _listCalendar; //used for the getAllCalendar in the NewAppointmentController
	
	static {
		init();
	}
	
	private ControllerFacade() { }
	
	public static void init() {
		if(_instance == null) {
			_logger.info("Initializing ControllerFacade...");
			
			_instance = new ControllerFacade();
			
			loadEventTypes();
			
			loadCalendar();
			
			_logger.info("ControllerFacade has been initialized.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void loadEventTypes(){
		_listEventTypes = new LinkedList<>();
		List<T> eventTypes = new LinkedList<>();
		eventTypes = (List<T>) PersistenceFacadeProvider.getPersistenceFacade().getAll(IEventType.class);
		for(T event : eventTypes){
			_listEventTypes.add((IEventType) event);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void loadCalendar(){
		_listCalendar = new LinkedList<>();
		List<T> calendars = new LinkedList<>();
		
		calendars = (List<T>) PersistenceFacadeProvider.getPersistenceFacade().getAll(ICalendar.class);
		for(T calendar : calendars){
			_listCalendar.add((ICalendar) calendar);
		}
	}
	
	public static ControllerFacade getInstance() {
		return _instance;
	}
	
	/* -- NewAppointmentController -- */
	public INewAppointmentController getNewAppointmentController() {
		return new NewAppointmentController();
	}
	
	public List<IEventType> getAllEventTypes(){
		return _listEventTypes; 
	}
	
	public List<ICalendar> getAllCalendars(){
		return _listCalendar; 
	}
	
}
