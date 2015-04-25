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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "doctor", catalog = "oculus_d")
public class Doctor implements java.io.Serializable, DoctorRO {
	private static final Logger _logger = LogManager.getLogger(Doctor.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _doctorId;
	private Calendar _calendar;
	private Doctor _doctorSubstitute;
	private User _user;
	private Set<Diagnosis> _diagnoses = new HashSet<Diagnosis>(0);
	private Set<Patient> _patients = new HashSet<Patient>(0);

	public Doctor() {
	}

	public Doctor(Calendar calendar) {
		_calendar = calendar;
	}

	public Doctor(Calendar calendar, Doctor doctorSubstitute, User user, Set<Diagnosis> diagnosises,
			Set<Patient> patients) {
		_calendar = calendar;
		_doctorSubstitute = doctorSubstitute;
		_user = user;
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
		_doctorId = doctorId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarId", nullable = false)
	public Calendar getCalendar() {
		return _calendar;
	}

	public void setCalendar(Calendar calendar) {
		_calendar = calendar;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorIdSubstitute")
	public Doctor getDoctorSubstitute() {
		return _doctorSubstitute;
	}

	public void setDoctorSubstitute(Doctor doctor) {
		_doctorSubstitute = doctor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Diagnosis> getDiagnoses() {
		return _diagnoses;
	}

	public void setDiagnoses(Set<Diagnosis> diagnoses) {
		_diagnoses = diagnoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Patient> getPatients() {
		return _patients;
	}

	public void setPatients(Set<Patient> patients) {
		_patients = patients;
	}
}
