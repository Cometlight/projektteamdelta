package at.itb13.oculus.technicalServices.dao;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Eventtype;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Eventtype.
 * @see .Eventtype
 * @author Hibernate Tools
 */
public class EventtypeDao {

	private static final Logger _logger = LogManager.getLogger(EventtypeDao.class.getName());
	
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

	public void persist(Eventtype transientInstance) {
		_logger.debug("persisting Eventtype instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Eventtype instance) {
		_logger.debug("attaching dirty Eventtype instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Eventtype instance) {
		_logger.debug("attaching clean Eventtype instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Eventtype persistentInstance) {
		_logger.debug("deleting Eventtype instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Eventtype merge(Eventtype detachedInstance) {
		_logger.debug("merging Eventtype instance");
		try {
			Eventtype result = (Eventtype) _sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Eventtype findById(java.lang.Integer id) {
		_logger.debug("getting Eventtype instance with id: " + id);
		try {
			Eventtype instance = (Eventtype) _sessionFactory.getCurrentSession()
					.get("Eventtype", id);
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

	public List<Eventtype> findByExample(Eventtype instance) {
		_logger.debug("finding Eventtype instance by example");
		try {
			List<Eventtype> results = (List<Eventtype>) _sessionFactory
					.getCurrentSession().createCriteria("Eventtype")
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
