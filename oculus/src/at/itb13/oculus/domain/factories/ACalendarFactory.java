package at.itb13.oculus.domain.factories;

import java.util.Set;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.CalendarWorkingHours;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.IDoctor;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 12.05.2015
 */
public abstract class ACalendarFactory {

	protected static ACalendarFactory _factory;
	
	public static ACalendarFactory getCalendarFactroy() {
		return _factory;
	}
	
	/**
	 * 
	 * 
	 * @return Calendar without parameters
	 */
	public abstract ICalendar createCalendar();
	
	/**
	 * 
	 * 
	 * @param title  name of the owner of the calendar
	 * @param doctor doctor assigned to the calendar
	 * @param orthoptist orthoptist assigned to the calendar
	 * @param calendarEvents Set of CalendarEvents
	 * @param calendarWorkingHours Set of CalendarWorkingHours
	 * @return Calendar with the specified parameters
	 */
	public abstract ICalendar createCalendar(String title, IDoctor doctor, Orthoptist orthoptist, Set<CalendarEvent> calendarEvents, 
			  Set<CalendarWorkingHours> calendarWorkingHours);
}
