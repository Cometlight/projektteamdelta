// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Patient.
 * @see .Patient
 * @author Hibernate Tools
 */
@Stateless
public class PatientHome {

	private static final Log log = LogFactory.getLog(PatientHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Patient transientInstance) {
		log.debug("persisting Patient instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Patient persistentInstance) {
		log.debug("removing Patient instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Patient merge(Patient detachedInstance) {
		log.debug("merging Patient instance");
		try {
			Patient result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Patient findById(Integer id) {
		log.debug("getting Patient instance with id: " + id);
		try {
			Patient instance = entityManager.find(Patient.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
