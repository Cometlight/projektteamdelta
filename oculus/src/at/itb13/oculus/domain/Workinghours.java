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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "workinghours", catalog = "oculusdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"weekDayKey", "morningFrom", "morningTo", "afternoonFrom",
		"afternoonTo" }))
/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Workinghours implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer workingHoursId;
	private Weekday weekday;
	private Date morningFrom;
	private Date morningTo;
	private Date afternoonFrom;
	private Date afternoonTo;
	private Set<Calendar> calendars = new HashSet<Calendar>(0);

	public Workinghours() {
	}

	public Workinghours(Weekday weekday) {
		this.weekday = weekday;
	}

	public Workinghours(Weekday weekday, Date morningFrom, Date morningTo,
			Date afternoonFrom, Date afternoonTo, Set<Calendar> calendars) {
		this.weekday = weekday;
		this.morningFrom = morningFrom;
		this.morningTo = morningTo;
		this.afternoonFrom = afternoonFrom;
		this.afternoonTo = afternoonTo;
		this.calendars = calendars;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "workingHoursId", unique = true, nullable = false)
	public Integer getWorkingHoursId() {
		return this.workingHoursId;
	}

	public void setWorkingHoursId(Integer workingHoursId) {
		this.workingHoursId = workingHoursId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weekDayKey", nullable = false)
	public Weekday getWeekday() {
		return this.weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "morningFrom", length = 8)
	public Date getMorningFrom() {
		return this.morningFrom;
	}

	public void setMorningFrom(Date morningFrom) {
		this.morningFrom = morningFrom;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "morningTo", length = 8)
	public Date getMorningTo() {
		return this.morningTo;
	}

	public void setMorningTo(Date morningTo) {
		this.morningTo = morningTo;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "afternoonFrom", length = 8)
	public Date getAfternoonFrom() {
		return this.afternoonFrom;
	}

	public void setAfternoonFrom(Date afternoonFrom) {
		this.afternoonFrom = afternoonFrom;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "afternoonTo", length = 8)
	public Date getAfternoonTo() {
		return this.afternoonTo;
	}

	public void setAfternoonTo(Date afternoonTo) {
		this.afternoonTo = afternoonTo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "calendarworkinghours", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "workingHoursId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "calendarId", nullable = false, updatable = false) })
	public Set<Calendar> getCalendars() {
		return this.calendars;
	}

	public void setCalendars(Set<Calendar> calendars) {
		this.calendars = calendars;
	}

}
