package at.itb13.oculus.technicalServices.daoOld;
// default package
// Generated 03.04.2015 15:26:51 by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import at.itb13.oculus.domain.ExaminationProtocolServiceCode;
import at.itb13.oculus.domain.ExaminationProtocolServiceCodeID;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Examinationprotocolservicecode.
 * @see .Examinationprotocolservicecode
 * @author Hibernate Tools
 */
public class ExaminationprotocolservicecodeDao {

	private static final Logger _logger = LogManager.getLogger(ExaminationprotocolservicecodeDao.class.getName());
	
	private final SessionFactory _sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			_logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(ExaminationProtocolServiceCode transientInstance) {
		_logger.debug("persisting Examinationprotocolservicecode instance");
		try {
			_sessionFactory.getCurrentSession().persist(transientInstance);
			_logger.debug("persist successful");
		} catch (RuntimeException re) {
			_logger.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ExaminationProtocolServiceCode instance) {
		_logger.debug("attaching dirty Examinationprotocolservicecode instance");
		try {
			_sessionFactory.getCurrentSession().saveOrUpdate(instance);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExaminationProtocolServiceCode instance) {
		_logger.debug("attaching clean Examinationprotocolservicecode instance");
		try {
			_sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			_logger.debug("attach successful");
		} catch (RuntimeException re) {
			_logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ExaminationProtocolServiceCode persistentInstance) {
		_logger.debug("deleting Examinationprotocolservicecode instance");
		try {
			_sessionFactory.getCurrentSession().delete(persistentInstance);
			_logger.debug("delete successful");
		} catch (RuntimeException re) {
			_logger.error("delete failed", re);
			throw re;
		}
	}

	public ExaminationProtocolServiceCode merge(
			ExaminationProtocolServiceCode detachedInstance) {
		_logger.debug("merging Examinationprotocolservicecode instance");
		try {
			ExaminationProtocolServiceCode result = (ExaminationProtocolServiceCode) _sessionFactory
					.getCurrentSession().merge(detachedInstance);
			_logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			_logger.error("merge failed", re);
			throw re;
		}
	}

	public ExaminationProtocolServiceCode findById(
			ExaminationProtocolServiceCodeID id) {
		_logger.debug("getting Examinationprotocolservicecode instance with id: "
				+ id);
		try {
			ExaminationProtocolServiceCode instance = (ExaminationProtocolServiceCode) _sessionFactory
					.getCurrentSession().get("Examinationprotocolservicecode",
							id);
			if (instance == null) {
				_logger.debug("get successful, no instance found");
			} else {
				_logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			_logger.error("get failed", re);
			throw re;
		}
	}

	public List<ExaminationProtocolServiceCode> findByExample(
			ExaminationProtocolServiceCode instance) {
		_logger.debug("finding Examinationprotocolservicecode instance by example");
		try {
			List<ExaminationProtocolServiceCode> results = (List<ExaminationProtocolServiceCode>) _sessionFactory
					.getCurrentSession()
					.createCriteria("Examinationprotocolservicecode")
					.add(create(instance)).list();
			_logger.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			_logger.error("find by example failed", re);
			throw re;
		}
	}
}
