// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Workdisability.
 * @see .Workdisability
 * @author Hibernate Tools
 */
@Stateless
public class WorkdisabilityHome {

	private static final Log log = LogFactory.getLog(WorkdisabilityHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Workdisability transientInstance) {
		log.debug("persisting Workdisability instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Workdisability persistentInstance) {
		log.debug("removing Workdisability instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Workdisability merge(Workdisability detachedInstance) {
		log.debug("merging Workdisability instance");
		try {
			Workdisability result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Workdisability findById(Integer id) {
		log.debug("getting Workdisability instance with id: " + id);
		try {
			Workdisability instance = entityManager.find(Workdisability.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
