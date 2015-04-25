package at.itb13.oculus.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "workinghours", catalog = "oculus_d", uniqueConstraints = @UniqueConstraint(columnNames = {
		"morningFrom", "morningTo", "afternoonFrom", "afternoonTo" }))
public class WorkingHours implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(WorkingHours.class.getName());
	
	private Integer _workingHoursId;
	private Date _morningFrom;
	private Date _morningTo;
	private Date _afternoonFrom;
	private Date _afternoonTo;

	public WorkingHours() {
	}

	public WorkingHours(Date morningFrom, Date morningTo, Date afternoonFrom,
			Date afternoonTo, Set<CalendarWorkingHours> calendarworkinghourses) {
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

	@Temporal(TemporalType.TIME)
	@Column(name = "morningFrom", length = 8)
	public Date getMorningFrom() {
		return _morningFrom;
	}

	public void setMorningFrom(Date morningFrom) {
		_morningFrom = morningFrom;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "morningTo", length = 8)
	public Date getMorningTo() {
		return _morningTo;
	}

	public void setMorningTo(Date morningTo) {
		_morningTo = morningTo;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "afternoonFrom", length = 8)
	public Date getAfternoonFrom() {
		return _afternoonFrom;
	}

	public void setAfternoonFrom(Date afternoonFrom) {
		_afternoonFrom = afternoonFrom;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "afternoonTo", length = 8)
	public Date getAfternoonTo() {
		return _afternoonTo;
	}

	public void setAfternoonTo(Date afternoonTo) {
		_afternoonTo = afternoonTo;
	}
}
