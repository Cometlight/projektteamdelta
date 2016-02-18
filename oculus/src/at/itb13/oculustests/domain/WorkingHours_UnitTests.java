package at.itb13.oculustests.domain;

import static at.itb13.oculustests.exceptioncatcher.ThrowableAssertion.assertThrown;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import at.itb13.oculus.domain.WorkingHours;

public class WorkingHours_UnitTests {

	@Test
	public void testIsDateInWorkingHours_Null() {
		WorkingHours wH = new WorkingHours();
		
		assertThrown(() -> wH.isDateInWorkingHours(LocalTime.now(), null))
			.isInstanceOf(NullPointerException.class);
		
		assertThrown(() -> wH.isDateInWorkingHours(null, LocalTime.now()))
			.isInstanceOf(NullPointerException.class);
		
		assertThrown(() -> wH.isDateInWorkingHours(null, null))
			.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void testIDateInWorkingHours_Valid() {
		WorkingHours wH = new WorkingHours();
		wH.setMorningFrom(LocalTime.of(9, 0));
		wH.setMorningTo(LocalTime.of(11, 0));
		wH.setAfternoonFrom(LocalTime.of(13, 0));
		wH.setAfternoonTo(LocalTime.of(17, 0));
		
		assertTrue(wH.isDateInWorkingHours(LocalTime.of(9, 0), LocalTime.of(11, 0)));
		assertTrue(wH.isDateInWorkingHours(LocalTime.of(14, 0), LocalTime.of(15, 30)));
		assertFalse(wH.isDateInWorkingHours(LocalTime.of(8, 0), LocalTime.of(9, 45)));
		assertFalse(wH.isDateInWorkingHours(LocalTime.of(12, 0), LocalTime.of(12, 30)));
	}
}
