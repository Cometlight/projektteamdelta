package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 09.04.2015
 */
public class CalendarEventDao extends GenericDao<CalendarEvent> {
	
	private static final Logger _logger = LogManager.getLogger(CalendarEventDao.class.getName());
	
	private static CalendarEventDao _calendarEventDao;
	
	static {
		_calendarEventDao = new CalendarEventDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private CalendarEventDao() {
		super(CalendarEvent.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static CalendarEventDao getInstance() {
		return _calendarEventDao;
	}
	

}
