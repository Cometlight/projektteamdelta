package at.itb13.oculus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
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

	public Integer getQueueId() {
		return this.queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Orthoptist getOrthoptist() {
		return this.orthoptist;
	}

	public void setOrthoptist(Orthoptist orthoptist) {
		this.orthoptist = orthoptist;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Queue getQueue() {
		return this.queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

}