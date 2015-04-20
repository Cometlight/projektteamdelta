package at.itb13.oculus.application.doctor;
import java.util.List;

import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.technicalServices.dao.DoctorDao;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 16.04.2015
 */
public class DoctorRequest {

	/**
	 * 
	 * @return May be empty.
	 */
	public List<? extends DoctorRO> getDoctorList(){
		return DoctorDao.getInstance().findAll();
	}
}

