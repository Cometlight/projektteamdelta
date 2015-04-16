
package at.itb13.oculus.application.patient;

import java.time.LocalDate;
import java.util.Date;

import at.itb13.oculus.application.IController;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * adds a new patient and makes it persistent in the database
 *
 * @author Florin Metzler
 * @since 10.04.2015
 */
public class PatientCreation implements IController {
	private Patient _p;

	public void createPatient(Doctor doctor, String socialInsuranceNr,
			String firstName, String lastName, LocalDate birthday, String gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) {
		
		_p = new Patient(doctor, socialInsuranceNr, firstName, lastName,	// rather use constructor
				birthday, gender, street, postalCode, city, countryIsoCode,
				phone, email);
		
		PatientDao.getInstance().makePersistent(_p);
	}
}

