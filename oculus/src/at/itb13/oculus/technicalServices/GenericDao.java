package at.itb13.oculus.technicalServices;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

/**
 * 
 * Offers common functionality for concrete DAO classes, like {@link #findById() findById} or {@link #makePersistent() makePersistent}.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 * @param <T> The class that this particular GenericDao is used for.
 */
public abstract class GenericDao<T> {
	
	private static final Logger _logger = LogManager.getLogger(GenericDao.class.getName());
	private Class<T> _domainClass;
	
	/**
	 * Example: GenericDao(Doctor.class);
	 * 
	 * @param domainClass The class that this particular GenericDao is used for.
	 */
	public GenericDao(Class<T> domainClass) {
		_domainClass = domainClass;
	}
	
	/**
	 * Opens a new Hibernate session and looks up the object with the domainClass specified in the constructor, and the supplied id.
	 * If the id is not valid, an error message is logged and null is returned.
	 * 
	 * @param id the unique identifier of the desired object. The id must be valid!
	 * @return the desired object if found, or null otherwise.
	 */
	@SuppressWarnings("unchecked")
	public T findById(Integer id) {
		if(id == null) {
			throw new NullPointerException("ID may not be null! (" + _domainClass.getName() + ")");
		}
		
		T domainClass = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			domainClass = (T) session.get(_domainClass, id);
			
			tx.commit();
			_logger.info("'" + _domainClass.getName() + "' with ID '" + id + "' successfully retrieved from database.");
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error("Error in findById(Integer)", ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return domainClass;
	}
	
	/**
	 * Opens a new Hibernate session and loads all objects with the domainClass specified in the constructor into a list.
	 * If no objects are found, the returned list is empty.
	 * 
	 * Alias: {@link #findAll() findAll}
	 * 
	 * @return a list of all objects found in the database. May be empty.
	 */
	@SuppressWarnings("unchecked")
	public List<T> list() {
		List<T> list = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			list = (List<T>) session.createCriteria(_domainClass).list();
			
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error("Error in list()", ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		if(list == null) {
			list = new LinkedList<T>();	// rather return an empty list than NULL
		}
		
		return list;
	}
	
	/**
	 * Opens a new Hibernate session and loads all objects with the domainClass specified in the constructor into a list.
	 * If no objects are found, the returned list is empty.
	 * 
	 * Alias: {@link #list() list}
	 * 
	 * @return a list of all objects found in the database. May be empty.
	 */
	public List<T> findAll() {
		return list();
	}

	/**
	 * Opens a new Hibernate session, adds all Criterions to a Criteria and returns the resulting list of objects that fit these Criterias.
	 * <p>
	 * If a more advanced Criteria, eg. with sorting and projections, is wanted, one must implement it themself. 
	 * This method was only added in order to offer a convenient way for simple Criterias.
	 * <p>
	 * Example: List<Patient> childrenMale = findByCriteria(Restrictions.gt("birthDay", "1997-01-01"), Restrictions.eq("gender", "M"));
	 * 
	 * @param criterion An object-oriented representation of a query criterion that is used as a restriction in a Criteria query.
	 * @return a list with all objects that fit the supplied Criterions. May be empty.
	 * @see <a href="http://www.tutorialspoint.com/hibernate/hibernate_criteria_queries.htm">How to use Criterias</a>
	 * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/querycriteria.html#querycriteria-narrowing">docs.jboss.org/hibernate</a>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		List<T> list = null;
		
		if(criterion == null) {
			_logger.warn("No criterions supplied. Rather use findAll() or list() to get a full not restricted list.");
			list = findAll();
		} else {
		
			Session session = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria crit = session.createCriteria(_domainClass);
				for(Criterion c : criterion) {
					crit.add(c);
				}
				list = crit.list();
			} catch (Exception ex) {
				_logger.error("Error in findByCriteria(Criterion...)", ex);
			} finally {
				if(session != null) {
					session.close();
				}
			}
			
			if(list == null) {
				list = new LinkedList<T>();	// rather return an empty list than NULL
			}
		}
		
		return list;
	}
	
	/**
	 * Opens a new Hibernate session and saves or updates the supplied entities.
	 * If there is a problem with a single entity, no entity gets saved or updated at all and false is returned.
	 * The entities are in a detached state after execution.
	 * 
	 * @param entities a list of entities that should be saved or updated
	 * @return true if all entities have been saved or updated; false if no entity has been saved or updated.
	 * @see #makePersistent(T... entities)
	 * @see #makeTransient(T... entities)
	 * @see #makeTransient(List<T> entities)
	 */
	public boolean makePersistent(List<T> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			for(T entity : entities) {
				session.saveOrUpdate(entity);
			}
			
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error("Error in makePersistent(List<T>)", ex);
			return false;
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		StringBuilder successMsgBuilder = new StringBuilder("makePersistent was successful for: ");
		entities.forEach(successMsgBuilder::append);
		_logger.info(successMsgBuilder);
		return true;
	}
	
	/**
	 * Opens a new Hibernate session and saves or updates the supplied entities.
	 * If there is a problem with a single entity, no entity gets saved or updated at all and false is returned.
	 * The entities are in a detached state after execution.
	 * 
	 * @param entities entities that should be saved or updated
	 * @return true if all entities have been saved or updated; false if no entity has been saved or updated.
	 * @see #makePersistent(List<T> entities)
	 * @see #makeTransient(T... entities)
	 * @see #makeTransient(List<T> entities)
	 */
	@SuppressWarnings("unchecked")
	public boolean makePersistent(T... entities) {
		if(entities == null) {
			_logger.error("No entities supplied. At least 1 entity has to be supplied!");
			return false;
		}
		
		return makePersistent(Arrays.asList(entities));
	}

	/**
	 * Opens a new Hibernate session and deletes the supplied entities from the database.
	 * The entities are in a transient state after execution.
	 * 
	 * @param entities entities that should be deleted
	 * @return true if all entities have been deleted; false if no entity has been deleted.
	 * @see #makeTransient(T... entities)
	 * @see #makePersistent(List<T> entities)
	 * @see #makePersistent(T... entities)
	 */
	public boolean makeTransient(List<T> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			for(T entity : entities) {
				session.delete(entity);
			}
			
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error("Error in makeTransient(List<T>)", ex);
			return false;
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		StringBuilder successMsgBuilder = new StringBuilder("makeTransient was successful for: ");
		entities.forEach(successMsgBuilder::append);
		_logger.info(successMsgBuilder);
		return true;
	}
	
	/**
	 * Opens a new Hibernate session and deletes the supplied entities from the database.
	 * The entities are in a transient state after execution.
	 * 
	 * @param entities entities that should be deleted
	 * @return true if all entities have been deleted; false if no entity has been deleted.
	 * @see #makeTransient(List<T> entities)
	 * @see #makePersistent(List<T> entities)
	 * @see #makePersistent(T... entities)
	 */
	@SuppressWarnings("unchecked")
	public boolean makeTransient(T... entities) {
		if(entities == null) {
			_logger.error("No entities supplied. At least 1 entity has to be supplied!");
			return false;
		}
		
		return makeTransient(Arrays.asList(entities));
	}

	/**
	 * 
	 * @return The domainClass that was passed into the constructor.
	 */
	public Class<T> getDomainClass() {
		return _domainClass;
	}
}
