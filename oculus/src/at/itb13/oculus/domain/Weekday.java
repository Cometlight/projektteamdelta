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
public class Weekday implements java.io.Serializable {

	private String weekDayKey;
	private String name;
	private Set<Workinghours> workinghourses = new HashSet<Workinghours>(0);

	public Weekday() {
	}

	public Weekday(String weekDayKey, String name) {
		this.weekDayKey = weekDayKey;
		this.name = name;
	}

	public Weekday(String weekDayKey, String name,
			Set<Workinghours> workinghourses) {
		this.weekDayKey = weekDayKey;
		this.name = name;
		this.workinghourses = workinghourses;
	}

	public String getWeekDayKey() {
		return this.weekDayKey;
	}

	public void setWeekDayKey(String weekDayKey) {
		this.weekDayKey = weekDayKey;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Workinghours> getWorkinghourses() {
		return this.workinghourses;
	}

	public void setWorkinghourses(Set<Workinghours> workinghourses) {
		this.workinghourses = workinghourses;
	}

}
