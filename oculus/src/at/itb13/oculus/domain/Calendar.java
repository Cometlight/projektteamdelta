package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Calendar generated by hbm2java
 */
@Entity
@Table(name = "calendar", catalog = "oculusdb")
public class Calendar implements java.io.Serializable {

	private Integer calendarId;
	private String title;
	private Set<Doctor> doctors = new HashSet<Doctor>(0);
	private Set<Orthoptist> orthoptists = new HashSet<Orthoptist>(0);
	private Set<CalendarEvent> calendarevents = new HashSet<CalendarEvent>(0);
	private Set<Workinghours> workinghourses = new HashSet<Workinghours>(0);

	public Calendar() {
	}

	public Calendar(String title, Set<Doctor> doctors,
			Set<Orthoptist> orthoptists, Set<CalendarEvent> calendarevents,
			Set<Workinghours> workinghourses) {
		this.title = title;
		this.doctors = doctors;
		this.orthoptists = orthoptists;
		this.calendarevents = calendarevents;
		this.workinghourses = workinghourses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calendarId", unique = true, nullable = false)
	public Integer getCalendarId() {
		return this.calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<Doctor> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<Orthoptist> getOrthoptists() {
		return this.orthoptists;
	}

	public void setOrthoptists(Set<Orthoptist> orthoptists) {
		this.orthoptists = orthoptists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
	public Set<CalendarEvent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<CalendarEvent> calendarevents) {
		this.calendarevents = calendarevents;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "calendars")
	public Set<Workinghours> getWorkinghourses() {
		return this.workinghourses;
	}

	public void setWorkinghourses(Set<Workinghours> workinghourses) {
		this.workinghourses = workinghourses;
	}

	//TODO getCalendarEvents 
	
	/**
	 * Returns calendarevents in timespan
	 * @return
	 */
//	public Set<CalendarEvent> getCalendarevents(Calendar cal, anfang ende) {
//		return this.calendarevents;
//	}
}
