package at.itb13.oculustests.domain;

import static at.itb13.oculustests.exceptioncatcher.ThrowableAssertion.assertThrown;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * Tests constructor and methods of the class Patient.
 * 
 * @author Karin Trommelschläger
 * @date 30.04.2015
 * 
 */
public class Patient_UnitTests {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void setPasswordAsHash_Null() {
		assertThrown(() -> new Patient().setPasswordAsHash(null))
			.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void setPasswordAsHash_Empty() {
		assertThrown(() -> new Patient().setPasswordAsHash(""))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("password may not be empty");
	}
	
	@Test
	public void setPasswordAsHash_Valid() {
		final String password1 = "letmein";
		Patient pat1 = new Patient();
		pat1.setPasswordAsHash(password1);
		assertEquals(pat1.getPassword(), "adfb6dd1ab1238afc37acd8ca24c1279f8d46f61907dd842faab35b0cc41c6e8ad84cbdbef4964b8334c22c4985c2387d53bc47e6c3d0940ac962f521a127d9f");
		
		final String password2 = "1";
		Patient pat2 = new Patient();
		pat2.setPasswordAsHash(password2);
		assertEquals(pat2.getPassword(), "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a");
		
		final String password3 = "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a";
		Patient pat3 = new Patient();
		pat3.setPasswordAsHash(password3);
		assertEquals(pat3.getPassword(), "a1bd4e0efc7ce8bd1d63433a0baa87e3a486fbfe2729d73d1dbf7d2822d201ee8726c6d94da1f09f1a53554e440ad6041ecab545b2085dc28c6f6849f0fcea23");
	}
	
	@Test
	public void isEqualPassword_Null() {
		assertThrown(() -> new Patient().isEqualPassword(null))
			.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void isEqualPassword_Empty() {
		assertThrown(() -> new Patient().isEqualPassword(""))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("password may not be empty");
	}
	
	@Test
	public void isEqualPassword_Valid() {
		final String password1 = "letmein";
		Patient pat1 = new Patient();
		pat1.setPasswordAsHash(password1);
		assertTrue(pat1.isEqualPassword(password1));
		
		final String password2 = "1";
		Patient pat2 = new Patient();
		pat2.setPasswordAsHash(password2);
		assertTrue(pat2.isEqualPassword(password2));
		
		final String password3 = "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a";
		Patient pat3 = new Patient();
		pat3.setPasswordAsHash(password3);
		assertTrue(pat3.isEqualPassword(password3));
	}
	
	@Test
	public void isEqualPassword_WrongPassword() {
		final String password1 = "letmein";
		Patient pat1 = new Patient();
		pat1.setPasswordAsHash(password1);
		assertFalse(pat1.isEqualPassword("password"));
		assertFalse(pat1.isEqualPassword("1" + "password"));
		assertFalse(pat1.isEqualPassword("password" + "1"));
	}
	
//	@Test
//	public void getNextAppointment_Test(){
//		Patient patient = new Patient();
//		Doctor doctor = new Doctor();
//		doctor = DoctorDao.getInstance().list().get(0);
//		CalendarEvent calendarEvent = new CalendarEvent();
//		int calendarEventId;
//		
//		patient.setFirstName("Maja");
//		patient.setLastName("Musterfrau");
//		patient.setGender(Gender.F);
//		patient.setSocialInsuranceNr("8976543201");
//		PatientDao.getInstance().makePersistent(patient);
//		
//		EventType eventType = new EventType();
//		eventType = EventTypeDao.getInstance().list().get(0);
//		
//		calendarEvent.setCalendar(doctor.getCalendar());
//		calendarEvent.setDescription("first Appointment");
//		calendarEvent.setEventStart(LocalDateTime.now().plusWeeks(1));
//		calendarEvent.setEventEnd(calendarEvent.getEventStart().plusMinutes(eventType.getEstimatedTime()));
//		calendarEvent.setPatient(patient);
//		calendarEvent.setEventType(eventType);
//		CalendarEventDao.getInstance().makePersistent(calendarEvent);
//		calendarEventId = patient.getCalendarevents().iterator().next().getCalendarEventId();
//		assertTrue(patient.getNextAppointment().getCalendarEventId()==calendarEventId);
//		
//		CalendarEventDao.getInstance().makeTransient(calendarEvent);
//		PatientDao.getInstance().makeTransient(patient);
//	}
//	

	// TODO: Check if the following tests are good (old tests)
//	@Test
//	public void constructorWithoutParameters() {
//		Patient p = new Patient();
//		assertEquals(true, p != null);
//	}
//
//	@Test
//	public void constructorWithGenaeralInformationWithNullParameters() {
//		String s = "";
//		Patient p = new Patient(null, s, s, s, null, null, s, s, s, s, s, s);
//		assertEquals(true, p != null);
//	}
//
//	@Test
//	public void constructotWithAllInformationWithNullParameters() {
//		Patient p = new Patient(null, null, null, null, null, null, null, null,
//				null, null, null, null, null, null, null, null, null, null,
//				null);
//		assertEquals(true, p != null);
//	}
//
//	@Test
//	public void isSocialInsuranceNrValid_Test() {
//		String nr1 = "1234567890";
//		String nr2 = "123456789";
//		String nr3 = "abcdefghij";
//
//		assertEquals(true, Patient.isSocialInsuranceNrValid(nr1));
//		assertEquals(false, Patient.isSocialInsuranceNrValid(nr2));
//		assertEquals(false, Patient.isSocialInsuranceNrValid(nr3));
//	}

}
