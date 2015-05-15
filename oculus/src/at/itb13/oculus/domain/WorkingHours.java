package at.itb13.oculus.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.support.LocalTimeConverter;
import at.itb13.teamD.domain.interfaces.IWorkingHours;

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

	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(WorkingHours.class.getName());
	
	private Integer _workingHoursId;
	private LocalTime _morningFrom;
	private LocalTime _morningTo;
	private LocalTime _afternoonFrom;
	private LocalTime _afternoonTo;

	public WorkingHours() {
	}

	public WorkingHours(LocalTime morningFrom, LocalTime morningTo, LocalTime afternoonFrom,
			LocalTime afternoonTo, Set<CalendarWorkingHours> calendarworkinghourses) {
		_morningFrom = morningFrom;
		_morningTo = morningTo;
		_afternoonFrom = afternoonFrom;
		_afternoonTo = afternoonTo;
	}
	
	/**
	 * checks if the date(start, end) is in the WorkingHours.
	 * 
	 * @param start of the date.
	 * @param end of the date.
	 * @return
	 */
	@Override
	public boolean isDateInWorkingHours(LocalTime start, LocalTime end){
		if(getMorningFrom() != null && getMorningTo() != null){
			if((start.isAfter(getMorningFrom()) || start.equals(getMorningFrom()))
				&& (end.isBefore(getMorningTo()) || end.equals(getMorningTo()))){
				return true;
			} 	
		}
		if(getAfternoonFrom() != null && getAfternoonTo() != null){
			if((start.isAfter(getAfternoonFrom()) || start.equals(getAfternoonFrom()))
				&& (end.isBefore(getAfternoonTo()) || end.equals(getAfternoonTo()))){
				return true;
			} 	
		}
		return false;		
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
	public LocalTime getMorningFrom() {
		return _morningFrom;
	}

	public void setMorningFrom(LocalTime morningFrom) {
		_morningFrom = morningFrom;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "morningTo", length = 8)
	@Override
	public LocalTime getMorningTo() {
		return _morningTo;
	}

	public void setMorningTo(LocalTime morningTo) {
		_morningTo = morningTo;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "afternoonFrom", length = 8)
	@Override
	public LocalTime getAfternoonFrom() {
		return _afternoonFrom;
	}

	public void setAfternoonFrom(LocalTime afternoonFrom) {
		_afternoonFrom = afternoonFrom;
	}

	@Convert(converter = LocalTimeConverter.class)
	@Column(name = "afternoonTo", length = 8)
	@Override
	public LocalTime getAfternoonTo() {
		return _afternoonTo;
	}

	public void setAfternoonTo(LocalTime afternoonTo) {
		_afternoonTo = afternoonTo;
	}
}
