package at.itb13.oculus.application.calendar;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.itb13.oculus.application.IController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.CalendarEvent;

/**
 * TODO
 *
 * @author Florin Metzler
 * @since 09.04.2015
 */
public class CalendarController implements IController {
	
	/**
	 * Loads a list of Calendar Event in a chosen timespan.
	 * 
	 * @param startDate the start Date of the timespan.
	 * @param endDate the end Date of the timespan.
	 * @return A list of CalendarEvent.
	 * @throws InvalidInputException when the startDate is bigger then the endDate.
	 */
	public List<CalendarEvent> getCalendarEventsByTimespan(LocalDateTime startDate, LocalDateTime endDate) throws InvalidInputException{
		Calendar calendar = new Calendar();
		List<CalendarEvent> c = new LinkedList<>();
		if(startDate.compareTo(endDate) < 0){
			c = calendar.getCalendarEvents(startDate, endDate);
			return c;
		}else{
			throw new InvalidInputException();
		}
	}
}
