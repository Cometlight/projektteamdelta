// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Calendarworkinghours.
 * @see .Calendarworkinghours
 * @author Hibernate Tools
 */
@Stateless
public class CalendarworkinghoursHome {

	private static final Log log = LogFactory
			.getLog(CalendarworkinghoursHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Calendarworkinghours transientInstance) {
		log.debug("persisting Calendarworkinghours instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Calendarworkinghours persistentInstance) {
		log.debug("removing Calendarworkinghours instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Calendarworkinghours merge(Calendarworkinghours detachedInstance) {
		log.debug("merging Calendarworkinghours instance");
		try {
			Calendarworkinghours result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Calendarworkinghours findById(Integer id) {
		log.debug("getting Calendarworkinghours instance with id: " + id);
		try {
			Calendarworkinghours instance = entityManager.find(
					Calendarworkinghours.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
