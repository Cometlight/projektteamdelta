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
public class Doctor implements java.io.Serializable {

	private Integer doctorId;
	private Calendar calendar;
	private Doctor doctor;
	private User user;
	private Set<Doctor> doctors = new HashSet<Doctor>(0);
	private Set<Queue> queues = new HashSet<Queue>(0);
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>(0);
	private Set<Patient> patients = new HashSet<Patient>(0);

	public Doctor() {
	}

	public Doctor(Calendar calendar) {
		this.calendar = calendar;
	}

	public Doctor(Calendar calendar, Doctor doctor, User user,
			Set<Doctor> doctors, Set<Queue> queues, Set<Diagnosis> diagnosises,
			Set<Patient> patients) {
		this.calendar = calendar;
		this.doctor = doctor;
		this.user = user;
		this.doctors = doctors;
		this.queues = queues;
		this.diagnosises = diagnosises;
		this.patients = patients;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Doctor> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

	public Set<Diagnosis> getDiagnosises() {
		return this.diagnosises;
	}

	public void setDiagnosises(Set<Diagnosis> diagnosises) {
		this.diagnosises = diagnosises;
	}

	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

}
