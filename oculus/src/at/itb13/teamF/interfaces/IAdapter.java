package at.itb13.teamF.interfaces;

/**
 * This interface should be implemented by every adapter.
 * getDomainObject() is used to retrieve the original domain object that is
 * managed by the adapter. This is, for example, useful when saving
 * the domain oject to the database using the persistence facade.
 * 
 * @author Daniel Scheffknecht
 * @date May 18, 2015
 */
public interface IAdapter {
	Object getDomainObject();
}
