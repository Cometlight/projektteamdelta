package at.itb13.oculus.domain.interfaces;

import java.time.LocalDateTime;

/**
 * This Interface defines the required methodes of the WorkingHours class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IWorkingHours {
	
	public abstract LocalDateTime getMorningFrom();
	
	public abstract LocalDateTime getMorningTo();
	
	public abstract LocalDateTime getAfternoonFrom();
	
	public abstract LocalDateTime getAfternoonTo();
}
