package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;

/**
 * Doctor generated by hbm2java
 */
@Entity
@Table(name = "doctor", catalog = "oculusdb")
public class Doctor implements java.io.Serializable, DoctorRO {

	private static final Logger _logger = LogManager.getLogger(Doctor.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Integer _doctorId;
	private Calendar _calendar;
	private Doctor _doctorSubstitute;
	private User _user;
	private Queue _queue;
	private Set<Diagnosis> _diagnoses = new HashSet<Diagnosis>(0);
	private Set<Patient> _patients = new HashSet<Patient>(0);

	public Doctor() {
	}

	public Doctor(Calendar calendar) {
		_calendar = calendar;
	}

	public Doctor(Calendar calendar, Doctor doctorSubstitute, User user,
			Queue queue, Set<Diagnosis> diagnosises,
			Set<Patient> patients) {
		_calendar = calendar;
		_doctorSubstitute = doctorSubstitute;
		_user = user;
		_queue = queue;
		_diagnoses = diagnosises;
		_patients = patients;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "doctorId", unique = true, nullable = false)
	public Integer getDoctorId() {
		return _doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this._doctorId = doctorId;
	}

	@OneToOne(fetch = FetchType.LAZY)	// TODO: check if works
	@JoinColumn(name = "calendarId", nullable = false)
	public Calendar getCalendar() {
		return this._calendar;
	}

	public void setCalendar(Calendar calendar) {
		this._calendar = calendar;
	}

	@OneToOne(fetch = FetchType.LAZY)	// TODO: Check if works
	@JoinColumn(name = "doctorIdSubstitute")
	public Doctor getDoctorSubstitute() {
		return this._doctorSubstitute;
	}

	public void setDoctorSubstitute(Doctor doctor) {
		this._doctorSubstitute = doctor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this._user;
	}

	public void setUser(User user) {
		this._user = user;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Diagnosis> getDiagnoses() {
		return this._diagnoses;
	}

	public void setDiagnoses(Set<Diagnosis> diagnoses) {
		this._diagnoses = diagnoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Patient> getPatients() {
		return this._patients;
	}

	public void setPatients(Set<Patient> patients) {
		this._patients = patients;
	}

}
