package at.itb13.oculus.technicalServices.daoOld;

import java.util.List;

import javax.naming.InitialContext;

import at.itb13.oculus.domain.VisualAid;

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
public class VisualaidDao {

	private static final Logger _logger = LogManager.getLogger(VisualaidDao.class.getName());

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

	public void persist(VisualAid transientInstance) {
		_logger.debug("persisting Visualaid instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VisualAid instance) {
		_logger.debug("attaching dirty Visualaid instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VisualAid instance) {
		_logger.debug("attaching clean Visualaid instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VisualAid persistentInstance) {
		_logger.debug("deleting Visualaid instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public VisualAid merge(VisualAid detachedInstance) {
		_logger.debug("merging Visualaid instance");
		try {
			VisualAid result = (VisualAid) _sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public VisualAid findById(java.lang.Integer id) {
		_logger.debug("getting Visualaid instance with id: " + id);
		try {
			VisualAid instance = (VisualAid) _sessionFactory.getCurrentSession()
					.get("Visualaid", id);
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

	public List<VisualAid> findByExample(VisualAid instance) {
		_logger.debug("finding Visualaid instance by example");
		try {
			List<VisualAid> results = (List<VisualAid>) _sessionFactory
					.getCurrentSession().createCriteria("Visualaid")
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
