package at.itb13.oculustests.application.calendar;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarFactory;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;

/**
 * Test for methods of CalendarController, except the methods, which have access to database
 * 
 * @author Karin Trommelschläger
 * @date 3 May 2015
 */
public class CalendarController_UnitTests {


		@Test
		public void getCalendarEventsInTimespanWithValidDates() throws InvalidInputException {
			List<? extends CalendarEventRO> calev = null;
			CalendarFactory cf = CalendarFactory.getInstance();
			Calendar calendar = cf.createCalendar();
			CalendarController cc = new CalendarController(calendar);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String strDate2 = "2015-04-01 10:11";
			LocalDateTime startDate = LocalDateTime.parse(strDate2, formatter);
			LocalDateTime endDate = LocalDateTime.now();
			calev = cc.getCalendarEventsInTimespan(startDate, endDate);
			assertEquals(true, calev!=null);
		}
		@Rule
		public ExpectedException thrown = ExpectedException.none();
		@Test
		public void getCalendarEventsInTimespanWithInvalidDates() throws InvalidInputException {
			thrown.expect(InvalidInputException.class);
			List<? extends CalendarEventRO> calev = null;
			CalendarFactory cf = CalendarFactory.getInstance();
			Calendar calendar = cf.createCalendar();
			CalendarController cc = new CalendarController(calendar);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String strDate2 = "2015-04-01 10:11";
			LocalDateTime endDate = LocalDateTime.parse(strDate2, formatter);
			LocalDateTime startDate = LocalDateTime.now();
			calev = cc.getCalendarEventsInTimespan(startDate, endDate);
		}

	

}
