package at.itb13.oculus.domain;

import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.support.WeekDayToDayOfWeekConverter;
import at.itb13.teamD.domain.interfaces.ICalendarWorkingHours;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "calendarworkinghours", catalog = "oculus_d")
public class CalendarWorkingHours implements java.io.Serializable, ICalendarWorkingHours{
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(CalendarWorkingHours.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _calendarWorkingHoursId;
	private Calendar _calendar;
	private WorkingHours _workingHours;
	private DayOfWeek _weekDayKey;
	
	public CalendarWorkingHours() {
	}

	public CalendarWorkingHours(DayOfWeek weekDayKey) {
		_weekDayKey = weekDayKey;
	}

	public CalendarWorkingHours(Calendar calendar, WorkingHours workinghours,
			DayOfWeek weekDayKey) {
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

	@Convert(converter = WeekDayToDayOfWeekConverter.class)
	@Column(name = "weekDayKey", nullable = false)
	public DayOfWeek getWeekDayKey() {
		return _weekDayKey;
	}

	public void setWeekDayKey(DayOfWeek weekDayKey) {
		_weekDayKey = weekDayKey;
	}
}
