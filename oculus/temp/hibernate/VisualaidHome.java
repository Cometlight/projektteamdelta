// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Visualaid.
 * @see .Visualaid
 * @author Hibernate Tools
 */
@Stateless
public class VisualaidHome {

	private static final Log log = LogFactory.getLog(VisualaidHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Visualaid transientInstance) {
		log.debug("persisting Visualaid instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Visualaid persistentInstance) {
		log.debug("removing Visualaid instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Visualaid merge(Visualaid detachedInstance) {
		log.debug("merging Visualaid instance");
		try {
			Visualaid result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Visualaid findById(Integer id) {
		log.debug("getting Visualaid instance with id: " + id);
		try {
			Visualaid instance = entityManager.find(Visualaid.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
