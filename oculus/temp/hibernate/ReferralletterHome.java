// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Referralletter.
 * @see .Referralletter
 * @author Hibernate Tools
 */
@Stateless
public class ReferralletterHome {

	private static final Log log = LogFactory.getLog(ReferralletterHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Referralletter transientInstance) {
		log.debug("persisting Referralletter instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Referralletter persistentInstance) {
		log.debug("removing Referralletter instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Referralletter merge(Referralletter detachedInstance) {
		log.debug("merging Referralletter instance");
		try {
			Referralletter result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Referralletter findById(Integer id) {
		log.debug("getting Referralletter instance with id: " + id);
		try {
			Referralletter instance = entityManager.find(Referralletter.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
