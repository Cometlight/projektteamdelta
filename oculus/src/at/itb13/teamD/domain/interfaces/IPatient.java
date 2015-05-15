package at.itb13.teamD.domain.interfaces;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * This Interface defines the required methods of the Patient class.
 *
 * @author Florin Metzler
 * @since 30.04.2015
 */
public interface IPatient {
	
	public abstract Integer getPatientId();
	
	public abstract IDoctor getDoctor();
	
	public abstract String getSocialInsuranceNr();
	
	public abstract String getFirstName();
	
	public abstract String getLastName();
	
	public abstract LocalDate getDateOfBirth();
	
	public static boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		String pattern = "^\\d{" + 10 + "}$";
		return socialInsuranceNr != null && Pattern.matches(pattern, socialInsuranceNr);
	}
	
	public abstract IGender getIGender();
	
	public abstract String getStreet();
	
	public abstract String getPostalCode();
	
	public abstract String getCity();
	
	public abstract String getCountryIsoCode();
	
	public abstract String getPhone();
	
	public abstract String getEmail();
	
	public enum IGender {
		M, F;
	}
}
