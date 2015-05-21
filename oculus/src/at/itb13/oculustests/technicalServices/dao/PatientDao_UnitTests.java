package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
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
	public void makePersistent_findById_makeTransient() {
		Patient patient = new Patient();
		Doctor doctor = DoctorDao.getInstance().findById(1);
		
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
		
		
		assertEquals(true, PatientDao.getInstance().makePersistent(patient));
		
		Patient patientTemp = PatientDao.getInstance().findBySocialInsuranceNr("1234567809");
		assertEquals(true, patientTemp != null);
		assertEquals(true, patientTemp.getFirstName().equals("Marc"));
		assertEquals(true, PatientDao.getInstance().makeTransient(patient));
	}
}
