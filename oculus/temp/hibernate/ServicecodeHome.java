// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Servicecode.
 * @see .Servicecode
 * @author Hibernate Tools
 */
@Stateless
public class ServicecodeHome {

	private static final Log log = LogFactory.getLog(ServicecodeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Servicecode transientInstance) {
		log.debug("persisting Servicecode instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Servicecode persistentInstance) {
		log.debug("removing Servicecode instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Servicecode merge(Servicecode detachedInstance) {
		log.debug("merging Servicecode instance");
		try {
			Servicecode result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicecode findById(Integer id) {
		log.debug("getting Servicecode instance with id: " + id);
		try {
			Servicecode instance = entityManager.find(Servicecode.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
