package at.itb13.oculus.domain.readonlyinterfaces;

import java.time.LocalDateTime;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.Patient;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 17.04.2015
 */
public interface CalendarEventRO {
	Integer getCalendarEventId();
	Calendar getCalendar();
	EventType getEventtype();
	Patient getPatient();
	LocalDateTime getEventStart();
	LocalDateTime getEventEnd();
	String getDescription();
	String getPatientName();
	boolean isOpen();
}
