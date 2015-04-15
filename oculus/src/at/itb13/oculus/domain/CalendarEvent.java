package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.util.LocalDatePersistenceConverter;
import at.itb13.oculus.technicalServices.util.LocalDateTimePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
@Entity
@Table(name = "calendarevent", catalog = "oculusdb")
public class CalendarEvent implements java.io.Serializable {

	private static final Logger _logger = LogManager.getLogger(CalendarEvent.class.getName());
	private static final long serialVersionUID = 1L;
	private Integer _calendarEventId;
	private Calendar _calendar;
	private EventType _eventtype;
	private Patient _patient;
	private LocalDateTime _eventStart;
	private LocalDateTime _eventEnd;
	private String _description;
	private String _patientName;
	private boolean _isOpen;
	
	private boolean _isFullyLoaded;

	public CalendarEvent() {
	}

	public CalendarEvent(Calendar calendar, EventType eventtype,
			LocalDateTime eventStart, LocalDateTime eventEnd, boolean isOpen) {
		_calendar = calendar;
		_eventtype = eventtype;
		_eventStart = eventStart;
		_eventEnd = eventEnd;
		_isOpen = isOpen;
	}

	public CalendarEvent(Calendar calendar, EventType eventtype,
			Patient patient, LocalDateTime eventStart, LocalDateTime eventEnd,
			String description, String patientName, boolean isOpen) {
		_calendar = calendar;
		_eventtype = eventtype;
		_patient = patient;
		_eventStart = eventStart;
		_eventEnd = eventEnd;
		_description = description;
		_patientName = patientName;
		_isOpen = isOpen;
	}
	
	/**
	 * Checks if the CalendarEvent is in a timespan.
	 * 
	 * @param startDate the strat Date of the timespan.
	 * @param endDate the end Date of the timespan.
	 * @return true if the CalendarEvent is in the timespan and false if not.
	 */
	protected boolean isInTimespan(LocalDateTime startDate, LocalDateTime endDate){
		if((startDate.compareTo(getEventStart()) <= 0) && endDate.compareTo(getEventEnd())>= 0){	// FIXME Check if it still works with LocalDateTime instead of Date
			return true;
		} else{
			return false;
		}
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calendarEventId", unique = true, nullable = false)
	public Integer getCalendarEventId() {
		return _calendarEventId;
	}

	public void setCalendarEventId(Integer calendarEventId) {
		_calendarEventId = calendarEventId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarId", nullable = false)
	public Calendar getCalendar() {		// TODO: Muss ein calendarEvent wirklich wissen, zu welchem calendar es gehört? Oder reicht es nicht auch, wenn einfach der Calendar seine CalendarEvents kennt?
		return _calendar;
	}

	public void setCalendar(Calendar calendar) {
		_calendar = calendar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventTypeId", nullable = false)
	public EventType getEventtype() {
//		if(!_isFullyLoaded) {	TODO: DELETE
//			Reloader.getInstance().reload(CalendarEvent.class, this);
//		}
		return _eventtype;
	}

	public void setEventtype(EventType eventtype) {
		_eventtype = eventtype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
//		if(!_isFullyLoaded) {	TODO: DELETE
//			Reloader.getInstance().reload(CalendarEvent.class, this);
//		}
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "eventStart", nullable = false, length = 19)
	public LocalDateTime getEventStart() {
		return _eventStart;
	}

	public void setEventStart(LocalDateTime eventStart) {
		_eventStart = eventStart;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "eventEnd", nullable = false, length = 19)
	public LocalDateTime getEventEnd() {
		return _eventEnd;
	}

	public void setEventEnd(LocalDateTime eventEnd) {
		_eventEnd = eventEnd;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@Column(name = "patientName", length = 100)
	public String getPatientName() {
		return _patientName;
	}

	public void setPatientName(String patientName) {
		_patientName = patientName;
	}

	@Column(name = "isOpen", nullable = false)
	public boolean isIsOpen() {
		return _isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		_isOpen = isOpen;
	}

}
