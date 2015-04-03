// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Userpermission.
 * @see .Userpermission
 * @author Hibernate Tools
 */
@Stateless
public class UserpermissionHome {

	private static final Log log = LogFactory.getLog(UserpermissionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Userpermission transientInstance) {
		log.debug("persisting Userpermission instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Userpermission persistentInstance) {
		log.debug("removing Userpermission instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Userpermission merge(Userpermission detachedInstance) {
		log.debug("merging Userpermission instance");
		try {
			Userpermission result = entityManager.merge(detachedInstance);
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
			Userpermission instance = entityManager.find(Userpermission.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
