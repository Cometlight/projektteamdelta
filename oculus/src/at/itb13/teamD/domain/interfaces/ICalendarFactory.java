package at.itb13.teamD.domain.interfaces;

import java.util.Set;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 13.05.2015
 */
public interface ICalendarFactory {

	/**
	 * 
	 * @return Calendar without parameters
	 */
	public abstract ICalendar createCalendar();
	
	/**
	 * 
	 * @param title  name of the owner of the calendar
	 * @param doctor doctor assigned to the calendar
	 * @param orthoptist orthoptist assigned to the calendar
	 * @param calendarEvents Set of CalendarEvents
	 * @param calendarWorkingHours Set of CalendarWorkingHours
	 * @return Calendar with the specified parameters
	 */
	public abstract ICalendar createCalendar(String title, IDoctor doctor, IOrthoptist orthoptist, Set<ICalendarEvent> calendarEvents, 
			  Set<ICalendarWorkingHours> calendarWorkingHours);
}
