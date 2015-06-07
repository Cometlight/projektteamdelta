package at.itb13.oculustests.application.patient;

import static at.itb13.oculustests.exceptioncatcher.ThrowableAssertion.assertThrown;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.patient.NewAppointment;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.CalendarWorkingHours;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.EventTypeDao;
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
		Doctor doctor;
		doctor = DoctorDao.getInstance().findAll().get(0);
		patient1.setFirstName("Max");
		patient1.setLastName("Mustermann");
		patient1.setGender(Gender.M);
		patient1.setSocialInsuranceNr("7418529635");
		patient1.setEmail(PATIENT1_EMAIL);
		patient1.setPasswordAsHash(PATIENT1_PASSWORD);
		patient1.setDoctor(doctor);
		PatientDao.getInstance().makePersistent(patient1);
	}

	@After
	public void tearDown() throws Exception {
		Patient patient1 = PatientDao.getInstance().findBySocialInsuranceNr("7418529635");
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
		
		assertThrown(() -> newAp.isLoginCredentialsValid("email", ""))
			.isInstanceOf(IllegalArgumentException.class);
		
		assertThrown(() -> newAp.isLoginCredentialsValid("", "password"))
			.isInstanceOf(IllegalArgumentException.class);
		
		assertThrown(() -> newAp.isLoginCredentialsValid("", ""))
			.isInstanceOf(IllegalArgumentException.class);
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
	
	/* -- isInWorkingHours -- */
	
	@Test
	public void testIsInWorkingHours_Null() {
		NewAppointment newAp = new NewAppointment();
		Patient patient = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		ControllerFacade.setPatientSelected(patient);
		LocalDateTime dateTime = LocalDateTime.now();
		
		assertThrown(() -> newAp.isInWorkingHours(null, null))
			.isInstanceOf(IllegalArgumentException.class);
		
		assertThrown(() -> newAp.isInWorkingHours(dateTime, null))
			.isInstanceOf(IllegalArgumentException.class);
		
		assertThrown(() -> newAp.isInWorkingHours(null, dateTime))
			.isInstanceOf(IllegalArgumentException.class);
		
		ControllerFacade.setPatientSelected(null);
		assertThrown(() -> newAp.isInWorkingHours(null, null))
			.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void testIsInWorkingHours_IllegalParameter() {
		NewAppointment newAp = new NewAppointment();
		Patient patient = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		ControllerFacade.setPatientSelected(patient);
		LocalDateTime dateTimeStart = LocalDateTime.now();
		LocalDateTime dateTimeEnd = dateTimeStart.minusHours(1);
		
		assertThrown(() -> newAp.isInWorkingHours(dateTimeStart, dateTimeEnd))
			.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void testIsInWorkingHours_Valid() {
		NewAppointment newAp = new NewAppointment();
		LocalDateTime monday9AM = LocalDateTime.of(2015, 6, 1, 9, 0);
		LocalDateTime tuesday9AM = LocalDateTime.of(2015, 6, 2, 9, 0);
		
		// Set up the patient, doctor, and calendar with its workinghours
		Patient patient = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		ControllerFacade.setPatientSelected(patient);
		WorkingHours workingHours = new WorkingHours();
		workingHours.setMorningFrom(monday9AM.toLocalTime().minusHours(2));
		workingHours.setMorningTo(monday9AM.toLocalTime().plusHours(2));
		Calendar calendar = new Calendar();
		CalendarWorkingHours calendarWorkingHours = new CalendarWorkingHours(calendar, workingHours, DayOfWeek.MONDAY);
		Set<CalendarWorkingHours> setWorkingHours = new HashSet<>();
		setWorkingHours.add(calendarWorkingHours);
		calendar.setCalendarWorkingHours(setWorkingHours);
		patient.setDoctor(new Doctor(calendar));
		
		// Tests
		assertTrue(newAp.isInWorkingHours(monday9AM, monday9AM.plusMinutes(30)));
		assertTrue(newAp.isInWorkingHours(monday9AM.minusHours(1), monday9AM.minusMinutes(30)));
		assertFalse(newAp.isInWorkingHours(monday9AM.plusHours(3), monday9AM.plusHours(4)));
		assertFalse(newAp.isInWorkingHours(monday9AM.plusHours(1), monday9AM.plusHours(3)));
		assertFalse(newAp.isInWorkingHours(tuesday9AM, tuesday9AM.plusHours(1)));
	}
	
	/* -- -- */
	@Test
	public void addAppointmentTest(){
		at.itb13.oculus.presentation.gwt.shared.Patient patientshared = 
				new at.itb13.oculus.presentation.gwt.shared.Patient();
		NewAppointment newApp = new NewAppointment();
		Patient patientdomain = new Patient();
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		
		patientshared.setName(patientdomain.getFirstName()+" "+patientdomain.getLastName());
		patientshared.setDoctor(patientdomain.getDoctor().getUser().getFirstName()+" "+
								patientdomain.getDoctor().getUser().getLastName());
		patientshared.setSin(patientdomain.getSocialInsuranceNr());
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent ce = 
				new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
		LocalDateTime ldt = LocalDateTime.now().plusWeeks(1);
		String type = EventTypeDao.getInstance().list().get(0).getEventTypeName();
		ce.setDate(ldt.toString());
		ce.setDoctorOrthoptist("David Ruben");
		ce.setType(type);
		ce.setReason("first Appointment");
		assertTrue(newApp.addAppointment(patientshared, ce));
		
		Set<CalendarEvent> ces = patientdomain.getCalendarevents();
		for(CalendarEvent c:ces){
			CalendarEventDao.getInstance().makeTransient(c);
		}
		
	}
	@Test
	public void addAppointmentTestWithEventTypeNull(){
		at.itb13.oculus.presentation.gwt.shared.Patient patientshared = 
				new at.itb13.oculus.presentation.gwt.shared.Patient();
		NewAppointment newApp = new NewAppointment();
		Patient patientdomain = new Patient();
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		
		patientshared.setName(patientdomain.getFirstName()+" "+patientdomain.getLastName());
		patientshared.setDoctor(patientdomain.getDoctor().getUser().getFirstName()+" "+
								patientdomain.getDoctor().getUser().getLastName());
		patientshared.setSin(patientdomain.getSocialInsuranceNr());
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent ce = 
				new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
		LocalDateTime ldt = LocalDateTime.now().plusWeeks(1);
		
		ce.setDate(ldt.toString());
		ce.setDoctorOrthoptist("David Ruben");
		ce.setType("xy");
		ce.setReason("first Appointment");
		assertFalse(newApp.addAppointment(patientshared, ce));
		
//		Set<CalendarEvent> ces = patientdomain.getCalendarevents();
//		for(CalendarEvent c:ces){
//			CalendarEventDao.getInstance().makeTransient(c);
//		}
		
	}
	@Test
	public void addAppointmentTest_Null(){
		at.itb13.oculus.presentation.gwt.shared.Patient patientshared = 
				new at.itb13.oculus.presentation.gwt.shared.Patient();
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent ce = 
				new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
		Patient patientdomain = new Patient();
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		
		patientshared.setName(patientdomain.getFirstName()+" "+patientdomain.getLastName());
		patientshared.setDoctor(patientdomain.getDoctor().getUser().getFirstName()+" "+
								patientdomain.getDoctor().getUser().getLastName());
		patientshared.setSin(patientdomain.getSocialInsuranceNr());
		LocalDateTime ldt = LocalDateTime.now().plusWeeks(1);
		ce.setDate(ldt.toString());
		ce.setDoctorOrthoptist("David Ruben");
		ce.setType("Standardtreatment");
		ce.setReason("first Appointment");
		
		
		NewAppointment newApp = new NewAppointment();
		assertThrown(() -> newApp.addAppointment(null, null))
			.isInstanceOf(NullPointerException.class);	
		assertThrown(() -> newApp.addAppointment(patientshared,null))
			.isInstanceOf(NullPointerException.class);	
		assertThrown(() -> newApp.addAppointment(null, ce))
			.isInstanceOf(NullPointerException.class);	
	}
	
	@Test
	public void deleteAppointmentTest_Null(){
		NewAppointment newApp = new NewAppointment();
		assertThrown(() -> newApp.deleteAppointment(-1))
			.isInstanceOf(IllegalArgumentException.class);	
	}
	
	@Test
	public void deleteAppointmentTest(){
		Patient patientdomain;
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		CalendarEvent ce = new CalendarEvent();
		NewAppointment newApp = new NewAppointment();
		EventType eventType = (EventType) ControllerFacade.getInstance().getAllEventTypes().get(0);
		ce.setCalendar(patientdomain.getDoctor().getCalendar());
		ce.setDescription("firstAppointment");
		ce.setEventStart(LocalDateTime.now().plusWeeks(1));
		ce.setEventEnd(ce.getEventStart().plusMinutes(eventType.getEstimatedTime()));
		ce.setPatient(patientdomain);
		ce.setEventType(eventType);
		CalendarEventDao.getInstance().makePersistent(ce);
		Set<CalendarEvent> ces = patientdomain.getCalendarevents();
		for(CalendarEvent c:ces){
			assertTrue(newApp.deleteAppointment(c.getCalendarEventId()));
		}
		
	}
	
	
	@Test
	public void getPatientAppointmentTest(){
		at.itb13.oculus.presentation.gwt.shared.Patient patientshared = 
				new at.itb13.oculus.presentation.gwt.shared.Patient();
		Patient patientdomain;
		CalendarEvent cedomain = new CalendarEvent();
		int cedomainId = 0;
		NewAppointment newApp = new NewAppointment();
		
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent ce = 
				new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
		patientshared.setName(patientdomain.getFirstName()+" "+patientdomain.getLastName());
		patientshared.setDoctor(patientdomain.getDoctor().getUser().getFirstName()+" "+
								patientdomain.getDoctor().getUser().getLastName());
		patientshared.setSin(patientdomain.getSocialInsuranceNr());
		
		
		EventType eventType = (EventType) ControllerFacade.getInstance().getAllEventTypes().get(0);
		cedomain.setCalendar(patientdomain.getDoctor().getCalendar());
		cedomain.setDescription("firstAppointment");
		cedomain.setEventStart(LocalDateTime.now().plusWeeks(1));
		cedomain.setEventEnd(cedomain.getEventStart().plusMinutes(eventType.getEstimatedTime()));
		cedomain.setPatient(patientdomain);
		cedomain.setEventType(eventType);
		CalendarEventDao.getInstance().makePersistent(cedomain);
		Set<CalendarEvent> ces = patientdomain.getCalendarevents();
		cedomainId = ces.iterator().next().getCalendarEventId();
		ce = newApp.getPatientAppointment(patientshared);
		assertTrue(ce.getId()==cedomainId);
		CalendarEventDao.getInstance().makeTransient(cedomain);
	}
	
	@Test
	public void getPatientAppointmentTestWithPastAppointment(){
		at.itb13.oculus.presentation.gwt.shared.Patient patientshared = 
				new at.itb13.oculus.presentation.gwt.shared.Patient();
		Patient patientdomain;
		CalendarEvent cedomain = new CalendarEvent();
		NewAppointment newApp = new NewAppointment();
		
		patientdomain = PatientDao.getInstance().findByEmail(PATIENT1_EMAIL);
		at.itb13.oculus.presentation.gwt.shared.CalendarEvent ce = 
				new at.itb13.oculus.presentation.gwt.shared.CalendarEvent();
		patientshared.setName(patientdomain.getFirstName()+" "+patientdomain.getLastName());
		patientshared.setDoctor(patientdomain.getDoctor().getUser().getFirstName()+" "+
								patientdomain.getDoctor().getUser().getLastName());
		patientshared.setSin(patientdomain.getSocialInsuranceNr());
		
		
		EventType eventType = (EventType) ControllerFacade.getInstance().getAllEventTypes().get(0);
		cedomain.setCalendar(patientdomain.getDoctor().getCalendar());
		cedomain.setDescription("firstAppointment");
		cedomain.setEventStart(LocalDateTime.now().minusWeeks(1));
		cedomain.setEventEnd(cedomain.getEventStart().plusMinutes(eventType.getEstimatedTime()));
		cedomain.setPatient(patientdomain);
		cedomain.setEventType(eventType);
		CalendarEventDao.getInstance().makePersistent(cedomain);
		ce = newApp.getPatientAppointment(patientshared);
		assertTrue(ce==null);
		CalendarEventDao.getInstance().makeTransient(cedomain);
	}
	
	@Test
	public void getPatientAppointmentTest_Null(){
		NewAppointment newApp = new NewAppointment();
		assertThrown(() -> newApp.getPatientAppointment(null))
		.isInstanceOf(NullPointerException.class);	
	}
}
