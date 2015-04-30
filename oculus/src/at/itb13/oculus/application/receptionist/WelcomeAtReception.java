package at.itb13.oculus.application.receptionist;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 22.04.2015
 */
public class WelcomeAtReception {
	private static final Logger _logger = LogManager.getLogger(WelcomeAtReception.class.getName());
	
	/**
	 * TODO
	 * 
	 * @param patientRO only the id is used
	 * @param doctorRO
	 * @param socialInsuranceNr
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 * @param gender
	 * @param street
	 * @param postalCode
	 * @param city
	 * @param countryIsoCode
	 * @param phone
	 * @param email
	 * @return null only if not able to update patient. or patient doesn't exist yet in the database
	 * @throws InvalidInputException patientRO, doctorRO, firstName, lastName and gender must not be null
	 */
	public PatientRO updatePatient(PatientRO patientRO, DoctorRO doctorRO, String socialInsuranceNr,
			String firstName, String lastName, LocalDate birthday, Gender gender,
			String street, String postalCode, String city,
			String countryIsoCode, String phone, String email) throws InvalidInputException {
		
		if(patientRO == null || doctorRO == null || firstName == null || lastName == null || gender == null) {
			throw new InvalidInputException("patientRO, doctorRO, firstName, lastName and gender must not be null");
		}
		
		PatientRO patROReturn = null;
		
		Patient patient = PatientDao.getInstance().findById(patientRO.getPatientId());
		if (patient != null) {	// Only update if patient already exists
			Doctor doctor = DoctorDao.getInstance().findById(doctorRO.getDoctorId());
			patient.setDoctor(doctor);
			patient.setSocialInsuranceNr(socialInsuranceNr);
			patient.setFirstName(firstName);
			patient.setLastName(lastName);
			patient.setDateOfBirth(birthday);
			patient.setGender(gender);
			patient.setStreet(street);
			patient.setPostalCode(postalCode);
			patient.setCity(city);
			patient.setCountryIsoCode(countryIsoCode);
			patient.setPhone(phone);
			patient.setEmail(email);
			
			if(PatientDao.getInstance().makePersistent(patient)) {
				_logger.info("Patient (" + patientRO.getPatientId() + ") has been updated.");
				patROReturn = patient;
			} else {
				_logger.error("Could not update Patient (" + patientRO.getPatientId() + ") in the database.");
			}
		} else {
			_logger.info("Patient with ID '" + patientRO.getPatientId() + "' not exists.");
		}
		
		return patROReturn;
	}
	
	/**
	 * TODO
	 * 
	 * @param patientRO
	 * @return
	 */
	public List<? extends CalendarEventRO> getAllCalendarEventsSorted(PatientRO patientRO) {
		Set<CalendarEvent> listCalEv = patientRO.getCalendarevents();
		return CalendarEvent.sortCalendarEventsByStartDate(listCalEv);
	}
	
	/**
	 * TODO
	 * 
	 * @param socialInsuranceNr
	 * @return
	 */
	public boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		return Patient.isSocialInsuranceNrValid(socialInsuranceNr);
	}
	
	/**
	 * 
	 * @return May be empty.
	 */
	public List<? extends DoctorRO> getDoctorList(){
		return DoctorDao.getInstance().findAll();
	}
	
	/**
	 * 
	 * @param patientRO
	 * @param allergy
	 * @param childhoodAilments
	 * @param medicineIntolerance
	 * @return the updated Patient. Null, if not possible to update patient and to save to the database.
	 */
	public PatientRO updateAnamnesis(PatientRO patientRO, String allergy, String childhoodAilments, String medicineIntolerance) {
		Patient patient = PatientDao.getInstance().findById(patientRO.getPatientId());
		if(patient != null) {
			patient.setAllergy(allergy);
			patient.setChildhoodAilments(childhoodAilments);
			patient.setMedicineIntolerance(medicineIntolerance);
			return PatientDao.getInstance().makePersistent(patient) ? patient : null;
		} else {
			return null;
		}
	}
}
