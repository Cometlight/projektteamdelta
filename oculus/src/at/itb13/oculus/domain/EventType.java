package at.itb13.oculus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.teamD.domain.interfaces.IEventType;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "eventtype", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = "eventTypeName"))
public class EventType implements java.io.Serializable, IEventType {
	private static final Logger _logger = LogManager.getLogger(EventType.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _eventTypeId;
	private String _eventTypeName;
	private Integer _estimatedTime;
	private String _description;

	public EventType() { }

	public EventType(String eventTypeName) {
		_eventTypeName = eventTypeName;
	}

	public EventType(String eventTypeName, Integer estimatedTime,
			String description) {
		_eventTypeName = eventTypeName;
		_estimatedTime = estimatedTime;
		_description = description;
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
	@Override
	public String getEventTypeName() {
		return _eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		_eventTypeName = eventTypeName;
	}

	@Column(name = "estimatedTime")
	@Override
	public Integer getEstimatedTime() {
		return _estimatedTime;
	}

	public void setEstimatedTime(Integer estimatedTime) {
		_estimatedTime = estimatedTime;
	}

	@Column(name = "description", length = 65535)
	@Override
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

}
