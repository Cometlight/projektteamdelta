// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Workinghours.
 * @see .Workinghours
 * @author Hibernate Tools
 */
@Stateless
public class WorkinghoursHome {

	private static final Log log = LogFactory.getLog(WorkinghoursHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Workinghours transientInstance) {
		log.debug("persisting Workinghours instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Workinghours persistentInstance) {
		log.debug("removing Workinghours instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Workinghours merge(Workinghours detachedInstance) {
		log.debug("merging Workinghours instance");
		try {
			Workinghours result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Workinghours findById(Integer id) {
		log.debug("getting Workinghours instance with id: " + id);
		try {
			Workinghours instance = entityManager.find(Workinghours.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
