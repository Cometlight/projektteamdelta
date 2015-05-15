package at.itb13.teamD.domain.interfaces;

import java.time.LocalTime;

/**
 * This Interface defines the required methodes of the WorkingHours class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IWorkingHours {
	
	public abstract LocalTime getMorningFrom();
	
	public abstract LocalTime getMorningTo();
	
	public abstract LocalTime getAfternoonFrom();
	
	public abstract LocalTime getAfternoonTo();
}
