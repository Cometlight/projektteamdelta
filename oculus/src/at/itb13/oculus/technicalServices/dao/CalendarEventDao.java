package at.itb13.oculus.technicalServices.dao;

import at.itb13.oculus.domain.CalenderEvent;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class CalendarEventDao extends GenericDao<CalenderEvent> {
	
	public CalendarEventDao() {
		super(CalenderEvent.class);
	}

}
