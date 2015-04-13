// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Examinationprotocol.
 * @see .Examinationprotocol
 * @author Hibernate Tools
 */
@Stateless
public class ExaminationprotocolHome {

	private static final Log log = LogFactory
			.getLog(ExaminationprotocolHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Examinationprotocol transientInstance) {
		log.debug("persisting Examinationprotocol instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Examinationprotocol persistentInstance) {
		log.debug("removing Examinationprotocol instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Examinationprotocol merge(Examinationprotocol detachedInstance) {
		log.debug("merging Examinationprotocol instance");
		try {
			Examinationprotocol result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocol findById(Integer id) {
		log.debug("getting Examinationprotocol instance with id: " + id);
		try {
			Examinationprotocol instance = entityManager.find(
					Examinationprotocol.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
