package at.itb13.oculus.technicalServices.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.EventType}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class EventTypeDao extends GenericDao<EventType> {
	
	private static final Logger _logger = LogManager.getLogger(EventTypeDao.class.getName());
	
	private static EventTypeDao _eventTypeDao;
	
	static {
		_eventTypeDao = new EventTypeDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private EventTypeDao() {
		super(EventType.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static EventTypeDao getInstance() {
		return _eventTypeDao;
	}
	
	/**
	 * Load the Eventtype with the specified name from the database.
	 * 
	 * @param name The name of the Eventtype that should be loaded from the database.
	 * @return the desired Eventtype, or null, if no Eventtype with the supplied name was found.
	 */
	public EventType findByName(String name) {
		EventType ev = null;
		
		List<EventType> eventtypes = findByCriteria(Restrictions.eq("eventTypeName", name));
		
		if(eventtypes.size() > 1) {
			_logger.warn("More than 1 Eventtype with the name'" + name + "' has been found!");
		}

		if(!eventtypes.isEmpty()) {
			ev = eventtypes.get(0);
		}
		
		return ev;
	}
}
