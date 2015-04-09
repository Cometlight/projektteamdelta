// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Insurancecarrier.
 * @see .Insurancecarrier
 * @author Hibernate Tools
 */
@Stateless
public class InsurancecarrierHome {

	private static final Log log = LogFactory
			.getLog(InsurancecarrierHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Insurancecarrier transientInstance) {
		log.debug("persisting Insurancecarrier instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Insurancecarrier persistentInstance) {
		log.debug("removing Insurancecarrier instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Insurancecarrier merge(Insurancecarrier detachedInstance) {
		log.debug("merging Insurancecarrier instance");
		try {
			Insurancecarrier result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Insurancecarrier findById(String id) {
		log.debug("getting Insurancecarrier instance with id: " + id);
		try {
			Insurancecarrier instance = entityManager.find(
					Insurancecarrier.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
