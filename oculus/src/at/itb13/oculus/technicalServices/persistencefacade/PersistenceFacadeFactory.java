package at.itb13.oculus.technicalServices.persistencefacade;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 12.05.2015
 */
public class PersistenceFacadeFactory extends APersistenceFacadeFactory {

	private static IPersistenceFacade _facade;
	
	public PersistenceFacadeFactory() {
		_factory = this;
	}

	@Override
	public IPersistenceFacade getPersistenceFacade() {
		if(_facade == null){
			_facade = new PersistenceFacade();
		}
		return _facade;
	}
	
}
