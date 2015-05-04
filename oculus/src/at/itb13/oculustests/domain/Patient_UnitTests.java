

	package at.itb13.oculustests.domain;

	import static org.junit.Assert.*;

	import org.junit.Test;

	import at.itb13.oculus.domain.Patient;

	/**
	 * Tests constructor and methods of the class Patient.
	 * 
	 * @author Karin Trommelschläger
	 * @date 30.04.2015
	 * 
	 */
	public class Patient_UnitTests {
		
		@Test
		public void constructorWithoutParameters(){
			Patient p = new Patient();
			assertEquals (true, p!=null);
		}
		
		@Test
		public void constructorWithGenaeralInformationWithNullParameters(){
			String s = "";
			Patient p = new Patient(null, s, s, s, null, null, s, s, 
						s, s, s, s);
			assertEquals(true, p!= null);
		}
		
		@Test
		public void constructotWithAllInformationWithNullParameters(){
			Patient p = new Patient(null, null, null, null, null, null, null, null, 
						null, null, null, null, null, null, null ,null, null, null, null);
			assertEquals(true, p!=null);
		}
		
		@Test
		public void isSocialInsuranceNrValid_Test(){
			String nr1 = "1234567890";
			String nr2 = "123456789";
			String nr3 = "abcdefghij";
			
			assertEquals(true, Patient.isSocialInsuranceNrValid(nr1) );
			assertEquals(false, Patient.isSocialInsuranceNrValid(nr2) );
			assertEquals(false, Patient.isSocialInsuranceNrValid(nr3) );
		}
		
		

	}

