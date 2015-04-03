// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Diagnosis.
 * @see .Diagnosis
 * @author Hibernate Tools
 */
@Stateless
public class DiagnosisHome {

	private static final Log log = LogFactory.getLog(DiagnosisHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Diagnosis transientInstance) {
		log.debug("persisting Diagnosis instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Diagnosis persistentInstance) {
		log.debug("removing Diagnosis instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Diagnosis merge(Diagnosis detachedInstance) {
		log.debug("merging Diagnosis instance");
		try {
			Diagnosis result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Diagnosis findById(Integer id) {
		log.debug("getting Diagnosis instance with id: " + id);
		try {
			Diagnosis instance = entityManager.find(Diagnosis.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
