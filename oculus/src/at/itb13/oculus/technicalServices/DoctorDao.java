package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Doctor;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Doctor.
 * @see .Doctor
 * @author Hibernate Tools
 */
public class DoctorDao {

	private static final Logger _logger = LogManager.getLogger(DoctorDao.class.getName());
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

	public void persist(Doctor transientInstance) {
		_logger.debug("persisting Doctor instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Doctor instance) {
		_logger.debug("attaching dirty Doctor instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Doctor instance) {
		_logger.debug("attaching clean Doctor instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Doctor persistentInstance) {
		_logger.debug("deleting Doctor instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public Doctor merge(Doctor detachedInstance) {
		_logger.debug("merging Doctor instance");
		try {
			Doctor result = (Doctor) _sessionFactory.getCurrentSession().merge(
					detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public Doctor findById(java.lang.Integer id) {
		_logger.debug("getting Doctor instance with id: " + id);
		try {
			Doctor instance = (Doctor) _sessionFactory.getCurrentSession().get(
					"Doctor", id);
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

	public List<Doctor> findByExample(Doctor instance) {
		_logger.debug("finding Doctor instance by example");
		try {
			List<Doctor> results = (List<Doctor>) _sessionFactory
					.getCurrentSession().createCriteria("Doctor")
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
