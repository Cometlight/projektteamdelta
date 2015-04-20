package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.Doctor}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class DoctorDao extends GenericDao<Doctor> {

	private static final Logger _logger = LogManager.getLogger(DoctorDao.class.getName());
	
	private static DoctorDao _doctorDao;
	
	static {
		_doctorDao = new DoctorDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private DoctorDao() {
		super(Doctor.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static DoctorDao getInstance() {
		return _doctorDao;
	}
	
	
	// TODO: Why does a doctor contain a list of "doctors"?
}
