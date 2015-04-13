package at.itb13.oculus.technicalServices.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
@Entity
@Table(name = "queue", catalog = "oculusdb", uniqueConstraints = @UniqueConstraint(columnNames = "patientId"))
public class QueueEntity implements java.io.Serializable {

	private Integer queueId;
	private Doctor doctor;
	private Orthoptist orthoptist;
	private Patient patient;
	private Date arrivalTime;
	private Integer queueIdParent;

	public QueueEntity() { }

	public QueueEntity(Patient patient, Date arrivalTime) {
		this.patient = patient;
		this.arrivalTime = arrivalTime;
	}

	public QueueEntity(Doctor doctor, Orthoptist orthoptist, Patient patient,
			Date arrivalTime, Integer queueIdParent) {
		this.doctor = doctor;
		this.orthoptist = orthoptist;
		this.patient = patient;
		this.arrivalTime = arrivalTime;
		this.queueIdParent = queueIdParent;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "queueId", unique = true, nullable = false)
	public Integer getQueueId() {
		return this.queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orthoptistId")
	public Orthoptist getOrthoptist() {
		return this.orthoptist;
	}

	public void setOrthoptist(Orthoptist orthoptist) {
		this.orthoptist = orthoptist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId", unique = true, nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrivalTime", nullable = false, length = 19)
	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Column(name = "queueIdParent")
	public Integer getQueueIdParent() {
		return queueIdParent;
	}

	public void setQueueIdParent(Integer queueIdParent) {
		this.queueIdParent = queueIdParent;
	}
}
