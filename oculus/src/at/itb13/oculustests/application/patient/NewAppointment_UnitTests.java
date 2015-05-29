package at.itb13.oculustests.application.patient;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.technicalServices.dao.PatientDao;

public class NewAppointment_UnitTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Patient patient1 = new Patient();
		patient1.setFirstName("Max");
		patient1.setLastName("Mustermann");
		patient1.setGender(Gender.M);
		patient1.setSocialInsuranceNr("741852963");
		PatientDao.getInstance().makePersistent(patient1);
	}

	@After
	public void tearDown() throws Exception {
		Patient patient1 = PatientDao.getInstance().findBySocialInsuranceNr("741852963");
		PatientDao.getInstance().makeTransient(patient1);
	}

	@Test
	public void testIsLoginCredentialsValid_Null() {
		// isLoginCredentialsValid(null, null);
		// isLoginCredentialsValid(null, "password");
	}

}
