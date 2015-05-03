
	package at.itb13.oculustests.application;

	import org.junit.Rule;
	import org.junit.Test;
	import org.junit.rules.ExpectedException;

	import at.itb13.oculus.application.exceptions.InvalidInputException;
	import at.itb13.oculus.application.receptionist.NewPatient;
	import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;

	/**
	 * tests the InvalidInputException for methode createPatient
	 * 
	 * @author Karin Trommelschläger
	 * @date 30.04.2015
	 * 
	 */
	public class NewPatient_UnitTests {
		@Rule
		public ExpectedException thrown = ExpectedException.none();
		@Test
		public void createPatientWithNullParameters() throws InvalidInputException{
			thrown.expect(InvalidInputException.class);
			NewPatient np = new NewPatient();
			PatientRO p;
			p = np.createPatient(null, null, null, null, 
							null, null, null, null, null, null, null, null);
			
				
		}
	}

