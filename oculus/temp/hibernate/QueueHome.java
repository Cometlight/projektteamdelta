// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Queue.
 * @see .Queue
 * @author Hibernate Tools
 */
@Stateless
public class QueueHome {

	private static final Log log = LogFactory.getLog(QueueHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Queue transientInstance) {
		log.debug("persisting Queue instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Queue persistentInstance) {
		log.debug("removing Queue instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Queue merge(Queue detachedInstance) {
		log.debug("merging Queue instance");
		try {
			Queue result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Queue findById(Integer id) {
		log.debug("getting Queue instance with id: " + id);
		try {
			Queue instance = entityManager.find(Queue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
