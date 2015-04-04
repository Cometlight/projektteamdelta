package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1


import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Administrator;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Administrator.
 * @see .Administrator
 * @author Hibernate Tools
 */
public class AdministratorDao {

	private static final Logger _logger = LogManager.getLogger(AdministratorDao.class.getName());
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

	public void persist(Administrator transientInstance) {
		_logger.debug("persisting Administrator instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Administrator instance) {
		_logger.debug("attaching dirty Administrator instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Administrator instance) {
		_logger.debug("attaching clean Administrator instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Administrator persistentInstance) {
		_logger.debug("deleting Administrator instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Administrator merge(Administrator detachedInstance) {
		_logger.debug("merging Administrator instance");
		try {
			Administrator result = (Administrator) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Administrator findById(java.lang.Integer id) {
		_logger.debug("getting Administrator instance with id: " + id);
		try {
			Administrator instance = (Administrator) _sessionFactory
					.getCurrentSession().get("Administrator", id);
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

	public List<Administrator> findByExample(Administrator instance) {
		_logger.debug("finding Administrator instance by example");
		try {
			List<Administrator> results = (List<Administrator>) _sessionFactory
					.getCurrentSession().createCriteria("Administrator")
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
