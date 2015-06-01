package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.ICalendarEvent;

/**
 * Adapter for the ICalendarEvent interface from TeamF.
 *
 * @author Florin Metzler
 * @since 18.05.2015
 */
public class CalendarEventAdapter implements ICalendarEvent, IAdapter {
	private CalendarEvent _calendarEvent;
	
	public CalendarEventAdapter() { }
	
	public CalendarEventAdapter(CalendarEvent calendarEvent){
		_calendarEvent = calendarEvent;
	}
	
	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		return _calendarEvent;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#getId()
	 */
	@Override
	public int getId() {
		return _calendarEvent.getCalendarEventId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#setId(int)
	 */
	@Override
	public void setId(int id) {
		_calendarEvent.setCalendarEventId(id);

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#getDescription()
	 */
	@Override
	public String getDescription() {
		return _calendarEvent.getDescription();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		_calendarEvent.setDescription(description);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#getEventStart()
	 */
	@Override
	public Date getEventStart() {
		LocalDateTime localDateTime = _calendarEvent.getEventStart();
		Date date = null;
		if(localDateTime != null){
			date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());	
		}
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#setEventStart(java.util.Date)
	 */
	@Override
	public void setEventStart(Date eventStart) {
		LocalDateTime localDateTime = eventStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_calendarEvent.setEventStart(localDateTime);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#getEventEnd()
	 */
	@Override
	public Date getEventEnd() {
		LocalDateTime localDateTime = _calendarEvent.getEventEnd();
		Date date = null;
		if(localDateTime != null){
			date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());		
		}
			
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.ICalendarEvent#setEventEnd(java.util.Date)
	 */
	@Override
	public void setEventEnd(Date eventEnd) {
		LocalDateTime localDateTime = eventEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		_calendarEvent.setEventEnd(localDateTime);
	}

}
