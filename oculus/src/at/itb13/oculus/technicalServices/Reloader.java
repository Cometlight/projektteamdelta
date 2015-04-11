package at.itb13.oculus.technicalServices;

import org.hibernate.Hibernate;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class Reloader {
	private static Reloader _reloader;
	
	static {
		_reloader = new Reloader();
	}
	
	private Reloader() { }
	
	public static Reloader getInstance() {
		return _reloader;
	}
	
	public <T> boolean isLoaded(T object) {
		return Hibernate.isInitialized(object);
	}
	
	public <T> T reload(Class<T> clazz, Integer id) {
		// TODO: Insert special case: queue ..? Actually, probably not needed
		GenericDao<T> dao = new GenericDao<T>(clazz);
		return dao.findById(id);
	}
}
