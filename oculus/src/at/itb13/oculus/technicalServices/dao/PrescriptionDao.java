package at.itb13.oculus.technicalServices.dao;

import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * @author Daniel Scheffknecht
 * @date May 18, 2015
 */
public class PrescriptionDao extends GenericDao<Prescription> {
	private static PrescriptionDao _prescriptionDao;
	
	static {
		_prescriptionDao = new PrescriptionDao();
	}
	
	private PrescriptionDao() {
		super(Prescription.class);
	}
	
	public static PrescriptionDao getInstance() {
		return _prescriptionDao;
	}
}
