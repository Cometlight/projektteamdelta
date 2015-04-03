package at.itb13.oculus.technicalServices;
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
 * Home object for domain model class Examinationprotocol.
 * @see .Examinationprotocol
 * @author Hibernate Tools
 */
public class ExaminationprotocolDao {

	private static final Log log = LogFactory
			.getLog(ExaminationprotocolDao.class);

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

	public void persist(Examinationprotocol transientInstance) {
		log.debug("persisting Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationprotocol instance) {
		log.debug("attaching dirty Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationprotocol instance) {
		log.debug("attaching clean Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationprotocol persistentInstance) {
		log.debug("deleting Examinationprotocol instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Examinationprotocol merge(Examinationprotocol detachedInstance) {
		log.debug("merging Examinationprotocol instance");
		try {
			Examinationprotocol result = (Examinationprotocol) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Examinationprotocol findById(java.lang.Integer id) {
		log.debug("getting Examinationprotocol instance with id: " + id);
		try {
			Examinationprotocol instance = (Examinationprotocol) sessionFactory
					.getCurrentSession().get("Examinationprotocol", id);
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

	public List<Examinationprotocol> findByExample(Examinationprotocol instance) {
		log.debug("finding Examinationprotocol instance by example");
		try {
			List<Examinationprotocol> results = (List<Examinationprotocol>) sessionFactory
					.getCurrentSession().createCriteria("Examinationprotocol")
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
