package at.itb13.oculustests.application.patient;

import static at.itb13.oculustests.exceptioncatcher.ThrowableAssertion.assertThrown;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.patient.NewAppointment;
import at.itb13.oculus.domain.CalendarEvent;
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

	/* -- IsLoginCredentialsValid -- */
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
		assertNotNull(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, PATIENT1_PASSWORD));
	}
	
	@Test
	public void testIsLoginCredentialsValid_PatientMissing() {
		NewAppointment newAp = new NewAppointment();
		assertNull(newAp.isLoginCredentialsValid("notindatabase", "password"));
	}
	
	@Test
	public void testIsLoginCredentialsValid_WrongPassword() {
		NewAppointment newAp = new NewAppointment();
		assertNull(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, "password"));
		assertNull(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, PATIENT1_PASSWORD + "1"));
		assertNull(newAp.isLoginCredentialsValid(PATIENT1_EMAIL, "1" + PATIENT1_PASSWORD));
	}
	
	/* -- hasFutureAppointment -- */
	@Test
	public void testHasFutureAppointment_Null() {
		// NullPointerException if ControllerFacade.getPatientSelected() is not set
		NewAppointment newAp = new NewAppointment();
		ControllerFacade.setPatientSelected(null);
		assertThrown(() -> newAp.hasFutureAppointment())
			.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void testHasFutureAppointment_True() {
		CalendarEvent calEv = new CalendarEvent();
		calEv.setEventStart(LocalDateTime.now().plusHours(2));
		calEv.setEventEnd(LocalDateTime.now().plusHours(3));
		
		Patient patient = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		patient.addCalendarEvent(calEv);
		ControllerFacade.setPatientSelected(patient);
		
		NewAppointment newAp = new NewAppointment();
		assertTrue(newAp.hasFutureAppointment());
	}
	
	@Test
	public void testHasFutureAppointment_False() {
		NewAppointment newAp = new NewAppointment();
		Patient patient = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		ControllerFacade.setPatientSelected(patient);
		
		assertFalse(newAp.hasFutureAppointment());
		
		CalendarEvent calEv1 = new CalendarEvent();
		calEv1.setEventStart(LocalDateTime.now().minusHours(1));
		calEv1.setEventEnd(LocalDateTime.now().minusMinutes(15));
		patient.addCalendarEvent(calEv1);
		assertFalse(newAp.hasFutureAppointment());
		
		CalendarEvent calEv2 = new CalendarEvent();
		calEv2.setEventStart(LocalDateTime.now().minusHours(1));
		calEv2.setEventEnd(LocalDateTime.now().plusMinutes(15));
		patient.addCalendarEvent(calEv2);
		assertFalse(newAp.hasFutureAppointment());
	}
}
