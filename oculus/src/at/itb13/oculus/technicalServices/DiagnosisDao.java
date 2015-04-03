package at.itb13.oculus.technicalServices;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Diagnosis;
import static org.hibernate.criterion.Example.create;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class DiagnosisDao {

	private static final Logger logger = LogManager.getLogger(DiagnosisDao.class.getName());
	
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

	public void persist(Diagnosis transientInstance) {
		logger.debug("persisting Diagnosis instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Diagnosis instance) {
		logger.debug("attaching dirty Diagnosis instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Diagnosis instance) {
		logger.debug("attaching clean Diagnosis instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Diagnosis persistentInstance) {
		logger.debug("deleting Diagnosis instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Diagnosis merge(Diagnosis detachedInstance) {
		logger.debug("merging Diagnosis instance");
		try {
			Diagnosis result = (Diagnosis) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Diagnosis findById(java.lang.Integer id) {
		logger.debug("getting Diagnosis instance with id: " + id);
		try {
			Diagnosis instance = (Diagnosis) sessionFactory.getCurrentSession()
					.get("Diagnosis", id);
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

	public List<Diagnosis> findByExample(Diagnosis instance) {
		logger.debug("finding Diagnosis instance by example");
		try {
			List<Diagnosis> results = (List<Diagnosis>) sessionFactory
					.getCurrentSession().createCriteria("Diagnosis")
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
