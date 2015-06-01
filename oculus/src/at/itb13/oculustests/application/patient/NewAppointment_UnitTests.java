package at.itb13.oculustests.application.patient;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static at.itb13.oculustests.exceptioncatcher.ThrowableAssertion.assertThrown;
import static org.junit.Assert.*;

import at.itb13.oculus.application.patient.NewAppointment;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.technicalServices.dao.PatientDao;


/**
 * 
 * @author Daniel Scheffknecht
 * @date 29 May 2015
 */
public class NewAppointment_UnitTests {
	private static final String PATIENT1_EMAIL = "max.mustermann@muster.com";
	private static final String PATIENT1_PASSWORD = "letmein";

	@Before
	public void setUp() throws Exception {
		Patient patient1 = new Patient();
		patient1.setFirstName("Max");
		patient1.setLastName("Mustermann");
		patient1.setGender(Gender.M);
		patient1.setSocialInsuranceNr("741852963");
		patient1.setEmail(PATIENT1_EMAIL);
		patient1.setPasswordAsHash(PATIENT1_PASSWORD);
		PatientDao.getInstance().makePersistent(patient1);
	}

	@After
	public void tearDown() throws Exception {
		Patient patient1 = PatientDao.getInstance().findBySocialInsuranceNr("741852963");
		PatientDao.getInstance().makeTransient(patient1);
	}

	@Test
	public void testIsLoginCredentialsValid_Null() {
		NewAppointment newAp = new NewAppointment();
		assertThrown(() -> newAp.isLoginCredentialsValid(null, null))
			.isInstanceOf(NullPointerException.class);
		assertThrown(() -> newAp.isLoginCredentialsValid(null, "password"))
			.isInstanceOf(NullPointerException.class);
		assertThrown(() -> newAp.isLoginCredentialsValid("email", null))
			.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void testIsLoginCredentialsValid_Empty() {
		NewAppointment newAp = new NewAppointment();
		// Should not throw any exceptions
		newAp.isLoginCredentialsValid("email", "");
		newAp.isLoginCredentialsValid("", "password");
		newAp.isLoginCredentialsValid("", "");
	}
	
	@Test
	public void testIsLoginCredentialsValid_Valid() {
		NewAppointment newAp = new NewAppointment();
		assertTrue(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, PATIENT1_PASSWORD));
	}
	
	@Test
	public void testIsLoginCredentialsValid_PatientMissing() {
		NewAppointment newAp = new NewAppointment();
		assertFalse(newAp.isLoginCredentialsValid("notindatabase", "password"));
	}
	
	@Test
	public void testIsLoginCredentialsValid_WrongPassword() {
		NewAppointment newAp = new NewAppointment();
		assertFalse(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, "password"));
		assertFalse(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, PATIENT1_PASSWORD + "1"));
		assertFalse(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, "1" + PATIENT1_PASSWORD));
	}

}
