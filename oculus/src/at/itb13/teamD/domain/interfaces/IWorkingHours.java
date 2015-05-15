package at.itb13.teamD.domain.interfaces;

import java.time.LocalTime;

/**
 * This Interface defines the required methods of the WorkingHours class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IWorkingHours {
	
	public abstract LocalTime getMorningFrom();
	
	public abstract LocalTime getMorningTo();
	
	public abstract LocalTime getAfternoonFrom();
	
	public abstract LocalTime getAfternoonTo();

	/**
	 * checks if the date(start, end) is in the WorkingHours.
	 * 
	 * @param start of the date.
	 * @param end of the date.
	 * @return
	 */
	public abstract boolean isDateInWorkingHours(LocalTime start, LocalTime end);
}
