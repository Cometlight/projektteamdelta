package at.itb13.oculus.technicalServices.dao;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Insurancecarrier;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Insurancecarrier.
 * @see .Insurancecarrier
 * @author Hibernate Tools
 */
public class InsurancecarrierDao {

	private static final Logger _logger = LogManager.getLogger(InsurancecarrierDao.class.getName());

	private final SessionFactory _sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			_logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Insurancecarrier transientInstance) {
		_logger.debug("persisting Insurancecarrier instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Insurancecarrier instance) {
		_logger.debug("attaching dirty Insurancecarrier instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Insurancecarrier instance) {
		_logger.debug("attaching clean Insurancecarrier instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Insurancecarrier persistentInstance) {
		_logger.debug("deleting Insurancecarrier instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Insurancecarrier merge(Insurancecarrier detachedInstance) {
		_logger.debug("merging Insurancecarrier instance");
		try {
			Insurancecarrier result = (Insurancecarrier) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Insurancecarrier findById(java.lang.String id) {
		_logger.debug("getting Insurancecarrier instance with id: " + id);
		try {
			Insurancecarrier instance = (Insurancecarrier) _sessionFactory
					.getCurrentSession().get("Insurancecarrier", id);
			if (instance == null) {
				_logger.debug("get successful, no instance found");
			} else {
				_logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			_logger.error("get failed", re);
			throw re;
		}
	}

	public List<Insurancecarrier> findByExample(Insurancecarrier instance) {
		_logger.debug("finding Insurancecarrier instance by example");
		try {
			List<Insurancecarrier> results = (List<Insurancecarrier>) _sessionFactory
					.getCurrentSession().createCriteria("Insurancecarrier")
					.add(create(instance)).list();
			_logger.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			_logger.error("find by example failed", re);
			throw re;
		}
	}
}
