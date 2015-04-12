package at.itb13.oculus.technicalServices;

import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @param <T>
 * @date 12.04.2015
 */
public interface DAO<T> {
	T findById(Integer id);
	List<T> list();
	List<T> findAll();
	boolean makePersistent(List<T> entities);
	boolean makePersistent(T... entities);
	boolean makeTransient(List<T> entities);
	boolean makeTransient(T... entitites);
}
