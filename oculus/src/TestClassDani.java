


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarRO;
import at.itb13.oculus.domain.readonlyinterfaces.ExaminationProtocolRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueEntryRO;
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
	static final Logger _logger = LogManager.getLogger(TestClassDani.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		removeIfTest();
//		loggingTest();
//		exProtTest1();
//		listTest();
//		queue1Output();
//		queueTestMove1();
//		queueTest1();
//		queueTest2();
//		newTest();
//		calEvTest1();
//		foo1();
//		foo2();
//		bar();
//		bar2();
//		foo3();
//		foo4();
		
		System.exit(0);
	}
	
	private static void removeIfTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		
		list.removeIf(i -> i > 3 && i <= 5);
		list.forEach(System.out::println);
	}

	private static void loggingTest() {
		System.out.println("logging:");
		_logger.info("okay, info");
		System.out.println("...");
		_logger.error("error as well! D:");
		System.out.println("done logging");
	}

//	private static void exProtTest1() {
//		PatientController pCol = ControllerFacade.getInstance().getPatientController();
//		PatientRO patRO = null;
//		try {
////			patRO = pCol.searchPatientBySocialInsuranceNr("7531653399");
//			patRO = new at.itb13.oculus.application.receptionist.PatientSearch().searchPatientBySocialInsuranceNr("7531653399");
//		} catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("# not sorted:");
//		for(ExaminationProtocol eP : patRO.getExaminationprotocols()) {
//			System.out.println(eP.getStartProtocol());
//		}
//		
//		System.out.println("\n# sorted:");
//		for(ExaminationProtocolRO ePRO : pCol.getAllExaminationProtocolsSorted(patRO)) {
//			System.out.println(ePRO.getStartProtocol());
//		}
//	}

	private static void queue1Output() {
		QueueController q = ControllerFacade.getInstance().getQueueController(1, null);
		
		System.out.println("# Content of Queue (1, null): ");
		q.getQueueEntries().forEach(qEs -> System.out.println(qEs.getPatient().getFirstName()));
		
	}

	private static void listTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		ListIterator<Integer> it = list.listIterator();
		list.forEach(System.out::print);
		System.out.println();
		
		it.next();
		it.next();
		Integer temp = it.previous();
		it.remove();
		it.next();
		it.add(temp);
		
		list.forEach(System.out::print);
	}
	
	private static void queueTestMove1() {
		QueueController q = ControllerFacade.getInstance().getQueueController(1, null);
		
		System.out.println("# Content of Queue: ");
		q.getQueueEntries().forEach(qEs -> System.out.println(qEs.getPatient().getFirstName()));
		
		System.out.println("-- MOVE --");
		System.out.print("Result of Move: ");
		QueueEntryRO qERO = q.getQueueEntries().get(1);
		System.out.println(q.moveQueueEntry(qERO, true) + "\n");
		
		System.out.println("# Content of Queue: ");
		q.getQueueEntries().forEach(qEs -> System.out.println(qEs.getPatient().getFirstName()));
	}

	private static void queueTest2() {
		QueueController q = ControllerFacade.getInstance().getQueueController(1, null);
		
		System.out.println("# Content of Queue: ");
		q.getQueueEntries().forEach(qEs -> System.out.println(qEs.getPatient().getFirstName()));
		
		System.out.println("# First patient: ");
		QueueEntryRO qERO = q.peekQueueEntry();
		System.out.println(qERO.getPatient().getFirstName());
		
		System.out.println("-- POP --");
		System.out.print("Result of POP: ");
		System.out.println(q.popQueueEntry() + "\n");
		
		
		System.out.println("# Content of Queue: ");
		q.getQueueEntries().forEach(qEs -> System.out.println(qEs.getPatient().getFirstName()));
		
		System.out.println("# First patient: ");
		QueueEntryRO qERO2 = q.peekQueueEntry();
		System.out.println(qERO2.getPatient().getFirstName());
	}
	
//	private static void queueTest1() {
//		List<QueueController> queues = ControllerFacade.getInstance().getAllQueueController();
//		for(QueueController q : queues) {
//			System.out.println(q.getQueue().getDoctor() + " - " + q.getQueue().getOrthoptist());
//			try {
//				System.out.println("# Start 1");
//				q.getQueueEntries().forEach(qe -> System.out.println(qe.getPatient().getFirstName()));
//				PatientRO patientRO = ControllerFacade.getInstance().getPatientController().searchPatientBySocialInsuranceNr("7531653399");
//				System.out.println(q.pushQueueEntry(patientRO));
//				// or q.pushQueueEntry(patientRO, the associated CalendarEvent); // in order to change the CalendarEvent's state (isOpen)
//				
//				System.out.println("# Start 2");
//				q.getQueueEntries().forEach(qe -> System.out.println(qe.getPatient().getFirstName()));
//			} catch (InvalidInputException e) {
//				e.printStackTrace();
//			}
//			break;
//		}
//	}
	
	private static void newTest() {
		CalendarRO cal = ControllerFacade.getInstance().getCalendarController(1, null).getCalendar();
		CalendarController calCol = ControllerFacade.getInstance().getCalendarController(cal);
		System.out.println(calCol);
	}
	
	private static void calEvTest1() {
		CalendarController cC = ControllerFacade.getInstance().getCalendarController(1, null);
		System.out.println("CalDoc: " + cC.getCalendar().getDoctor().getUser().getFirstName());
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
		//PatientSearch pS = ControllerFacade.getInstance().getPatientSearch();
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
