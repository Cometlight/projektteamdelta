package at.itb13.oculus.technicalServices;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Doctor.
 * @see .Doctor
 * @author Hibernate Tools
 */
public class DoctorDao {

	private static final Logger logger = LogManager.getLogger(DoctorDao.class.getName());
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Doctor transientInstance) {
		logger.debug("persisting Doctor instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Doctor instance) {
		logger.debug("attaching dirty Doctor instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Doctor instance) {
		logger.debug("attaching clean Doctor instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Doctor persistentInstance) {
		logger.debug("deleting Doctor instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Doctor merge(Doctor detachedInstance) {
		logger.debug("merging Doctor instance");
		try {
			Doctor result = (Doctor) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Doctor findById(java.lang.Integer id) {
		logger.debug("getting Doctor instance with id: " + id);
		try {
			Doctor instance = (Doctor) sessionFactory.getCurrentSession().get(
					"Doctor", id);
			if (instance == null) {
				logger.debug("get successful, no instance found");
			} else {
				logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public List<Doctor> findByExample(Doctor instance) {
		logger.debug("finding Doctor instance by example");
		try {
			List<Doctor> results = (List<Doctor>) sessionFactory
					.getCurrentSession().createCriteria("Doctor")
					.add(create(instance)).list();
			logger.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error("find by example failed", re);
			throw re;
		}
	}
}
