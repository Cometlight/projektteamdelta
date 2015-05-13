package at.itb13.oculus.technicalServices.persistencefacade;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 13.05.2015
 */
public class PersistenceFacadeProvider {
	private static IPersistenceFacade _facade;

	private PersistenceFacadeProvider() {
	}

	public static IPersistenceFacade getPersistenceFacade() {
		return _facade;
	}

	public static void setPersistenceFacade(IPersistenceFacade facade) {
		_facade = facade;
	}
}
