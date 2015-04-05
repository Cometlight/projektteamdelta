package at.itb13.oculus.technicalServices;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
