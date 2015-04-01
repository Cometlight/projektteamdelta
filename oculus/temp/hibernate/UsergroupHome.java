// default package
// Generated 01.04.2015 15:28:33 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Usergroup.
 * @see .Usergroup
 * @author Hibernate Tools
 */
@Stateless
public class UsergroupHome {

	private static final Log log = LogFactory.getLog(UsergroupHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Usergroup transientInstance) {
		log.debug("persisting Usergroup instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Usergroup persistentInstance) {
		log.debug("removing Usergroup instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Usergroup merge(Usergroup detachedInstance) {
		log.debug("merging Usergroup instance");
		try {
			Usergroup result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Usergroup findById(Integer id) {
		log.debug("getting Usergroup instance with id: " + id);
		try {
			Usergroup instance = entityManager.find(Usergroup.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
