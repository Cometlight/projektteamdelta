package at.itb13.oculus.technicalServices;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 * @param <T>
 */
public abstract class GenericDao<T> {
	
	private static final Logger _logger = LogManager.getLogger(HibernateUtil.class.getName());
	private Class<T> _domainClass;
	
	/**
	 * Example: GenericDao(Doctor.class);
	 * @param domainClass The class that this particular GenericDao is used for.
	 */
	public GenericDao(Class<T> domainClass) {
		_domainClass = domainClass;
	}
	
	/**
	 * Opens a new Hibernate session and looks up the object with the _domainClass specified in the constructor, and the supplied id.
	 * If the id is not valid, an error message is logged and null is returned.
	 * 
	 * @param id the unique identifier of the desired object. The id must be valid!
	 * @return the desired object if found, or null otherwise.
	 */
	@SuppressWarnings("unchecked")
	public T findById(Integer id) {
		T domainClass = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			domainClass = (T) session.get(_domainClass, id);
			
			tx.commit();
			session.flush();
			_logger.info("'" + _domainClass.getName() + "' with ID '" + id + "' successfully retrieved from database.");
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			_logger.error(ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return domainClass;
	}
	
	/**
	 * Opens a new Hibernate session and loads all objects with the _domainClass specified in the constructor into a list.
	 * If no objects are found, the returned is empty.
	 * Alias: {@link #list() list}
	 * 
	 * @return a list of all objects found in the database. May be empty.
	 */
	public List<T> findAll() {
		return list();
	}
	
	/**
	 * Opens a new Hibernate session and loads all objects with the _domainClass specified in the constructor into a list.
	 * If no objects are found, the returned is empty.
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
			session.flush();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
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
	 * Adds all Criterions to a Criteria and returns the resulting list of objects that fit these Criterias.
	 * 
	 * @param order TODO
	 * @param criterion An object-oriented representation of a query criterion that is used as a restriction in a Criteria query.
	 * @return a list with all objects that fit the supplied Criterions. May be empty.
	 * @see <a href="http://www.tutorialspoint.com/hibernate/hibernate_criteria_queries.htm">How to use Criterias</a>
	 * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/querycriteria.html#querycriteria-narrowing">docs.jboss.org/hibernate</a>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Order order, Criterion... criterion) {
		List<T> list = null;
		
		if(criterion == null) {
			list = findAll();
		} else {
		
			Session session = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria crit = session.createCriteria(_domainClass);
				for(Criterion c : criterion) {
					crit.add(c);
				}
				if(order != null) {
					crit.addOrder(order);
				}
				list = crit.list();
			} catch (Exception ex) {
				ex.printStackTrace();
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
	
	protected List<T> findByCriteria(Criterion... criterion) {
		return findByCriteria(null, criterion);
	}
	
	public List<T> makePersistent(T entity, T... entities) {
		List<T> list = new LinkedList<T>();
		list.add(entity);
		for(T e : entities) {
			list.add(e);
		}
		return makePersistent(list);
	}
	
	public List<T> makePersistent(List<T> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			for(T entity : entities) {
				session.saveOrUpdate(entity);	// TODO: check if saveOrUpdate is the most appropriate method
			}
			
			tx.commit();
			session.flush();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return entities;
	}
	
	public void makeTransient(T entity, T... entities) {
		List<T> list = new LinkedList<T>();
		list.add(entity);
		for(T e : entities) {
			list.add(e);
		}
		makeTransient(list);
	}
	
	public void makeTransient(List<T> entities) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.delete(entities);
			
			tx.commit();
			session.flush();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	protected void loadCollection(T entity, Collection<?> collection) throws Exception {
		if(Hibernate.isInitialized(collection)) {
			// TODO: Log info that already initialized. However, there is no need to throw an exception.
		} else {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				
				session.update(entity);	// TODO: Check if merge(), lock(), or sth. similar would be more appropriate for reattaching the object;
				Hibernate.initialize(collection);
				
				tx.commit();
				session.flush();	// TODO: Check if flush() is needed
			} catch (Exception ex) {
				if(tx != null) {
					tx.rollback();
				}
				throw ex;
			} finally {
				if(session != null) {
					session.close();
				}
			}
		}
	}

	public Class<T> getDomainClass() {
		return _domainClass;
	}
}
