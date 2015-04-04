package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Examinationprotocol;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Examinationprotocol.
 * @see .Examinationprotocol
 * @author Hibernate Tools
 */
public class ExaminationprotocolDao {

	private static final Logger _logger = LogManager.getLogger(ExaminationprotocolDao.class.getName());
	
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

	public void persist(Examinationprotocol transientInstance) {
		_logger.debug("persisting Examinationprotocol instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationprotocol instance) {
		_logger.debug("attaching dirty Examinationprotocol instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationprotocol instance) {
		_logger.debug("attaching clean Examinationprotocol instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationprotocol persistentInstance) {
		_logger.debug("deleting Examinationprotocol instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Examinationprotocol merge(Examinationprotocol detachedInstance) {
		_logger.debug("merging Examinationprotocol instance");
		try {
			Examinationprotocol result = (Examinationprotocol) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocol findById(java.lang.Integer id) {
		_logger.debug("getting Examinationprotocol instance with id: " + id);
		try {
			Examinationprotocol instance = (Examinationprotocol) _sessionFactory
					.getCurrentSession().get("Examinationprotocol", id);
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

	public List<Examinationprotocol> findByExample(Examinationprotocol instance) {
		_logger.debug("finding Examinationprotocol instance by example");
		try {
			List<Examinationprotocol> results = (List<Examinationprotocol>) _sessionFactory
					.getCurrentSession().createCriteria("Examinationprotocol")
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
