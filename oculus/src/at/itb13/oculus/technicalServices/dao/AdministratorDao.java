package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Administrator;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 10 Apr 2015
 */
public class AdministratorDao extends GenericDao<Administrator> {
	
	private static final Logger _logger = LogManager.getLogger(AdministratorDao.class.getName());
	
	private static AdministratorDao _administratorDao;
	
	static {
		_administratorDao = new AdministratorDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private AdministratorDao() {
		super(Administrator.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static AdministratorDao getInstance() {
		return _administratorDao;
	}
}
