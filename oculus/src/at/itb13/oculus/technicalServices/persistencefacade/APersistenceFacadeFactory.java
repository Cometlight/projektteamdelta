package at.itb13.oculus.technicalServices.persistencefacade;

public abstract class APersistenceFacadeFactory {

	protected static APersistenceFacadeFactory _factory;
	
	public static APersistenceFacadeFactory getPersistenceFacadeFactory(){
		return _factory;
	}
	
	public abstract IPersistenceFacade getPersistenceFacade();
}