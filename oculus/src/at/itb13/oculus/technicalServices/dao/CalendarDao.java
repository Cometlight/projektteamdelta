package at.itb13.oculus.technicalServices.dao;

import java.util.Collections;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 09.04.2015
 */
public class CalendarDao extends GenericDao<Calendar> {

	private static final Logger _logger = LogManager.getLogger(DoctorDao.class.getName());
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	public CalendarDao() {
		super(Calendar.class);
	}
	
	/**
	 * TODO
	 * @param calendar
	 * @return
	 */
	public Set<CalendarEvent> loadCalendarevents(Calendar calendar) {
		try {
			loadCollection(calendar, calendar.getCalendarevents());
		} catch (Exception e) {
			_logger.error(e);
			return Collections.emptySet();
		}
		
		return calendar.getCalendarevents();
	}
	
}
