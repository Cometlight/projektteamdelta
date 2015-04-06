package at.itb13.oculus.technicalServices.dao;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.itb13.oculus.domain.Calendarevent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Eventtype;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.HibernateUtil;

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
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(eventtype);	// TODO: Check if merge(), lock(), or sth. similar would be more appropriate for reattaching the object;
			Hibernate.initialize(eventtype.getCalendarevents());
			
			tx.commit();
			session.flush();	// TODO: Check if flush() is needed
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return eventtype.getCalendarevents();
	}
	// TODO: Add logging
}
