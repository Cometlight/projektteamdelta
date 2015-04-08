import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Patient;
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
		PatientDao patDao = new PatientDao();
		Patient pat = patDao.findBySocialInsuranceNr("5678151082");	// should be Max Mustermann
		System.out.println(pat.getFirstName() + " " + pat.getLastName() + " (" + pat.getSocialInsuranceNr() + ")");
		
		CalendarEvent ev1 = patDao.loadCalendarevents(pat).iterator().next();
		int numberOfCalEvents = pat.getCalendarevents().size();
		
		System.out.println(numberOfCalEvents + " calendarEvents associated with the patient");
		System.out.print("First calendarEvent:\n\t");
		System.out.println(ev1.getDescription());
		
		System.exit(0);
	}

}
