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
public class Eventtype implements java.io.Serializable {

	private Integer eventTypeId;
	private String eventTypeName;
	private Integer estimatedTime;
	private String description;
	private Set<Calendarevent> calendarevents = new HashSet<Calendarevent>(0);

	public Eventtype() {
	}

	public Eventtype(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public Eventtype(String eventTypeName, Integer estimatedTime,
			String description, Set<Calendarevent> calendarevents) {
		this.eventTypeName = eventTypeName;
		this.estimatedTime = estimatedTime;
		this.description = description;
		this.calendarevents = calendarevents;
	}

	public Integer getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeName() {
		return this.eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public Integer getEstimatedTime() {
		return this.estimatedTime;
	}

	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Calendarevent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<Calendarevent> calendarevents) {
		this.calendarevents = calendarevents;
	}

}
