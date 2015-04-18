package at.itb13.oculus.technicalServices.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * This class is used to load and save {@link at.itb13.oculus.domain.ExaminationProtocol}s, 
 * as well as load collections that have not been loaded from the database yet (in case of lazy loading).
 * 
 * @author Daniel Scheffknecht
 * @date 18.04.2015
 */
public class ExaminationProtocolDao extends GenericDao<ExaminationProtocol> {
	private static final Logger _logger = LogManager.getLogger(ExaminationProtocolDao.class.getName());
	
	private static ExaminationProtocolDao _examinationProtocolDao;
	
	static {
		_examinationProtocolDao = new ExaminationProtocolDao();
	}
	
	/**
	 * @see GenericDao#GenericDao(Class);
	 */
	private ExaminationProtocolDao() {
		super(ExaminationProtocol.class);
	}
	
	public ExaminationProtocolDao getInstance() {
		return _examinationProtocolDao;
	}
}
