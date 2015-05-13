package at.itb13.oculus.domain.factories;

import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.CalendarWorkingHours;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.ICalendarFactory;
import at.itb13.oculus.domain.interfaces.IDoctor;

/**
 * Factory, which provides a new calendar
 *
 * @author Florin Metzler
 * @since 10.05.2015
 */
public class CalendarFactory implements ICalendarFactory{
	
	
	/**
	 *  
	 * @return Calendar without parameters
	 */
	public ICalendar createCalendar(){
		Calendar calendar = Calendar.getInstance();
		return calendar;
	}
	/**
	 *  
	 * @param title  name of the owner of the calendar
	 * @param doctor doctor assigned to the calendar
	 * @param orthoptist orthoptist assigned to the calendar
	 * @param calendarEvents Set of CalendarEvents
	 * @param calendarWorkingHours Set of CalendarWorkingHours
	 * @return Calendar with the specified parameters
	 */
	public ICalendar createCalendar(String title, IDoctor doctor, Orthoptist orthoptist, Set<CalendarEvent> calendarEvents, 
			  Set<CalendarWorkingHours> calendarWorkingHours){
		ICalendar calendar = Calendar.getInstance(title, doctor, orthoptist, calendarEvents, calendarWorkingHours);
		return calendar;
	}
	
	/**
	 * TODO: get
	 */
}
