
package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.util.Date;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * adds a new patient and makes it persistent in the database
 *
 * @author Florin Metzler
 * @since 10.04.2015
 */
public class PatientCreation {
	
//	/**
//	 * 
//	 * @param doctor
//	 * @param socialInsuranceNr
//	 * @param firstName
//	 * @param lastName
//	 * @param birthday
//	 * @param gender
//	 * @param street
//	 * @param postalCode
//	 * @param city
//	 * @param countryIsoCode
//	 * @param phone
//	 * @param email
//	 * @return null only if not able to save patient.
//	 */
//	public PatientRO createPatient(DoctorRO doctorRO, String socialInsuranceNr,
//			String firstName, String lastName, LocalDate birthday, String gender,
//			String street, String postalCode, String city,
//			String countryIsoCode, String phone, String email) {
//		
//		Doctor doctor = DoctorDao.getInstance().findById(doctorRO.getDoctorId());
//		
//		Patient patient = new Patient(doctor, socialInsuranceNr, firstName, lastName,
//				birthday, gender, street, postalCode, city, countryIsoCode,
//				phone, email);
//		
//		PatientRO patientRO;
//		
//		if(PatientDao.getInstance().makePersistent(patient)) {
//			patientRO = patient;
//		} else {
//			patientRO = null;
//		}
//		
//		return patientRO;
//	}
}

