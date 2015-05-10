package at.itb13.oculus.domain;

import java.util.Set;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 10.05.2015
 */
public class CalendarFactory {

private static CalendarFactory factory;
	
	private CalendarFactory(){	}
	
	public static CalendarFactory getInstance(){
		if(factory == null){
			factory = new CalendarFactory();
		}
		return factory;
	}
	
	public Calendar createCalendar(){
		Calendar calendar = Calendar.getInstance();
		return calendar;
	}
	
	public Calendar createCalendar(String title, Doctor doctor, Orthoptist orthoptist, Set<CalendarEvent> calendarEvents, 
			  Set<CalendarWorkingHours> calendarWorkingHours){
		Calendar calendar = Calendar.getInstance(title, doctor, orthoptist, calendarEvents, calendarWorkingHours);
		return calendar;
	}
}
