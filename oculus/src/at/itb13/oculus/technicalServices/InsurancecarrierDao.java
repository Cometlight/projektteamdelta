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
 * Home object for domain model class Insurancecarrier.
 * @see .Insurancecarrier
 * @author Hibernate Tools
 */
public class InsurancecarrierDao {

	private static final Logger logger = LogManager.getLogger(InsurancecarrierDao.class.getName());

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

	public void persist(Insurancecarrier transientInstance) {
		logger.debug("persisting Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Insurancecarrier instance) {
		logger.debug("attaching dirty Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Insurancecarrier instance) {
		logger.debug("attaching clean Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Insurancecarrier persistentInstance) {
		logger.debug("deleting Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Insurancecarrier merge(Insurancecarrier detachedInstance) {
		logger.debug("merging Insurancecarrier instance");
		try {
			Insurancecarrier result = (Insurancecarrier) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Insurancecarrier findById(java.lang.String id) {
		logger.debug("getting Insurancecarrier instance with id: " + id);
		try {
			Insurancecarrier instance = (Insurancecarrier) sessionFactory
					.getCurrentSession().get("Insurancecarrier", id);
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

	public List<Insurancecarrier> findByExample(Insurancecarrier instance) {
		logger.debug("finding Insurancecarrier instance by example");
		try {
			List<Insurancecarrier> results = (List<Insurancecarrier>) sessionFactory
					.getCurrentSession().createCriteria("Insurancecarrier")
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
