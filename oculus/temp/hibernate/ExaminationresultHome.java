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
 * Home object for domain model class Examinationresult.
 * @see .Examinationresult
 * @author Hibernate Tools
 */
public class ExaminationresultHome {

	private static final Log log = LogFactory
			.getLog(ExaminationresultHome.class);

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

	public void persist(Examinationresult transientInstance) {
		log.debug("persisting Examinationresult instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Examinationresult instance) {
		log.debug("attaching dirty Examinationresult instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Examinationresult instance) {
		log.debug("attaching clean Examinationresult instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Examinationresult persistentInstance) {
		log.debug("deleting Examinationresult instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Examinationresult merge(Examinationresult detachedInstance) {
		log.debug("merging Examinationresult instance");
		try {
			Examinationresult result = (Examinationresult) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Examinationresult findById(java.lang.Integer id) {
		log.debug("getting Examinationresult instance with id: " + id);
		try {
			Examinationresult instance = (Examinationresult) sessionFactory
					.getCurrentSession().get("Examinationresult", id);
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

	public List<Examinationresult> findByExample(Examinationresult instance) {
		log.debug("finding Examinationresult instance by example");
		try {
			List<Examinationresult> results = (List<Examinationresult>) sessionFactory
					.getCurrentSession().createCriteria("Examinationresult")
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
