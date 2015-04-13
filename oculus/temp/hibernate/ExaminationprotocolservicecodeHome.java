// default package
// Generated 03.04.2015 16:43:15 by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Examinationprotocolservicecode.
 * @see .Examinationprotocolservicecode
 * @author Hibernate Tools
 */
@Stateless
public class ExaminationprotocolservicecodeHome {

	private static final Log log = LogFactory
			.getLog(ExaminationprotocolservicecodeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Examinationprotocolservicecode transientInstance) {
		log.debug("persisting Examinationprotocolservicecode instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Examinationprotocolservicecode persistentInstance) {
		log.debug("removing Examinationprotocolservicecode instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Examinationprotocolservicecode merge(
			Examinationprotocolservicecode detachedInstance) {
		log.debug("merging Examinationprotocolservicecode instance");
		try {
			Examinationprotocolservicecode result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocolservicecode findById(
			ExaminationprotocolservicecodeId id) {
		log.debug("getting Examinationprotocolservicecode instance with id: "
				+ id);
		try {
			Examinationprotocolservicecode instance = entityManager.find(
					Examinationprotocolservicecode.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
