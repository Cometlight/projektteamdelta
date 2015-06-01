package at.itb13.oculus.technicalServices.dao;

import at.itb13.oculus.domain.VisualAid;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * @author Daniel Scheffknecht
 * @date May 18, 2015
 */
public class VisualAidDao extends GenericDao<VisualAid> {
	private static VisualAidDao _visualAidDao;
	
	static {
		_visualAidDao = new VisualAidDao();
	}

	private VisualAidDao() {
		super(VisualAid.class);
	}
	
	public static VisualAidDao getInstance() {
		return _visualAidDao;
	}

}
