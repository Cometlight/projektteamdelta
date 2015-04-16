


import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.oculus.technicalServices.dao.QueueDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class TestClassDani {
	static final Logger logger = LogManager.getLogger(TestClassDani.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		foo1();
//		foo2();
		bar();
//		bar2();
		foo3();
		foo4();
		
		System.exit(0);
	}
	
	private static void foo4() {
		System.out.println("--- foo4: ---");
		for(Queue q : QueueDao.getInstance().list()) {
			System.out.println(q.getQueueEntries().size());
		}
		
		Queue q = QueueDao.getInstance().findById(1, null);
		System.out.println(q.getDoctor().getUser().getFirstName());
		
		// TODO: Check if dao.makePersistent is working as intended
	}
	
	private static void foo3() {
		PatientSearch pS = ControllerFacade.getInstance().getPatientSearch();
	}
	
	private static void foo1() {
		Patient pat = PatientDao.getInstance().findBySocialInsuranceNr("5736198542");	// should be Russel Fuller
		System.out.println(pat.getFirstName() + " " + pat.getLastName() + " (" + pat.getSocialInsuranceNr() + ")");
		
//		CalendarEvent ev1 = patDao.loadCalendarevents(pat).iterator().next();
//		int numberOfCalEvents = pat.getCalendarevents().size();
//		
//		System.out.println(numberOfCalEvents + " calendarEvents associated with the patient");
//		System.out.print("First calendarEvent:\n\t");
//		System.out.println(ev1.getDescription());
	}
	
//	private static void foo2() {
//		Doctor doctor = new DoctorDao().findById(1);
//		Queue queue = AllQueues.getInstance().getQueueByDoctorId(doctor.getDoctorId());
//		System.out.println(queue.getQueueEntries().size());
//		for (QueueEntry qE : queue.getQueueEntries()) {
//			System.out.println(qE.getPatient().getFirstName());
//		}
//		System.out.print("DOCTOR: ");
//		System.out.println(queue.getQueueEntries().get(0).getPatient().getDoctor().getUser().getFirstName());
//		System.out.println(queue.getQueueEntries().get(0).getPatient().getDoctor().getUser().getFirstName());
//		for(CalendarEvent cE : queue.getQueueEntries().get(1).getPatient().getCalendarevents()) {
//			System.out.println(cE.getDescription());
//		}
//	}
	
	private static void bar(){
		Queue queue = QueueDao.getInstance().findById(1,null);
		
		for(QueueEntry q : queue.getQueueEntries()){
			System.out.println(q.getPatient().getFirstName());
		}
		
	}
	
	private static void bar2(){
		Patient p = new Patient();
		p.setGender("M");
		p.setFirstName("Hugo");
		p.setLastName("Clonk");
		Doctor doc = DoctorDao.getInstance().findById(1);
//		new DoctorDao().finda
		p.setDoctor(doc);
		
		PatientDao.getInstance().makePersistent(p);
	}

}
