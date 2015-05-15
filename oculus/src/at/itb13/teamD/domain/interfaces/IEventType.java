package at.itb13.teamD.domain.interfaces;

/**
 * TODO: This Interface defines the required methodes of the EventType class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IEventType {

	public abstract String getEventTypeName();
	
	public abstract Integer getEstimatedTime();
	
	public abstract String getDescription();
}
