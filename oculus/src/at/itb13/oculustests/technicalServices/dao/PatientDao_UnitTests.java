package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.domain.ReferralLetter;
import at.itb13.oculus.domain.User;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * Unittesting for class PatientDao
 * 
 * @author Andrew Sparr
 * @date 13.04.2015
 */
public class PatientDao_UnitTests {

	@Test
	public void getInstance() {
		assertEquals(true, (PatientDao.getInstance() != null));
	}

	@Test
	public void findAll() {
		assertEquals(true, (PatientDao.getInstance().findAll() != null));
	}

	@Test
	public void findByFirstName() {
		assertEquals(true,
				(PatientDao.getInstance().findByFirstName("Test").isEmpty()));
	}

	@Test(expected = NullPointerException.class)
	public void findById() {
		PatientDao.getInstance().findById(null);
	}

	@Test
	public void findByLastName() {
		assertEquals(true,
				(PatientDao.getInstance().findByLastName(null)).isEmpty());
	}

	@Test
	public void findByFullName() {
		assertEquals(true,
				(PatientDao.getInstance().findByFullName(null, null).isEmpty()));
	}

	@Test
	public void findBySocialInsuranceNr() {
		assertEquals(
				true,
				(PatientDao.getInstance().findBySocialInsuranceNr(null)) == null);
	}

	// private Integer _patientId;
	// private Doctor _doctor;
	// private String _socialInsuranceNr;
	// private String _firstName;
	// private String _lastName;
	// private LocalDate _birthDay;
	// private Gender _gender;
	// private String _street;
	// private String _postalCode;
	// private String _city;
	// private String _countryIsoCode;
	// private String _phone;
	// private String _email;
	// private String _allergy;
	// private String _childhoodAilments;
	// private String _medicineIntolerance;
	// private Set<CalendarEvent> _calendarevents = new
	// HashSet<CalendarEvent>(0);
	// private Set<Prescription> _prescriptions = new HashSet<Prescription>(0);
	// private Set<ReferralLetter> _referralletters = new
	// HashSet<ReferralLetter>(0);
	// private Set<ExaminationProtocol> _examinationprotocols = new
	// HashSet<ExaminationProtocol>(0);

	// Doctor member variables;
	// private Integer _doctorId;
	// private Calendar _calendar;
	// private Doctor _doctorSubstitute;
	// private User _user;
	// private Set<Diagnosis> _diagnoses = new HashSet<Diagnosis>(0);
	// private Set<Patient> _patients = new HashSet<Patient>(0);

	@Test
	public void makePersistent() {
		Patient patient = new Patient();
//		TODO User erstellen???
//		User user = new User();
		Patient patient2 = new Patient();
		Doctor doctor = DoctorDao.getInstance().findById(1);
		
		
//		Doctor doctor = new Doctor();
//		doctor.setDoctorId(1);
//		patient.setPatientId(5000);
		patient.setDoctor(doctor);
		patient.setSocialInsuranceNr("1234567809");
		patient.setFirstName("Marc");
		patient.setLastName("Kletz");
		patient.setDateOfBirth(LocalDate.now());
		patient.setGender(Gender.M);
		patient.setStreet("Street");
		patient.setCity("City");
		patient.setCountryIsoCode("AZ");
		patient.setPhone("000-Phone");
		patient.setEmail("this@email.com");
		patient.setAllergy("Allergy");
		patient.setChildhoodAilments("Childhood illnesses");
		patient.setMedicineIntolerance("Drug intolerances");
		
//		patient2.setPatientId(5001);
//		patient2.setDoctor(doctor);
//		patient2.setSocialInsuranceNr("1234567809");
//		patient2.setFirstName("Marc");
//		patient2.setLastName("Kletz");
//		patient2.setDateOfBirth(LocalDate.now());
//		patient2.setGender(Gender.M);
//		patient2.setStreet("Street");
//		patient2.setCity("City");
//		patient2.setCountryIsoCode("AZ");
//		patient2.setPhone("000-Phone");
//		patient2.setEmail("this@email.com");
//		patient2.setAllergy("Allergy");
//		patient2.setChildhoodAilments("Childhood illnesses");
//		patient2.setMedicineIntolerance("Drug intolerances");
		
		assertEquals(true, PatientDao.getInstance().makePersistent(patient));
	}
	
	@Test
	public void makeTransient() {
		Patient patient = PatientDao.getInstance().findBySocialInsuranceNr("1234567809");
		assertEquals(true, patient != null);
//		assertEquals(true, PatientDao.getInstance().makeTransient(patient));
	}

}
