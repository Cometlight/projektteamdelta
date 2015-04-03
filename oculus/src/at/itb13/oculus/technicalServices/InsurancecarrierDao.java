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
 * Home object for domain model class Insurancecarrier.
 * @see .Insurancecarrier
 * @author Hibernate Tools
 */
public class InsurancecarrierDao {

	private static final Log log = LogFactory
			.getLog(InsurancecarrierDao.class);

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

	public void persist(Insurancecarrier transientInstance) {
		log.debug("persisting Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Insurancecarrier instance) {
		log.debug("attaching dirty Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Insurancecarrier instance) {
		log.debug("attaching clean Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Insurancecarrier persistentInstance) {
		log.debug("deleting Insurancecarrier instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Insurancecarrier merge(Insurancecarrier detachedInstance) {
		log.debug("merging Insurancecarrier instance");
		try {
			Insurancecarrier result = (Insurancecarrier) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Insurancecarrier findById(java.lang.String id) {
		log.debug("getting Insurancecarrier instance with id: " + id);
		try {
			Insurancecarrier instance = (Insurancecarrier) sessionFactory
					.getCurrentSession().get("Insurancecarrier", id);
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

	public List<Insurancecarrier> findByExample(Insurancecarrier instance) {
		log.debug("finding Insurancecarrier instance by example");
		try {
			List<Insurancecarrier> results = (List<Insurancecarrier>) sessionFactory
					.getCurrentSession().createCriteria("Insurancecarrier")
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
