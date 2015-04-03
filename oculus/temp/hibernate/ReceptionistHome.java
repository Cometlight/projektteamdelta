// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Receptionist.
 * @see .Receptionist
 * @author Hibernate Tools
 */
@Stateless
public class ReceptionistHome {

	private static final Log log = LogFactory.getLog(ReceptionistHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Receptionist transientInstance) {
		log.debug("persisting Receptionist instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Receptionist persistentInstance) {
		log.debug("removing Receptionist instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Receptionist merge(Receptionist detachedInstance) {
		log.debug("merging Receptionist instance");
		try {
			Receptionist result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Receptionist findById(Integer id) {
		log.debug("getting Receptionist instance with id: " + id);
		try {
			Receptionist instance = entityManager.find(Receptionist.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
