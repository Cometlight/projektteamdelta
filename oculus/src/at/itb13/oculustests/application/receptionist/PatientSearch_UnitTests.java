package at.itb13.oculustests.application.receptionist;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import at.itb13.oculus.application.receptionist.PatientSearch;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.teamD.application.exceptions.InvalidInputException;

/**
 * Tests for the methodes of Class PatientSearch
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
		String name = "";
		PatientSearch ps = new PatientSearch();
		ps.searchPatient(name);	
	}
	@Test
	public void searchPatientByNameWithFirstName() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String fname = "abc";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatient(fname);
		assertEquals (true, patientList!=null);
	}
	@Test
	public void searchPatientByNameWithLastName() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String lname = "abc";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatient(lname);
		assertEquals (true, patientList!=null);
	}
	@Test
	public void searchPatientBySocialInsuranceNrWithNotValidNr() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String sin = "123456789";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatient(sin);
		assertEquals(true,patientList.isEmpty());
	}
	@Test
	public void searchPatientBySocialInsuranceNrWithExistingNr() throws InvalidInputException{
		List<? extends PatientRO> patientList = null;
		String sin = "3333333333";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatient(sin);
		assertEquals(true,patientList.size()==1);
	}
	@Test
	public void searchPatientBySocialInsuranceNrWithNotExistingNr() throws InvalidInputException{
	List<? extends PatientRO> patientList = null;
		String sin = "3333333332";
		PatientSearch ps = new PatientSearch();
		patientList = ps.searchPatient(sin);
		assertEquals(true,patientList.size()==0);
	}
}
