package at.itb13.oculus.technicalServices.daoOld;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.ExaminationResult;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Examinationresult.
 * @see .Examinationresult
 * @author Hibernate Tools
 */
public class ExaminationresultDao {

	private static final Logger _logger = LogManager.getLogger(ExaminationresultDao.class.getName());

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

	public void persist(ExaminationResult transientInstance) {
		_logger.debug("persisting Examinationresult instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ExaminationResult instance) {
		_logger.debug("attaching dirty Examinationresult instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExaminationResult instance) {
		_logger.debug("attaching clean Examinationresult instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ExaminationResult persistentInstance) {
		_logger.debug("deleting Examinationresult instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public ExaminationResult merge(ExaminationResult detachedInstance) {
		_logger.debug("merging Examinationresult instance");
		try {
			ExaminationResult result = (ExaminationResult) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public ExaminationResult findById(java.lang.Integer id) {
		_logger.debug("getting Examinationresult instance with id: " + id);
		try {
			ExaminationResult instance = (ExaminationResult) _sessionFactory
					.getCurrentSession().get("Examinationresult", id);
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

	public List<ExaminationResult> findByExample(ExaminationResult instance) {
		_logger.debug("finding Examinationresult instance by example");
		try {
			List<ExaminationResult> results = (List<ExaminationResult>) _sessionFactory
					.getCurrentSession().createCriteria("Examinationresult")
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
