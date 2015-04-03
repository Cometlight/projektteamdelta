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
 * Home object for domain model class Examinationprotocolservicecode.
 * @see .Examinationprotocolservicecode
 * @author Hibernate Tools
 */
public class ExaminationprotocolservicecodeHome {

	private static final Log log = LogFactory
			.getLog(ExaminationprotocolservicecodeHome.class);

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

	public void persist(Examinationprotocolservicecode transientInstance) {
		log.debug("persisting Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationprotocolservicecode instance) {
		log.debug("attaching dirty Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationprotocolservicecode instance) {
		log.debug("attaching clean Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationprotocolservicecode persistentInstance) {
		log.debug("deleting Examinationprotocolservicecode instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Examinationprotocolservicecode merge(
			Examinationprotocolservicecode detachedInstance) {
		log.debug("merging Examinationprotocolservicecode instance");
		try {
			Examinationprotocolservicecode result = (Examinationprotocolservicecode) sessionFactory
					.getCurrentSession().merge(detachedInstance);
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
			Examinationprotocolservicecode instance = (Examinationprotocolservicecode) sessionFactory
					.getCurrentSession().get("Examinationprotocolservicecode",
							id);
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

	public List<Examinationprotocolservicecode> findByExample(
			Examinationprotocolservicecode instance) {
		log.debug("finding Examinationprotocolservicecode instance by example");
		try {
			List<Examinationprotocolservicecode> results = (List<Examinationprotocolservicecode>) sessionFactory
					.getCurrentSession()
					.createCriteria("Examinationprotocolservicecode")
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
