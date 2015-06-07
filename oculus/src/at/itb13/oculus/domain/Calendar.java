package at.itb13.oculus.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.CalendarRO;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.ICalendarWorkingHours;
import at.itb13.teamD.domain.interfaces.IDoctor;
import at.itb13.teamD.domain.interfaces.IOrthoptist;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "calendar", catalog = "oculus_d")
public class Calendar implements java.io.Serializable, CalendarRO, ICalendar {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(Calendar.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _calendarId;
	private String _title;
	private Doctor _doctor;
	private Orthoptist _orthoptist;
	private Set<CalendarEvent> _calendarEvents = new HashSet<CalendarEvent>(0);
	private Set<CalendarWorkingHours> _calendarWorkingHours = new HashSet<CalendarWorkingHours>(
			0);

	public Calendar() {
	}

	public Calendar(String title, IDoctor doctor, IOrthoptist orthoptist, Set<ICalendarEvent> calendarevents,
			Set<ICalendarWorkingHours> calendarworkinghourses) {
		_title = title;
		_doctor = (Doctor) doctor;
		_orthoptist = (Orthoptist) orthoptist;
		_calendarEvents = (Set<CalendarEvent>) getListWithCalendarEvent(calendarevents);
		_calendarWorkingHours = (Set<CalendarWorkingHours>) getListWithCalendarWorkingHours(calendarworkinghourses);
	}
	
	public static ICalendar getInstance(String title, IDoctor doctor, IOrthoptist orthoptist, Set<ICalendarEvent> calendarevents, 
									  Set<ICalendarWorkingHours> calendarworkinghours){
		Calendar calendar = new Calendar(title, doctor, orthoptist, calendarevents, calendarworkinghours);
		return calendar;
	}
	@Transient
	public LocalDateTime findPossibleAppointment(LocalDateTime startTime, LocalDateTime endTime, int appointmentDuration){
		LocalDateTime appointmentTime = startTime.plusMinutes(appointmentDuration);
		boolean isFree = false;
		while(!isFree){
			isFree = true;
			for(CalendarEvent event : _calendarEvents){
				if(event.isInTimespan(startTime, appointmentTime)){
					if(!(event.getEventEnd().plusMinutes(appointmentDuration).isAfter(endTime))){
						startTime = event.getEventEnd();
						appointmentTime = startTime.plusMinutes(appointmentDuration);
						isFree = false;
						break;
					} else{
						startTime.plusDays(7);
						appointmentTime = startTime.plusMinutes(appointmentDuration);
						isFree = false;
						break;
					}
				}
			}
		}
		return startTime;
	}
	
	
	/**
	 * Creates a list of Calendar Event for a chosen timespan.
	 * 
	 * @param startDate the start Date of the timespan. (inclusive)
	 * @param endDate the end Date of the timespan. (inclusive)
	 * @return A list of CalendarEvent.
	 */
	@Transient
	@Override
	public List<CalendarEvent> getCalendarEventsForTimespan(LocalDateTime startDate, LocalDateTime endDate) {
		List<CalendarEvent> listCalEv = new LinkedList<>();
		for (CalendarEvent c : _calendarEvents) {
			if (c.isInTimespan(startDate, endDate)) {
				listCalEv.add(c);
			}
		}
		return listCalEv;
	}
	
	/**
	 * Checks if a list of CalendarEvent is in a timespan but also if a CalendarEvent starts befor timespan as long the end date
	 * is in timespan, or a CalendarEvent ends after timespan as long the start date is in timespan.
	 * 
	 * @param startDate the start Date of the timespan.
	 * @param endDate the end Date of the timespan.
	 * @return true when one CalendarEvent of the list is a part of the timespan.
	 */
	public boolean isOneCalendarEventInTimespan(LocalDateTime startDate, LocalDateTime endDate){
		if(startDate.isBefore(endDate)){
			for (CalendarEvent c : _calendarEvents) {
				if (c.isPartInTimespan(startDate, endDate)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * searches for a CalendarEvent by the calendarEventId.
	 * 
	 * @param calendarEventId the ID of the CalendarEvent.
	 * @return a CalendarEvent.
	 */
	@Transient
	public ICalendarEvent getCalendarEventById(int calendarEventId){
		for (CalendarEvent c : _calendarEvents) {
			if (c.getCalendarEventId() == calendarEventId) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns the Working Hours of a chosen day of the week.
	 * 
	 * @param weekDay is an Enum of the days of the week.
	 * @return A IWorkingHours.
	 */
	@Transient
	@Override
	public WorkingHours getWorkingHoursOfWeekDay(DayOfWeek weekDay) {
		WorkingHours workingHours = new WorkingHours();
		for(CalendarWorkingHours wh: _calendarWorkingHours) {
			if(wh.getWeekDayKey() == weekDay){
				workingHours = wh.getWorkinghours();
			}
		}
		return workingHours;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Override
	@Column(name = "calendarId", unique = true, nullable = false)
	public Integer getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		_calendarId = calendarId;
	}

	@Column(name = "title")
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Doctor getDoctor() {
		return _doctor;
	}

	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Orthoptist getOrthoptist() {
		return _orthoptist;
	}

	public void setOrthoptist(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<CalendarEvent> getCalendarEvents() {
		return _calendarEvents;
	}
	
	@Transient
	public Set<ICalendarEvent> getICalendarEvents() {
		Set<ICalendarEvent> newEvents = new HashSet<>();
		for(CalendarEvent event : _calendarEvents){
			newEvents.add((ICalendarEvent) event);
		}
		return newEvents;
	}

	public void setCalendarEvents(Set<ICalendarEvent> calendarEvents) {
		Set<CalendarEvent> newEvents = new HashSet<>();
		for(ICalendarEvent event : calendarEvents){
			newEvents.add((CalendarEvent) event);
		}
		_calendarEvents = newEvents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<CalendarWorkingHours> getCalendarWorkingHours() {
		return _calendarWorkingHours;
	}

	public void setCalendarWorkingHours (
			Set<CalendarWorkingHours> calendarWorkingHours) {
		_calendarWorkingHours = calendarWorkingHours;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public Collection<CalendarEvent> getListWithCalendarEvent(Set<ICalendarEvent> calendarevents) {
	    Collection<CalendarEvent> coll = new HashSet<>();
	    coll.addAll((Collection<? extends CalendarEvent>) calendarevents);
	    return coll;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public Collection<CalendarWorkingHours> getListWithCalendarWorkingHours(Set<ICalendarWorkingHours> calendarworkinghourses) {
	    Collection<CalendarWorkingHours> coll = new HashSet<>();
	    coll.addAll((Collection<? extends CalendarWorkingHours>) calendarworkinghourses);
	    return coll;
	}
	
	/**
	 * if a new CalendarEvent is created this method adds it to the list of all CalendarEvents for a specific calendar.
	 * 
	 * @param newEvent which is to add.
	 */
	@Override
	public void addCalendarEventToList(ICalendarEvent newEvent){
		_calendarEvents.add((CalendarEvent) newEvent);
	}
}
