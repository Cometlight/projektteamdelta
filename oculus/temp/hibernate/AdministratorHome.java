// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Administrator.
 * @see .Administrator
 * @author Hibernate Tools
 */
@Stateless
public class AdministratorHome {

	private static final Log log = LogFactory.getLog(AdministratorHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Administrator transientInstance) {
		log.debug("persisting Administrator instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Administrator persistentInstance) {
		log.debug("removing Administrator instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Administrator merge(Administrator detachedInstance) {
		log.debug("merging Administrator instance");
		try {
			Administrator result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Administrator findById(Integer id) {
		log.debug("getting Administrator instance with id: " + id);
		try {
			Administrator instance = entityManager
					.find(Administrator.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
