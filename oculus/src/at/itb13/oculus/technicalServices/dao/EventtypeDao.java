package at.itb13.oculus.technicalServices.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.domain.Calendarevent;
import at.itb13.oculus.domain.Eventtype;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class EventtypeDao extends GenericDao<Eventtype> {
	
	public EventtypeDao() {
		super(Eventtype.class);
	}
	
	public Set<Calendarevent> loadCalendarevents(Eventtype eventtype) {
		try {
			loadCollection(eventtype, eventtype.getCalendarevents());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eventtype.getCalendarevents();
	}
	// TODO: Add logging
	
	public Eventtype findByName(String name) {
		Eventtype ev = null;
		
		List<Eventtype> eventtypes = findByCriteria(Restrictions.like("eventTypeName", name));
		
		if(eventtypes.size() > 1) {
			// TODO logging warning, shouldn't happen.
		}

		if(!eventtypes.isEmpty()) {
			ev = eventtypes.get(0);
		}
		
		return ev;
	}
}
