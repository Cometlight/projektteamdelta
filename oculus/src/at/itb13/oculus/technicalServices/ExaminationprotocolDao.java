package at.itb13.oculus.technicalServices;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.Examinationprotocol;
import static org.hibernate.criterion.Example.create;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class ExaminationprotocolDao {

	private static final Logger logger = LogManager.getLogger(ExaminationprotocolDao.class.getName());
	
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

	public void persist(Examinationprotocol transientInstance) {
		logger.debug("persisting Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationprotocol instance) {
		logger.debug("attaching dirty Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationprotocol instance) {
		logger.debug("attaching clean Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationprotocol persistentInstance) {
		logger.debug("deleting Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Examinationprotocol merge(Examinationprotocol detachedInstance) {
		logger.debug("merging Examinationprotocol instance");
		try {
			Examinationprotocol result = (Examinationprotocol) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocol findById(java.lang.Integer id) {
		logger.debug("getting Examinationprotocol instance with id: " + id);
		try {
			Examinationprotocol instance = (Examinationprotocol) sessionFactory
					.getCurrentSession().get("Examinationprotocol", id);
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

	public List<Examinationprotocol> findByExample(Examinationprotocol instance) {
		logger.debug("finding Examinationprotocol instance by example");
		try {
			List<Examinationprotocol> results = (List<Examinationprotocol>) sessionFactory
					.getCurrentSession().createCriteria("Examinationprotocol")
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
