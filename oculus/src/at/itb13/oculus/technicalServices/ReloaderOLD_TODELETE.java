package at.itb13.oculus.technicalServices;

import java.util.Collection;

import org.hibernate.Hibernate;

/**
 * TODO: Insert description here. DELETE THIS CLASS IF EVERYTHING WORKS.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class ReloaderOLD_TODELETE {
	private static ReloaderOLD_TODELETE _reloader;
	
	static {
		_reloader = new ReloaderOLD_TODELETE();
	}
	
	private ReloaderOLD_TODELETE() { }
	
	public static ReloaderOLD_TODELETE getInstance() {
		return _reloader;
	}
	
	public <T> boolean isLoaded(T object) {
		return Hibernate.isInitialized(object);
	}
	
//	public <T> T reload(Class<T> clazz, Integer id) {
//		GenericDao<T> dao = new GenericDao<T>(clazz);
//		return dao.findById(id);
//	}
//	
////	public <T> void reload(Class<T> clazz, T entity, Collection<?> collection) {
////		GenericDao<T> dao = new GenericDao<T>(clazz);
////		try {
////			dao.loadCollection(entity, collection);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
//////			e.printStackTrace();
////			System.out.println("oh noes");
////		}
////	}
}
