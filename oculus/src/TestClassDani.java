import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.domain.AllQueues;
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
		foo2();
		bar();
		bar2();
		foo3();
		
		System.exit(0);
	}
	
	private static void foo3() {
		PatientSearch pS = ControllerFacade.getInstance().getController(PatientSearch.class);
		CalendarController cC = ControllerFacade.getInstance().getController(CalendarController.class);
	}
	
	private static void foo1() {
		PatientDao patDao = new PatientDao();
		Patient pat = patDao.findBySocialInsuranceNr("5736198542");	// should be Russel Fuller
		System.out.println(pat.getFirstName() + " " + pat.getLastName() + " (" + pat.getSocialInsuranceNr() + ")");
		
//		CalendarEvent ev1 = patDao.loadCalendarevents(pat).iterator().next();
//		int numberOfCalEvents = pat.getCalendarevents().size();
//		
//		System.out.println(numberOfCalEvents + " calendarEvents associated with the patient");
//		System.out.print("First calendarEvent:\n\t");
//		System.out.println(ev1.getDescription());
	}
	
	private static void foo2() {
		Doctor doctor = new DoctorDao().findById(1);
		Queue queue = AllQueues.getInstance().getQueueByDoctorId(doctor.getDoctorId());
		System.out.println(queue.getQueueEntries().size());
		for (QueueEntry qE : queue.getQueueEntries()) {
			System.out.println(qE.getPatient().getFirstName());
		}
		System.out.print("DOCTOR: ");
		System.out.println(queue.getQueueEntries().get(0).getPatient().getDoctor().getUser().getFirstName());
		System.out.println(queue.getQueueEntries().get(0).getPatient().getDoctor().getUser().getFirstName());
		for(CalendarEvent cE : queue.getQueueEntries().get(1).getPatient().getCalendarevents()) {
			System.out.println(cE.getDescription());
		}
	}
	
	private static void bar(){
		QueueDao qDao = new QueueDao();
		Queue queue = qDao.findById(1,null);
		
		for(QueueEntry q : queue.getQueueEntries()){
			System.out.println(q.getPatient().getFirstName());
		}
		
	}
	
	private static void bar2(){
		Patient p = new Patient();
		p.setGender("M");
		p.setFirstName("Hugo");
		p.setLastName("Clonk");
		Doctor doc = new DoctorDao().findById(1);
//		new DoctorDao().finda
		p.setDoctor(doc);
		
		PatientDao dao = new PatientDao();
		dao.makePersistent(p);
	}

}
