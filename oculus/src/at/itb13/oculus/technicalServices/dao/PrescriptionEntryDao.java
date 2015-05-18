package at.itb13.oculus.technicalServices.dao;

import at.itb13.oculus.domain.PrescriptionEntry;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save PrescriptionEntrys, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 *
 * @author Florin Metzler
 * @since 18.05.2015
 */
public class PrescriptionEntryDao extends GenericDao<PrescriptionEntry> {
private static PrescriptionEntryDao _PrescriptionEntryDao;
	
	static {
		_PrescriptionEntryDao = new PrescriptionEntryDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private PrescriptionEntryDao() {
		super(PrescriptionEntry.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static PrescriptionEntryDao getInstance() {
		return _PrescriptionEntryDao;
	}
}
