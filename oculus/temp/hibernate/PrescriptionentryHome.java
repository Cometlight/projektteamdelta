// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Prescriptionentry.
 * @see .Prescriptionentry
 * @author Hibernate Tools
 */
@Stateless
public class PrescriptionentryHome {

	private static final Log log = LogFactory
			.getLog(PrescriptionentryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Prescriptionentry transientInstance) {
		log.debug("persisting Prescriptionentry instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Prescriptionentry persistentInstance) {
		log.debug("removing Prescriptionentry instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Prescriptionentry merge(Prescriptionentry detachedInstance) {
		log.debug("merging Prescriptionentry instance");
		try {
			Prescriptionentry result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Prescriptionentry findById(Integer id) {
		log.debug("getting Prescriptionentry instance with id: " + id);
		try {
			Prescriptionentry instance = entityManager.find(
					Prescriptionentry.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
