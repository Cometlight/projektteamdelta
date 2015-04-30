package at.itb13.oculus.domain.interfaces;

import java.time.LocalDate;

/**
 * This Interface defines the required methodes of the Patient class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IPatient {
	
	public abstract IDoctor getDoctor();
	
	public abstract String getSocialInsuranceNr();
	
	public abstract String getFirstName();
	
	public abstract String getLastName();
	
	public abstract LocalDate getDateOfBirth();
}
