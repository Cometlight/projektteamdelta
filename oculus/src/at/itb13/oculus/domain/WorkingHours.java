package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.interfaces.IWorkingHours;
import at.itb13.oculus.domain.support.LocalTimeConverter;

/**
 * 
 * WorkingHours are assigned to a doctor or an orthoptist and are the hours of one weekday, the doctor or the orthoptist is 
 * usually available at the surgery and accept appointments
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "workinghours", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = {
		"morningFrom", "morningTo", "afternoonFrom", "afternoonTo" }))
public class WorkingHours implements java.io.Serializable, IWorkingHours {
	private static final long serialVersionUID = 1L;

	private static final Logger _logger = LogManager.getLogger(WorkingHours.class.getName());
	
	private Integer _workingHoursId;
	private LocalDateTime _morningFrom;
	private LocalDateTime _morningTo;
	private LocalDateTime _afternoonFrom;
	private LocalDateTime _afternoonTo;

	public WorkingHours() {
	}

	public WorkingHours(LocalDateTime morningFrom, LocalDateTime morningTo, LocalDateTime afternoonFrom,
			LocalDateTime afternoonTo, Set<CalendarWorkingHours> calendarworkinghourses) {
		_morningFrom = morningFrom;
		_morningTo = morningTo;
		_afternoonFrom = afternoonFrom;
		_afternoonTo = afternoonTo;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "workingHoursId", unique = true, nullable = false)
	public Integer getWorkingHoursId() {
		return _workingHoursId;
	}

	public void setWorkingHoursId(Integer workingHoursId) {
		_workingHoursId = workingHoursId;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "morningFrom", length = 8)
	@Override
	public LocalDateTime getMorningFrom() {
		return _morningFrom;
	}

	public void setMorningFrom(LocalDateTime morningFrom) {
		_morningFrom = morningFrom;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "morningTo", length = 8)
	@Override
	public LocalDateTime getMorningTo() {
		return _morningTo;
	}

	public void setMorningTo(LocalDateTime morningTo) {
		_morningTo = morningTo;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "afternoonFrom", length = 8)
	@Override
	public LocalDateTime getAfternoonFrom() {
		return _afternoonFrom;
	}

	public void setAfternoonFrom(LocalDateTime afternoonFrom) {
		_afternoonFrom = afternoonFrom;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "afternoonTo", length = 8)
	@Override
	public LocalDateTime getAfternoonTo() {
		return _afternoonTo;
	}

	public void setAfternoonTo(LocalDateTime afternoonTo) {
		_afternoonTo = afternoonTo;
	}
}
