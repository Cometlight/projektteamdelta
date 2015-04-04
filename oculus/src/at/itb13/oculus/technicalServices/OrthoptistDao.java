package at.itb13.oculus.technicalServices;

import at.itb13.oculus.domain.Orthoptist;

import java.util.List;

import javax.naming.InitialContext;

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
public class OrthoptistDao {

	private static final Logger _logger = LogManager.getLogger(OrthoptistDao.class.getName());
	
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

	public void persist(Orthoptist transientInstance) {
		_logger.debug("persisting Orthoptist instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Orthoptist instance) {
		_logger.debug("attaching dirty Orthoptist instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orthoptist instance) {
		_logger.debug("attaching clean Orthoptist instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Orthoptist persistentInstance) {
		_logger.debug("deleting Orthoptist instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Orthoptist merge(Orthoptist detachedInstance) {
		_logger.debug("merging Orthoptist instance");
		try {
			Orthoptist result = (Orthoptist) _sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Orthoptist findById(java.lang.Integer id) {
		_logger.debug("getting Orthoptist instance with id: " + id);
		try {
			Orthoptist instance = (Orthoptist) _sessionFactory
					.getCurrentSession().get("Orthoptist", id);
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

	public List<Orthoptist> findByExample(Orthoptist instance) {
		_logger.debug("finding Orthoptist instance by example");
		try {
			List<Orthoptist> results = (List<Orthoptist>) _sessionFactory
					.getCurrentSession().createCriteria("Orthoptist")
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
