// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Orthoptist.
 * @see .Orthoptist
 * @author Hibernate Tools
 */
@Stateless
public class OrthoptistHome {

	private static final Log log = LogFactory.getLog(OrthoptistHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Orthoptist transientInstance) {
		log.debug("persisting Orthoptist instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Orthoptist persistentInstance) {
		log.debug("removing Orthoptist instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Orthoptist merge(Orthoptist detachedInstance) {
		log.debug("merging Orthoptist instance");
		try {
			Orthoptist result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Orthoptist findById(Integer id) {
		log.debug("getting Orthoptist instance with id: " + id);
		try {
			Orthoptist instance = entityManager.find(Orthoptist.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
