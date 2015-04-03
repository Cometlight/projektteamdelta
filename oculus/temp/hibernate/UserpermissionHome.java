// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Userpermission.
 * @see .Userpermission
 * @author Hibernate Tools
 */
public class UserpermissionHome {

	private static final Log log = LogFactory.getLog(UserpermissionHome.class);

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
