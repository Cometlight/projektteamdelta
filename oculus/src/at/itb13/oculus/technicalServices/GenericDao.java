package at.itb13.oculus.technicalServices;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 * @param <T>
 */
public abstract class GenericDao<T> {
	
	private Class<T> _domainClass;
	
	public GenericDao(Class<T> domainClass) {
		_domainClass = domainClass;
	}
	// TODO: Add logging to whole class
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
		
		return domainClass;
	}
	
	public List<T> findAll() {
		return list();
	}
	
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
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		List<T> list = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(_domainClass);
			for(Criterion c : criterion) {
				crit.add(c);
			}
			list = crit.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return list;
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
	
	protected <U> void loadCollection(T entity, Object collection) throws Exception {
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
