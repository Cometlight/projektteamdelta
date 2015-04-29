package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "eventtype", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = "eventTypeName"))
public class EventType implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(EventType.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _eventTypeId;
	private String _eventTypeName;
	private Integer _estimatedTime;
	private String _description;
	private Set<CalendarEvent> _calendarEvents = new HashSet<CalendarEvent>(0);	// TODO: -> delete?

	public EventType() { }

	public EventType(String eventTypeName) {
		_eventTypeName = eventTypeName;
	}

	public EventType(String eventTypeName, Integer estimatedTime,
			String description, Set<CalendarEvent> calendarevents) {
		_eventTypeName = eventTypeName;
		_estimatedTime = estimatedTime;
		_description = description;
		_calendarEvents = calendarevents;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "eventTypeId", unique = true, nullable = false)
	public Integer getEventTypeId() {
		return _eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		_eventTypeId = eventTypeId;
	}

	@Column(name = "eventTypeName", unique = true, nullable = false, length = 50)
	public String getEventTypeName() {
		return _eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		_eventTypeName = eventTypeName;
	}

	@Column(name = "estimatedTime")
	public Integer getEstimatedTime() {
		return _estimatedTime;
	}

	public void setEstimatedTime(Integer estimatedTime) {
		_estimatedTime = estimatedTime;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventtype")
	public Set<CalendarEvent> getCalendarEvents() {
		return _calendarEvents;
	}

	public void setCalendarEvents(Set<CalendarEvent> calendarevents) {
		_calendarEvents = calendarevents;
	}

}
