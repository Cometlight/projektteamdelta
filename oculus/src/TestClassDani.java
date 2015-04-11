import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.AllQueues;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.technicalServices.dao.DoctorDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

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
		
		System.exit(0);
	}
	
	private static void foo1() {
		PatientDao patDao = new PatientDao();
		Patient pat = patDao.findBySocialInsuranceNr("5736198542");	// should be Russel Fuller
		System.out.println(pat.getFirstName() + " " + pat.getLastName() + " (" + pat.getSocialInsuranceNr() + ")");
		
		CalendarEvent ev1 = patDao.loadCalendarevents(pat).iterator().next();
		int numberOfCalEvents = pat.getCalendarevents().size();
		
		System.out.println(numberOfCalEvents + " calendarEvents associated with the patient");
		System.out.print("First calendarEvent:\n\t");
		System.out.println(ev1.getDescription());
	}
	
	private static void foo2() {
		Doctor doctor = new DoctorDao().findById(1);
		Queue queue = AllQueues.getInstance().getQueueByDoctorId(doctor.getDoctorId());
		System.out.println(queue.getQueueEntries().size());
	}

}
