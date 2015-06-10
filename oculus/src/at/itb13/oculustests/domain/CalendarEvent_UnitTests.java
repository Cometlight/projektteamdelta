package at.itb13.oculustests.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.technicalServices.dao.CalendarEventDao;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 08.06.2015
 */
public class CalendarEvent_UnitTests {

	@Test
	public void isInTimeSpan() {
		CalendarEvent event = CalendarEventDao.getInstance().findById(100);
		
		//Event start: 10:00, end: 10:30
		LocalDateTime startTime = LocalDateTime.of(2015, 4, 27, 10, 00);
		LocalDateTime endTime = LocalDateTime.of(2015, 4, 27, 10, 30);
		
		assertTrue(event.isInTimespan(startTime, endTime));
		
		startTime = LocalDateTime.of(2015, 4, 27, 9, 50);
		endTime = LocalDateTime.of(2015, 4, 27, 10, 40);
		
		assertTrue(event.isInTimespan(startTime, endTime));
		
		startTime = LocalDateTime.of(2015, 4, 27, 10, 35);
		endTime = LocalDateTime.of(2015, 4, 27, 10, 55);

		assertFalse(event.isInTimespan(startTime, endTime));
		
		startTime = LocalDateTime.of(2015, 4, 27, 10, 5);
		endTime = LocalDateTime.of(2015, 4, 27, 10, 30);

		assertFalse(event.isInTimespan(startTime, endTime));
		
		startTime = LocalDateTime.of(2015, 4, 27, 10, 0);
		endTime = LocalDateTime.of(2015, 4, 27, 10, 20);

		assertFalse(event.isInTimespan(startTime, endTime));
	}

}
