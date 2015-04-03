package at.itb13.oculus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Workinghours implements java.io.Serializable {

	private Integer workingHoursId;
	private Weekday weekday;
	private Date morningFrom;
	private Date morningTo;
	private Date afternoonFrom;
	private Date afternoonTo;
	private Set<Calendar> calendars = new HashSet<Calendar>(0);

	public Workinghours() {
	}

	public Workinghours(Weekday weekday) {
		this.weekday = weekday;
	}

	public Workinghours(Weekday weekday, Date morningFrom, Date morningTo,
			Date afternoonFrom, Date afternoonTo, Set<Calendar> calendars) {
		this.weekday = weekday;
		this.morningFrom = morningFrom;
		this.morningTo = morningTo;
		this.afternoonFrom = afternoonFrom;
		this.afternoonTo = afternoonTo;
		this.calendars = calendars;
	}

	public Integer getWorkingHoursId() {
		return this.workingHoursId;
	}

	public void setWorkingHoursId(Integer workingHoursId) {
		this.workingHoursId = workingHoursId;
	}

	public Weekday getWeekday() {
		return this.weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public Date getMorningFrom() {
		return this.morningFrom;
	}

	public void setMorningFrom(Date morningFrom) {
		this.morningFrom = morningFrom;
	}

	public Date getMorningTo() {
		return this.morningTo;
	}

	public void setMorningTo(Date morningTo) {
		this.morningTo = morningTo;
	}

	public Date getAfternoonFrom() {
		return this.afternoonFrom;
	}

	public void setAfternoonFrom(Date afternoonFrom) {
		this.afternoonFrom = afternoonFrom;
	}

	public Date getAfternoonTo() {
		return this.afternoonTo;
	}

	public void setAfternoonTo(Date afternoonTo) {
		this.afternoonTo = afternoonTo;
	}

	public Set<Calendar> getCalendars() {
		return this.calendars;
	}

	public void setCalendars(Set<Calendar> calendars) {
		this.calendars = calendars;
	}

}
