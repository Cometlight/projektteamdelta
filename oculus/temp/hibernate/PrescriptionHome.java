// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Prescription.
 * @see .Prescription
 * @author Hibernate Tools
 */
@Stateless
public class PrescriptionHome {

	private static final Log log = LogFactory.getLog(PrescriptionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Prescription transientInstance) {
		log.debug("persisting Prescription instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Prescription persistentInstance) {
		log.debug("removing Prescription instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Prescription merge(Prescription detachedInstance) {
		log.debug("merging Prescription instance");
		try {
			Prescription result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Prescription findById(Integer id) {
		log.debug("getting Prescription instance with id: " + id);
		try {
			Prescription instance = entityManager.find(Prescription.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
