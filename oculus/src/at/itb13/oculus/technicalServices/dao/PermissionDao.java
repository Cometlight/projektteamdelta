package at.itb13.oculus.technicalServices.dao;

import java.util.List;

import javax.naming.InitialContext;

import at.itb13.oculus.domain.Permission;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class PermissionDao {

	private static final Logger _logger = LogManager.getLogger(PermissionDao.class.getName());

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

	public void persist(Permission transientInstance) {
		_logger.debug("persisting Permission instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Permission instance) {
		_logger.debug("attaching dirty Permission instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Permission instance) {
		_logger.debug("attaching clean Permission instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Permission persistentInstance) {
		_logger.debug("deleting Permission instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Permission merge(Permission detachedInstance) {
		_logger.debug("merging Permission instance");
		try {
			Permission result = (Permission) _sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Permission findById(java.lang.Integer id) {
		_logger.debug("getting Permission instance with id: " + id);
		try {
			Permission instance = (Permission) _sessionFactory
					.getCurrentSession().get("Permission", id);
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

	public List<Permission> findByExample(Permission instance) {
		_logger.debug("finding Permission instance by example");
		try {
			List<Permission> results = (List<Permission>) _sessionFactory
					.getCurrentSession().createCriteria("Permission")
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
