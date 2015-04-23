package at.itb13.oculus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "calendarworkinghours", catalog = "oculus_d")
public class CalendarWorkingHours implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(CalendarWorkingHours.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _calendarWorkingHoursId;
	private Calendar _calendar;
	private WorkingHours _workingHours;
	private WeekDayKey _weekDayKey;

	public enum WeekDayKey {
		MON, TUE, WED, THU, FRI, SAT, SUN
	}
	
	public CalendarWorkingHours() {
	}

	public CalendarWorkingHours(WeekDayKey weekDayKey) {
		_weekDayKey = weekDayKey;
	}

	public CalendarWorkingHours(Calendar calendar, WorkingHours workinghours,
			WeekDayKey weekDayKey) {
		_calendar = calendar;
		_workingHours = workinghours;
		_weekDayKey = weekDayKey;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calendarWorkingHoursId", unique = true, nullable = false)
	public Integer getCalendarWorkingHoursId() {
		return _calendarWorkingHoursId;
	}

	public void setCalendarWorkingHoursId(Integer calendarWorkingHoursId) {
		_calendarWorkingHoursId = calendarWorkingHoursId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarId")
	public Calendar getCalendar() {
		return _calendar;
	}

	public void setCalendar(Calendar calendar) {
		_calendar = calendar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workingHoursId")
	public WorkingHours getWorkinghours() {
		return _workingHours;
	}

	public void setWorkinghours(WorkingHours workinghours) {
		_workingHours = workinghours;
	}

	@Enumerated (EnumType.STRING)
	@Column(name = "weekDayKey", nullable = false)
	public WeekDayKey getWeekDayKey() {
		return _weekDayKey;
	}

	public void setWeekDayKey(WeekDayKey weekDayKey) {
		_weekDayKey = weekDayKey;
	}

}
