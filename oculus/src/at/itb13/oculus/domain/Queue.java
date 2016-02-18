package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;

/**
 * 
 * TODO: Insert description here.
 * If neither the doctor nor the orthoptist is set, it's the general orthoptist queue.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class Queue implements QueueRO {

	private static final Logger _logger = LogManager.getLogger(Queue.class.getName());
	
	private Doctor _doctor;
	private Orthoptist _orthoptist;
	private List<QueueEntry> _queueEntries;	// sorted, newest at front

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
	
	public void pushQueueEntry(QueueEntry queueEntry) {
		_queueEntries.add(queueEntry);
	}
	
	public void pushQueueEntry(Patient patient, CalendarEvent calendarEvent) {
		pushQueueEntry(patient, calendarEvent, LocalDateTime.now());
	}
	
	/**
	 * 
	 * @param patient
	 * @param calendarEvent may be null, if only the patient without reference to a calendarEvent should be inserted
	 * @param arrivalTime
	 */
	public void pushQueueEntry(Patient patient, CalendarEvent calendarEvent, LocalDateTime arrivalTime) {
		QueueEntry entry = new QueueEntry();
		entry.setPatient(patient);
		entry.setCalendarEvent(calendarEvent);
		entry.setArrivalTime(arrivalTime);
		pushQueueEntry(entry);
	}
	
	/**
	 * 
	 * @return null if empty QueueEntryList
	 */
	public QueueEntry peek() {
		QueueEntry queueEntry = null;
		if(!_queueEntries.isEmpty()) {
			Iterator<QueueEntry> it = _queueEntries.iterator();
			queueEntry = it.next();
		}
		return queueEntry;
	}
	
	/**
	 * Removes all calendarEvents from the queue.
	 */
	public void clear() {
		_queueEntries.clear();
	}
	
	/**
	 * 
	 * @return true if the queue is empty, i.e. does not contain any calendarevents.
	 */
	public boolean isEmpty() {
		return _queueEntries.isEmpty();
	}
	
	/**
	 * TODO
	 * @return null if empty QueueEntryList
	 */
	public QueueEntry popQueueEntry() {
		QueueEntry queueEntry = null;
		if(!_queueEntries.isEmpty()) {
			Iterator<QueueEntry> it = _queueEntries.iterator();
			queueEntry = it.next();
			it.remove();
		}
		return queueEntry;
	}
	
	/**
	 * @param queueEntry
	 * @return
	 */
	public boolean removeQueueEntry(QueueEntry queueEntry) {
		if(!_queueEntries.remove(queueEntry)) {
			_logger.warn("queueEntry not in list.");	
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param patient
	 * @return true if removed; false if patient wasn't in the queue
	 */
	public boolean removeQueueEntry(Patient patient) {
		return _queueEntries.removeIf(entry -> entry.getPatient().getPatientId().equals(patient.getPatientId()));
	}
	
	/**
	 * Moves a QueueEntry in the Queue's list up (forward) or down (to the back).
	 * 
	 * @param queueEntryID The ID of the QueueEntry to be moved
	 * @param moveUp if false, moves down instead
	 * @return true if successfully moved
	 */
	public boolean move(Integer queueEntryID, boolean moveUp) {
		QueueEntry queueEntryFromList = null;
		ListIterator<QueueEntry> it = _queueEntries.listIterator();
		while(it.hasNext()) {
			if(it.next().getQueueEntryId().equals(queueEntryID)) {
				queueEntryFromList = it.previous();
				break;
			}
		}
		
		if(queueEntryFromList != null) {
			// "it" points now to queueEntry
			if(moveUp) {
				if(it.hasPrevious()) {	// not possible to move up otherwise
					it.remove();
					it.previous();
					it.add(queueEntryFromList);
					return true;
				}
			} else {
				it.next();
				if(it.hasNext()) {
					it.previous();
					it.remove();
					it.next();
					it.add(queueEntryFromList);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * TODO
	 * 
	 * @param patient
	 * @return
	 */
	public boolean containsPatient(Integer patientID) {
		for(QueueEntry queueEntry : _queueEntries) {
			if(queueEntry.getPatient().getPatientId().equals(patientID)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TODO
	 * @param queueEntryID
	 * @return
	 */
	public boolean contains(Integer queueEntryID) {
		for(QueueEntry qE : _queueEntries) {
			if(qE.getQueueEntryId().equals(queueEntryID)) {
				return true;
			}
		}
		return false;
	}
	
	public QueueEntry getQueueEntryById(Integer queueEntryID) {
		for(QueueEntry qE : _queueEntries) {
			if(qE.getQueueEntryId().equals(queueEntryID)) {
				return qE;
			}
		}
		return null;
	}
	
	/**
	 * Not overriding equals() because this method only checks concerning the IDs.
	 * @param queue
	 * @return
	 */
	public boolean representsSameQueueByID(Queue queue) {
		return (
				   (_doctor != null && queue.getDoctor() != null && _doctor.getDoctorId().equals(queue.getDoctor().getDoctorId()))
				|| (_orthoptist != null && queue.getOrthoptist() != null && _orthoptist.getOrthoptistId().equals(queue.getOrthoptist().getOrthoptistId()))
				|| (_doctor == null && _orthoptist == null && queue.getDoctor() == null && queue.getOrthoptist() == null)
			);
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
