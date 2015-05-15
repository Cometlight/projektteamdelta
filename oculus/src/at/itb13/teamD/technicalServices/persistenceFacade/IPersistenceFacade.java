package at.itb13.teamD.technicalServices.persistenceFacade;

import java.util.Collection;
import java.util.List;

import at.itb13.teamD.technicalServices.exceptions.PersistenceFacadeException;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 11.05.2015
 */
public interface IPersistenceFacade {

	/**
	 * 
	 * 
	 * @param id the ID of the Entity searched for
	 * @param clazz the class, esp. the Interface of the entity searched for
	 * @return an instance of the required class or null
	 */
	public <T> T getById(Integer id, Class<T> clazz);
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getAll(Class<T> clazz);
	
	/**
	 * 
	 * @param obj object that should be made persistent in database
	 * @return
	 */
	public boolean makePersistent(Object obj);
	
	/**
	 * 
	 * @param clazz
	 * @param searchString
	 * @return
	 * @throws PersistenceFacadeException
	 */
	public <T> Collection<T> searchFor(Class<T> clazz, String searchString) throws PersistenceFacadeException;
	
}
