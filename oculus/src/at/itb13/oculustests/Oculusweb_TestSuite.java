package at.itb13.oculustests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.itb13.oculustests.application.patient.NewAppointment_UnitTests;
import at.itb13.oculustests.domain.Patient_UnitTests;

@RunWith(Suite.class)
@SuiteClasses({
	NewAppointment_UnitTests.class,
	Patient_UnitTests.class
})
public class Oculusweb_TestSuite {

}
