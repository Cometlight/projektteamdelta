package at.itb13.oculus.domain;

import javax.persistence.Column;
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

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "calendarworkinghours", catalog = "oculusdb")
public class CalendarWorkingHours implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(CalendarWorkingHours.class.getName());
	
	private Integer calendarWorkingHoursId;
	private Calendar calendar;
	private WorkingHours workinghours;
	private String weekDayKey;

	public CalendarWorkingHours() {
	}

	public CalendarWorkingHours(String weekDayKey) {
		this.weekDayKey = weekDayKey;
	}

	public CalendarWorkingHours(Calendar calendar, WorkingHours workinghours,
			String weekDayKey) {
		this.calendar = calendar;
		this.workinghours = workinghours;
		this.weekDayKey = weekDayKey;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calendarWorkingHoursId", unique = true, nullable = false)
	public Integer getCalendarWorkingHoursId() {
		return this.calendarWorkingHoursId;
	}

	public void setCalendarWorkingHoursId(Integer calendarWorkingHoursId) {
		this.calendarWorkingHoursId = calendarWorkingHoursId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarId")
	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workingHoursId")
	public WorkingHours getWorkinghours() {
		return this.workinghours;
	}

	public void setWorkinghours(WorkingHours workinghours) {
		this.workinghours = workinghours;
	}

	@Column(name = "weekDayKey", nullable = false, length = 4)
	public String getWeekDayKey() {
		return this.weekDayKey;
	}

	public void setWeekDayKey(String weekDayKey) {
		this.weekDayKey = weekDayKey;
	}

}
