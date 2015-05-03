package at.itb13.oculustests.application;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.receptionist.PatientSearch;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;

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
	@Test
	public void searchPatientByNameWithFirstName() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String fn = "abc";
		String ln = "";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatientByName(fn, ln);
		assertEquals (true, patientList!=null);
	}
	@Test
	public void searchPatientByNameWithLastName() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String fn = "";
		String ln = "abc";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatientByName(fn, ln);
		assertEquals (true, patientList!=null);
	}
	@Test
	public void searchPatientByNameWithFullName() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String fn = "abc";
		String ln = "xyz";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatientByName(fn, ln);
		assertEquals (true, patientList!=null);
	}
	@Rule
	public ExpectedException thrown2 = ExpectedException.none();
	@Test
	public void searchPatientBySocialInsuranceNrWithNotValidNr() throws InvalidInputException{
		thrown2.expect(InvalidInputException.class);
		String sin = "123456789";
		PatientSearch ps = new PatientSearch();
		ps.searchPatientBySocialInsuranceNr(sin);	
	}
}
