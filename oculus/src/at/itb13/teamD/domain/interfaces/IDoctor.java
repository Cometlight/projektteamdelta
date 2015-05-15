package at.itb13.teamD.domain.interfaces;

/**
 * This Interface defines the required methods of the Doctor class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IDoctor {
	
	public abstract IUser getUser();
	
	public abstract ICalendar getICalendar();
}
