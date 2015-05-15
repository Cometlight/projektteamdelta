package at.itb13.teamD.technicalServices.persistenceFacade;

/**
 * This class provides the implemented PersistenceFacade. This provider class
 * MUST be initialized with concrete implementation of the PersistenceFacade.
 * 
 * @author Andrew Sparr
 * @date 13.05.2015
 */
public class PersistenceFacadeProvider {
	private static IPersistenceFacade _facade;

	private PersistenceFacadeProvider() {
	}

	/**
	 * Provides the set persistencefacade
	 * @return concrete implementation of persistencefacade. The persistencefacade must be set during startup of Oculus.
	 */
	public static IPersistenceFacade getPersistenceFacade() {
		return _facade;
	}

	/**
	 * 
	 * @param facade
	 *            The persistencefacade MUST be initialised during Oculus
	 *            startup. Use the concrete implementation of the
	 *            PersistenceFacade as parameter.
	 */
	public static void setPersistenceFacade(IPersistenceFacade facade) {
		_facade = facade;
	}
}
