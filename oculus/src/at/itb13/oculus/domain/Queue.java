package at.itb13.oculus.domain;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
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
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class Queue implements java.io.Serializable {

	private static final Logger _logger = LogManager.getLogger(Queue.class.getName());
	
	private Doctor _doctor;
	private Orthoptist _orthoptist;
	private List<QueueEntry> _queueEntries;	// sorted

	public Queue() {
	}

	public Queue(Doctor doctor, Orthoptist orthoptist) {
		_doctor = doctor;
		_orthoptist = orthoptist;
	}

	public Queue(Doctor doctor, Orthoptist orthoptist, List<QueueEntry> queueEntries) {
		_doctor = doctor;
		_orthoptist = orthoptist;
		_queueEntries = queueEntries;
	}
	
	public void addQueueEntry(QueueEntry queueEntry) {
		_queueEntries.add(queueEntry);
	}
	
	public void removeQueueEntry(QueueEntry queueEntry) {
		_queueEntries.remove(_queueEntries);	// TODO: log if not contained. (maybe warn or so)
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return _doctor;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	/**
	 * @return the orthoptist
	 */
	public Orthoptist getOrthoptist() {
		return _orthoptist;
	}

	/**
	 * @param orthoptist the orthoptist to set
	 */
	public void setOrthoptist(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}

	/**
	 * @return the queueEntries
	 */
	public List<QueueEntry> getQueueEntries() {
		return Collections.unmodifiableList(_queueEntries);
	}

	/**
	 * @param queueEntries the queueEntries to set
	 */
	public void setQueueEntries(List<QueueEntry> queueEntries) {
		_queueEntries = queueEntries;
	}
}
