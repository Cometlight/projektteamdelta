package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.Orthoptist}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 07.04.2015
 */
public class OrthoptistDao extends GenericDao<Orthoptist> {

	private static final Logger _logger = LogManager.getLogger(OrthoptistDao.class.getName());
	
	private static OrthoptistDao _orthoptistDao;
	
	static {
		_orthoptistDao = new OrthoptistDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private OrthoptistDao() {
		super(Orthoptist.class);
	}
	
	/**
	 * 
	 * @return instance of the Singleton
	 */
	public static OrthoptistDao getInstance() {
		return _orthoptistDao;
	}
	
}
