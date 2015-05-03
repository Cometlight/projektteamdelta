package at.itb13.oculustests.application;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.receptionist.PatientSearch;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 03.05.2015
 * 
 */
public class PatientSearch_UnitTests {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void searchPatientByNameWithEmptyName() throws InvalidInputException{
		thrown.expect(InvalidInputException.class);
		String fn = "";
		String ln ="";
		PatientSearch ps = new PatientSearch();
		ps.searchPatientByName(fn,ln);	
	}
}
