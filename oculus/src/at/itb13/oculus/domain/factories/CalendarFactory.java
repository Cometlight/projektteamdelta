package at.itb13.oculus.domain.factories;

import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.ICalendarFactory;
import at.itb13.teamD.domain.interfaces.ICalendarWorkingHours;
import at.itb13.teamD.domain.interfaces.IDoctor;
import at.itb13.teamD.domain.interfaces.IOrthoptist;

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
	@Override
	public ICalendar createCalendar(String title, IDoctor doctor, IOrthoptist orthoptist, Set<ICalendarEvent> calendarEvents, 
			  Set<ICalendarWorkingHours> calendarWorkingHours){
		ICalendar calendar = Calendar.getInstance(title, doctor, orthoptist, calendarEvents, calendarWorkingHours);
		return calendar;
	}
}
