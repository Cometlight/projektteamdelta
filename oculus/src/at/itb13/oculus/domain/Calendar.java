package at.itb13.oculus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "calendar", catalog = "oculusdb")
public class Calendar implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(Calendar.class.getName());
	
	private Integer _calendarId;
	private String _title;
	private Set<Doctor> _doctors = new HashSet<Doctor>(0);
	private Set<Orthoptist> _orthoptists = new HashSet<Orthoptist>(0);
	private Set<CalendarEvent> _calendarevents = new HashSet<CalendarEvent>(0);
	private Set<CalendarWorkingHours> _calendarworkinghours = new HashSet<CalendarWorkingHours>(
			0);

	public Calendar() {
	}

	public Calendar(String title, Set<Doctor> doctors,
			Set<Orthoptist> orthoptists, Set<CalendarEvent> calendarevents,
			Set<CalendarWorkingHours> calendarworkinghourses) {
		this._title = title;
		this._doctors = doctors;
		this._orthoptists = orthoptists;
		this._calendarevents = calendarevents;
		this._calendarworkinghours = calendarworkinghourses;
	}
	
	/**
	 * Creates a list of Calendar Event in a chosen timespan.
	 * 
	 * @param startDate
	 *            the start Date of the timespan.
	 * @param endDate
	 *            the end Date of the timespan.
	 * @return A list of CalendarEvent.
	 */
	public List<CalendarEvent> getCalendarEvents(Date startDate, Date endDate) {
		List<CalendarEvent> curEvents = new LinkedList<>();
		for (CalendarEvent c : _calendarevents) {
			if (c.isInTimespan(startDate, endDate)) {
				curEvents.add(c);
			}
		}
		return curEvents;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calendarId", unique = true, nullable = false)
	public Integer getCalendarId() {
		return this._calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this._calendarId = calendarId;
	}

	@Column(name = "title")
	public String getTitle() {
		return this._title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<Doctor> getDoctors() {
		return this._doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this._doctors = doctors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<Orthoptist> getOrthoptists() {
		return this._orthoptists;
	}

	public void setOrthoptists(Set<Orthoptist> orthoptists) {
		this._orthoptists = orthoptists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<CalendarEvent> getCalendarevents() {
		return this._calendarevents;
	}

	public void setCalendarevents(Set<CalendarEvent> calendarevents) {
		this._calendarevents = calendarevents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<CalendarWorkingHours> getCalendarworkinghourses() {
		return this._calendarworkinghours;
	}

	public void setCalendarworkinghourses(
			Set<CalendarWorkingHours> calendarworkinghourses) {
		this._calendarworkinghours = calendarworkinghourses;
	}

}
