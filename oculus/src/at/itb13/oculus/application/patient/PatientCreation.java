
package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.util.Date;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * adds a new patient and makes it persistent in the database
 *
 * @author Florin Metzler
 * @since 10.04.2015
 */
public class PatientCreation {
	public void createPatient(Doctor doctor, String socialInsuranceNr,	// TODO: DoctorRO instead of Doctor
			String firstName, String lastName, LocalDate birthday, String gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) {
		
		Patient patient = new Patient(doctor, socialInsuranceNr, firstName, lastName,
				birthday, gender, street, postalCode, city, countryIsoCode,
				phone, email);
		
		PatientDao.getInstance().makePersistent(patient);
	}
}

