// default package
// Generated 14.04.2015 20:32:57 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Calendarworkinghours generated by hbm2java
 */
@Entity
@Table(name = "calendarworkinghours", catalog = "oculusdb")
public class Calendarworkinghours implements java.io.Serializable {

	private Integer calendarWorkingHoursId;
	private Calendar calendar;
	private Workinghours workinghours;
	private String weekDayKey;

	public Calendarworkinghours() {
	}

	public Calendarworkinghours(String weekDayKey) {
		this.weekDayKey = weekDayKey;
	}

	public Calendarworkinghours(Calendar calendar, Workinghours workinghours,
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
	public Workinghours getWorkinghours() {
		return this.workinghours;
	}

	public void setWorkinghours(Workinghours workinghours) {
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
