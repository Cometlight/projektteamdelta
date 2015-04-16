package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 09.04.2015
 */
public class CalendarDao extends GenericDao<Calendar> {

	private static final Logger _logger = LogManager.getLogger(CalendarDao.class.getName());
	
	private static CalendarDao _calendarDao;
	
	static {
		_calendarDao = new CalendarDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private CalendarDao() {
		super(Calendar.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static CalendarDao getInstance() {
		return _calendarDao;
	}
}
