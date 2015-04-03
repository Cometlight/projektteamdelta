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
 * Home object for domain model class Examinationprotocolservicecode.
 * @see .Examinationprotocolservicecode
 * @author Hibernate Tools
 */
public class ExaminationprotocolservicecodeDao {

	private static final Logger logger = LogManager.getLogger(ExaminationprotocolservicecodeDao.class.getName());
	
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

	public void persist(Examinationprotocolservicecode transientInstance) {
		logger.debug("persisting Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationprotocolservicecode instance) {
		logger.debug("attaching dirty Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationprotocolservicecode instance) {
		logger.debug("attaching clean Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationprotocolservicecode persistentInstance) {
		logger.debug("deleting Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Examinationprotocolservicecode merge(
			Examinationprotocolservicecode detachedInstance) {
		logger.debug("merging Examinationprotocolservicecode instance");
		try {
			Examinationprotocolservicecode result = (Examinationprotocolservicecode) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocolservicecode findById(
			ExaminationprotocolservicecodeId id) {
		logger.debug("getting Examinationprotocolservicecode instance with id: "
				+ id);
		try {
			Examinationprotocolservicecode instance = (Examinationprotocolservicecode) sessionFactory
					.getCurrentSession().get("Examinationprotocolservicecode",
							id);
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

	public List<Examinationprotocolservicecode> findByExample(
			Examinationprotocolservicecode instance) {
		logger.debug("finding Examinationprotocolservicecode instance by example");
		try {
			List<Examinationprotocolservicecode> results = (List<Examinationprotocolservicecode>) sessionFactory
					.getCurrentSession()
					.createCriteria("Examinationprotocolservicecode")
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
