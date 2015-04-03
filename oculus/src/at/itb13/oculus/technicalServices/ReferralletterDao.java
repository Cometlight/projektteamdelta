package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Referralletter.
 * @see .Referralletter
 * @author Hibernate Tools
 */
public class ReferralletterDao {

	private static final Logger logger = LogManager.getLogger(ReferralletterDao.class.getName());
	
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Referralletter transientInstance) {
		logger.debug("persisting Referralletter instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Referralletter instance) {
		logger.debug("attaching dirty Referralletter instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Referralletter instance) {
		logger.debug("attaching clean Referralletter instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Referralletter persistentInstance) {
		logger.debug("deleting Referralletter instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Referralletter merge(Referralletter detachedInstance) {
		logger.debug("merging Referralletter instance");
		try {
			Referralletter result = (Referralletter) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Referralletter findById(java.lang.Integer id) {
		logger.debug("getting Referralletter instance with id: " + id);
		try {
			Referralletter instance = (Referralletter) sessionFactory
					.getCurrentSession().get("Referralletter", id);
			if (instance == null) {
				logger.debug("get successful, no instance found");
			} else {
				logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public List<Referralletter> findByExample(Referralletter instance) {
		logger.debug("finding Referralletter instance by example");
		try {
			List<Referralletter> results = (List<Referralletter>) sessionFactory
					.getCurrentSession().createCriteria("Referralletter")
					.add(create(instance)).list();
			logger.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error("find by example failed", re);
			throw re;
		}
	}
}
