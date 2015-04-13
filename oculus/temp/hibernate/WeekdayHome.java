// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Weekday.
 * @see .Weekday
 * @author Hibernate Tools
 */
@Stateless
public class WeekdayHome {

	private static final Log log = LogFactory.getLog(WeekdayHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Weekday transientInstance) {
		log.debug("persisting Weekday instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Weekday persistentInstance) {
		log.debug("removing Weekday instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Weekday merge(Weekday detachedInstance) {
		log.debug("merging Weekday instance");
		try {
			Weekday result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Weekday findById(String id) {
		log.debug("getting Weekday instance with id: " + id);
		try {
			Weekday instance = entityManager.find(Weekday.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
