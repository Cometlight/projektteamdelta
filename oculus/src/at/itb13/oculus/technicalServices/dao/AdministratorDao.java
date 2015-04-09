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
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	public AdministratorDao() {
		super(Administrator.class);
	}
	
	//TODO add methods
}
