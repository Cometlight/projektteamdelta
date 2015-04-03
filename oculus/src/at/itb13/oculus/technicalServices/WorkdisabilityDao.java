package at.itb13.oculus.technicalServices;

import java.util.List;

import javax.naming.InitialContext;

import at.itb13.oculus.domain.Workdisability;

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
public class WorkdisabilityDao {

	private static final Logger log = LogManager.getLogger(WorkdisabilityDao.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Workdisability transientInstance) {
		log.debug("persisting Workdisability instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Workdisability instance) {
		log.debug("attaching dirty Workdisability instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Workdisability instance) {
		log.debug("attaching clean Workdisability instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Workdisability persistentInstance) {
		log.debug("deleting Workdisability instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Workdisability merge(Workdisability detachedInstance) {
		log.debug("merging Workdisability instance");
		try {
			Workdisability result = (Workdisability) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Workdisability findById(java.lang.Integer id) {
		log.debug("getting Workdisability instance with id: " + id);
		try {
			Workdisability instance = (Workdisability) sessionFactory
					.getCurrentSession().get("Workdisability", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Workdisability> findByExample(Workdisability instance) {
		log.debug("finding Workdisability instance by example");
		try {
			List<Workdisability> results = (List<Workdisability>) sessionFactory
					.getCurrentSession().createCriteria("Workdisability")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
