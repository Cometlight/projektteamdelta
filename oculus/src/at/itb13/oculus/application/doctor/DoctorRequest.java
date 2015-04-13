/**
 * Provides a List of all doctors
 * 
 * @author Karin Trommelschläger
 * @date 13.04.2015
 * 
 */
package at.itb13.oculus.application.doctor;
import java.util.ArrayList;
import java.util.List;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.technicalServices.dao.DoctorDao;

/**
 * @author Karin
 *
 */
public class DoctorRequest {

	public List<Doctor> getDoctorList(){
		List<Doctor> doctorList = new ArrayList<Doctor>();
		DoctorDao dd = new DoctorDao();
		doctorList = dd.findAll();
		return doctorList;
		
	}
}
