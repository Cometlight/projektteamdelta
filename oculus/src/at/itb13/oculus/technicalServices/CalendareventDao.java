package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Calendarevent;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Calendarevent.
 * @see .Calendarevent
 * @author Hibernate Tools
 */
public class CalendareventDao {

	private static final Logger _logger = LogManager.getLogger(CalendareventDao.class.getName());
	
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

	public void persist(Calendarevent transientInstance) {
		_logger.debug("persisting Calendarevent instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Calendarevent instance) {
		_logger.debug("attaching dirty Calendarevent instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Calendarevent instance) {
		_logger.debug("attaching clean Calendarevent instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Calendarevent persistentInstance) {
		_logger.debug("deleting Calendarevent instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Calendarevent merge(Calendarevent detachedInstance) {
		_logger.debug("merging Calendarevent instance");
		try {
			Calendarevent result = (Calendarevent) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Calendarevent findById(java.lang.Integer id) {
		_logger.debug("getting Calendarevent instance with id: " + id);
		try {
			Calendarevent instance = (Calendarevent) _sessionFactory
					.getCurrentSession().get("Calendarevent", id);
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

	public List<Calendarevent> findByExample(Calendarevent instance) {
		_logger.debug("finding Calendarevent instance by example");
		try {
			List<Calendarevent> results = (List<Calendarevent>) _sessionFactory
					.getCurrentSession().createCriteria("Calendarevent")
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
