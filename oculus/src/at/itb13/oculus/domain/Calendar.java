package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Calendar implements java.io.Serializable {

	private Integer calendarId;
	private String title;
	private Set<Doctor> doctors = new HashSet<Doctor>(0);
	private Set<Orthoptist> orthoptists = new HashSet<Orthoptist>(0);
	private Set<Calendarevent> calendarevents = new HashSet<Calendarevent>(0);
	private Set<Workinghours> workinghourses = new HashSet<Workinghours>(0);

	public Calendar() {
	}

	public Calendar(String title, Set<Doctor> doctors,
			Set<Orthoptist> orthoptists, Set<Calendarevent> calendarevents,
			Set<Workinghours> workinghourses) {
		this.title = title;
		this.doctors = doctors;
		this.orthoptists = orthoptists;
		this.calendarevents = calendarevents;
		this.workinghourses = workinghourses;
	}

	public Integer getCalendarId() {
		return this.calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Doctor> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Orthoptist> getOrthoptists() {
		return this.orthoptists;
	}

	public void setOrthoptists(Set<Orthoptist> orthoptists) {
		this.orthoptists = orthoptists;
	}

	public Set<Calendarevent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<Calendarevent> calendarevents) {
		this.calendarevents = calendarevents;
	}

	public Set<Workinghours> getWorkinghourses() {
		return this.workinghourses;
	}

	public void setWorkinghourses(Set<Workinghours> workinghourses) {
		this.workinghourses = workinghourses;
	}

}