package at.itb13.oculus.domain.readonlyinterfaces;

import java.util.Set;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.CalendarWorkingHours;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 16.04.2015
 */
public interface CalendarRO {
	Integer getCalendarId();
	String getTitle();
	Doctor getDoctor();
	Orthoptist getOrthoptist();
	Set<CalendarEvent> getCalendarEvents();
	Set<CalendarWorkingHours> getCalendarWorkingHours();
}
