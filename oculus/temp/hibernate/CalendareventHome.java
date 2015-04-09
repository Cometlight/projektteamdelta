// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Calendarevent.
 * @see .Calendarevent
 * @author Hibernate Tools
 */
@Stateless
public class CalendareventHome {

	private static final Log log = LogFactory.getLog(CalendareventHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Calendarevent transientInstance) {
		log.debug("persisting Calendarevent instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Calendarevent persistentInstance) {
		log.debug("removing Calendarevent instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Calendarevent merge(Calendarevent detachedInstance) {
		log.debug("merging Calendarevent instance");
		try {
			Calendarevent result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Calendarevent findById(Integer id) {
		log.debug("getting Calendarevent instance with id: " + id);
		try {
			Calendarevent instance = entityManager
					.find(Calendarevent.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
