package at.itb13.oculus.technicalServices.dao;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save Diagnosis, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 *
 * @author Florin Metzler
 * @since 18.05.2015
 */
public class DiagnosisDao extends GenericDao<Diagnosis> {

private static DiagnosisDao _DiagnosisDao;
	
	static {
		_DiagnosisDao = new DiagnosisDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private DiagnosisDao() {
		super(Diagnosis.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static DiagnosisDao getInstance() {
		return _DiagnosisDao;
	}
}
