// default package
// Generated 14.04.2015 20:32:57 by Hibernate Tools 4.3.1

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Queue generated by hbm2java
 */
@Entity
@Table(name = "queue", catalog = "oculusdb", uniqueConstraints = @UniqueConstraint(columnNames = "patientId"))
public class Queue implements java.io.Serializable {

	private Integer queueId;
	private Doctor doctor;
	private Orthoptist orthoptist;
	private Patient patient;
	private Queue queue;
	private Date arrivalTime;
	private Set<Queue> queues = new HashSet<Queue>(0);

	public Queue() {
	}

	public Queue(Patient patient, Date arrivalTime) {
		this.patient = patient;
		this.arrivalTime = arrivalTime;
	}

	public Queue(Doctor doctor, Orthoptist orthoptist, Patient patient,
			Queue queue, Date arrivalTime, Set<Queue> queues) {
		this.doctor = doctor;
		this.orthoptist = orthoptist;
		this.patient = patient;
		this.queue = queue;
		this.arrivalTime = arrivalTime;
		this.queues = queues;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "queueIdParent")
	public Queue getQueue() {
		return this.queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrivalTime", nullable = false, length = 19)
	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queue")
	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

}