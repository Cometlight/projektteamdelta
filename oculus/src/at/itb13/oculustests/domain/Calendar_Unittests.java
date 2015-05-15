package at.itb13.oculustests.domain;


import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.WorkingHours;
import at.itb13.oculus.domain.factories.CalendarFactory;

import java.time.DayOfWeek;

/**
 * TODO: JUnit-Tests for methods of Calendar
 * 
 * @author Karin Trommelschläger
 * @date 12.05.2015
 * 
 */
public class Calendar_Unittests {
	@Test
	public void getCalendarEventsForTimespan_Test(){
		List<CalendarEvent> ce = null;
		Calendar cal = Calendar.getInstance();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String strDate2 = "2015-04-01 10:11";
		LocalDateTime endDate = LocalDateTime.parse(strDate2, formatter);
		LocalDateTime startDate = LocalDateTime.now();
		ce = cal.getCalendarEventsForTimespan(startDate, endDate);
		assertEquals(true, ce!=null);
	}
	
	@Test
	public void isOneCalendarEventinTimespan_Test(){
		Calendar cal = Calendar.getInstance();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String strDate2 = "2015-04-01 10:11";
		LocalDateTime endDate = LocalDateTime.parse(strDate2, formatter);
		LocalDateTime startDate = LocalDateTime.now();
		assertEquals(false, cal.isOneCalendarEventInTimespan(startDate, endDate));
	}
	
	@Test
	public void getCalendarEventById(){
		Calendar cal = Calendar.getInstance();
		CalendarEvent ce;
		ce = (CalendarEvent) cal.getCalendarEventById(1);
		assertEquals(true, ce==null);
	}
	
	@Test
	public void getWorkingHoursOfWeekDay_Test(){
		DayOfWeek day = DayOfWeek.MONDAY;
		WorkingHours wh = null;
		Calendar cal = Calendar.getInstance();
		wh = cal.getWorkingHoursOfWeekDay(day);
		assertEquals(true, wh!=null);
	}

}
