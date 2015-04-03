package at.itb13.oculus.technicalServices;

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

	private static final Logger log = LogManager.getLogger(UserpermissionDao.class.getName());

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

	public void persist(Userpermission transientInstance) {
		log.debug("persisting Userpermission instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Userpermission instance) {
		log.debug("attaching dirty Userpermission instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userpermission instance) {
		log.debug("attaching clean Userpermission instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Userpermission persistentInstance) {
		log.debug("deleting Userpermission instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userpermission merge(Userpermission detachedInstance) {
		log.debug("merging Userpermission instance");
		try {
			Userpermission result = (Userpermission) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Userpermission findById(UserpermissionId id) {
		log.debug("getting Userpermission instance with id: " + id);
		try {
			Userpermission instance = (Userpermission) sessionFactory
					.getCurrentSession().get("Userpermission", id);
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

	public List<Userpermission> findByExample(Userpermission instance) {
		log.debug("finding Userpermission instance by example");
		try {
			List<Userpermission> results = (List<Userpermission>) sessionFactory
					.getCurrentSession().createCriteria("Userpermission")
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
