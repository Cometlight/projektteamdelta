package at.itb13.oculus.application.interfaces;

import java.util.List;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.teamD.domain.interfaces.IPatient;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 10.05.2015
 */
public interface IPatientSearch {

	public abstract List<? extends IPatient> searchPatient(String searchValue) throws InvalidInputException;	
	
}
