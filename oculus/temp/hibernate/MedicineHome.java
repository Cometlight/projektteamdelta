// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Medicine.
 * @see .Medicine
 * @author Hibernate Tools
 */
@Stateless
public class MedicineHome {

	private static final Log log = LogFactory.getLog(MedicineHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Medicine transientInstance) {
		log.debug("persisting Medicine instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Medicine persistentInstance) {
		log.debug("removing Medicine instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Medicine merge(Medicine detachedInstance) {
		log.debug("merging Medicine instance");
		try {
			Medicine result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Medicine findById(Integer id) {
		log.debug("getting Medicine instance with id: " + id);
		try {
			Medicine instance = entityManager.find(Medicine.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
