package at.itb13.oculus.technicalServices.daoOld;

import java.util.List;

import javax.naming.InitialContext;

import at.itb13.oculus.domain.Userpermission;
import at.itb13.oculus.domain.UserpermissionId;

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
public class UserpermissionDao {

	private static final Logger _logger = LogManager.getLogger(UserpermissionDao.class.getName());

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

	public void persist(Userpermission transientInstance) {
		_logger.debug("persisting Userpermission instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Userpermission instance) {
		_logger.debug("attaching dirty Userpermission instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userpermission instance) {
		_logger.debug("attaching clean Userpermission instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Userpermission persistentInstance) {
		_logger.debug("deleting Userpermission instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Userpermission merge(Userpermission detachedInstance) {
		_logger.debug("merging Userpermission instance");
		try {
			Userpermission result = (Userpermission) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Userpermission findById(UserpermissionId id) {
		_logger.debug("getting Userpermission instance with id: " + id);
		try {
			Userpermission instance = (Userpermission) _sessionFactory
					.getCurrentSession().get("Userpermission", id);
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

	public List<Userpermission> findByExample(Userpermission instance) {
		_logger.debug("finding Userpermission instance by example");
		try {
			List<Userpermission> results = (List<Userpermission>) _sessionFactory
					.getCurrentSession().createCriteria("Userpermission")
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
