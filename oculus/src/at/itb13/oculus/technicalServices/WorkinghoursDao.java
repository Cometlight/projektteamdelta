package at.itb13.oculus.technicalServices;

import java.util.List;

import javax.naming.InitialContext;

import at.itb13.oculus.domain.Workinghours;

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
public class WorkinghoursDao {

	private static final Logger log = LogManager.getLogger(WorkinghoursDao.class.getName());

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

	public void persist(Workinghours transientInstance) {
		log.debug("persisting Workinghours instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Workinghours instance) {
		log.debug("attaching dirty Workinghours instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Workinghours instance) {
		log.debug("attaching clean Workinghours instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Workinghours persistentInstance) {
		log.debug("deleting Workinghours instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Workinghours merge(Workinghours detachedInstance) {
		log.debug("merging Workinghours instance");
		try {
			Workinghours result = (Workinghours) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Workinghours findById(java.lang.Integer id) {
		log.debug("getting Workinghours instance with id: " + id);
		try {
			Workinghours instance = (Workinghours) sessionFactory
					.getCurrentSession().get("Workinghours", id);
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

	public List<Workinghours> findByExample(Workinghours instance) {
		log.debug("finding Workinghours instance by example");
		try {
			List<Workinghours> results = (List<Workinghours>) sessionFactory
					.getCurrentSession().createCriteria("Workinghours")
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
