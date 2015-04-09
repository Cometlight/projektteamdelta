// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Doctor.
 * @see .Doctor
 * @author Hibernate Tools
 */
@Stateless
public class DoctorHome {

	private static final Log log = LogFactory.getLog(DoctorHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Doctor transientInstance) {
		log.debug("persisting Doctor instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Doctor persistentInstance) {
		log.debug("removing Doctor instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Doctor merge(Doctor detachedInstance) {
		log.debug("merging Doctor instance");
		try {
			Doctor result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Doctor findById(Integer id) {
		log.debug("getting Doctor instance with id: " + id);
		try {
			Doctor instance = entityManager.find(Doctor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
