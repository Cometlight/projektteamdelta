package at.itb13.oculus.application.queue;

import java.time.LocalDateTime;
import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueEntryRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.oculus.technicalServices.dao.PatientDao;
import at.itb13.oculus.technicalServices.dao.QueueDao;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
public class QueueController {
	private Queue _queue;
	
	public QueueController(Queue queue) {	// TODO: sichtbarkeit einschränken, wenn möglich
		_queue = queue;
	}
	
	public QueueRO getQueue() {
		return _queue;
	}
	
	public List<? extends QueueEntryRO> getQueueEntries() {
		return _queue.getQueueEntries();
	}
	
	/**
	 * Created QueueEntry is not persisted! Can only be persisted by persisting its Queue.
	 * @param patientRO
	 * @param arrivalTime
	 * @return
	 */
	public QueueEntryRO createQueueEntry(PatientRO patientRO, LocalDateTime arrivalTime) {
		Patient patient = PatientDao.getInstance().findById(patientRO.getPatientId());
		
		QueueEntry queueEntry = new QueueEntry();
		queueEntry.setPatient(patient);
		queueEntry.setArrivalTime(arrivalTime);
		
		return queueEntry;
	}
	
	/**
	 * Inserts queueEntryRO to the last position in the Queue's QueueEntryList
	 * 
	 * @param queueEntryRO the QueueEntry to be inserted
	 * @return true if the queueEntry was successfully added to the Queue and saved in the database
	 * @see #pushQueueEntry(QueueEntryRO, CalendarEventRO)
	 */
	public boolean pushQueueEntry(QueueEntryRO queueEntryRO) {
		QueueEntry queueEntry = (QueueEntry) queueEntryRO;
		_queue.pushQueueEntry(queueEntry);
		return QueueDao.getInstance().makePersistent(_queue);
	}
	
	/**
	 * Inserts queueEntryRO to the last position in the Queue's QueueEntryList
	 * May update the calendarEventRo's state (isOpen) if it's not the general orthoptist queue.
	 * 
	 * @param queueEntryRO the QueueEntry to be inserted
	 * @param calendarEventRo the CalendarEventRO which state (isOpen) will be updated if necessary
	 * @return true if the queueEntry was successfully added to the Queue and saved in the database
	 * @see #pushQueueEntry(QueueEntryRO)
	 */
	public boolean pushQueueEntry(QueueEntryRO queueEntryRO, CalendarEventRO calendarEventRO) {
		if(_queue.getDoctor() != null || _queue.getOrthoptist() != null) {	// if(not in general orthoptist queue)
			ControllerFacade.getInstance().getCalendarController(calendarEventRO.getCalendar()).setCalendarEventState(calendarEventRO, false);
		}
		return pushQueueEntry(queueEntryRO);
	}
	
	/**
	 * Inserts a QueueEntry that is associated with patientRO to the last position in the Queue's QueueEntryList.
	 * @param patientRO the PatientRO to be inserted
	 * @return true if the new QueueEntry (with its patientRO) was successfully added to the Queue and saved in the database
	 */
	public boolean pushQueueEntry(PatientRO patientRO) {
		QueueEntry queueEntry = createQueueEntry(patientRO);
		return pushQueueEntry(queueEntry);
	}
	
	/**
	 * Inserts a QueueEntry that is associated with patientRO to the last position in the Queue's QueueEntryList.
	 * May update the calendarEventRo's state (isOpen) if it's not the general orthoptist queue.
	 * 
	 * @param patientRO the PatientRO to be inserted
	 * @param calendarEventRO the CalendarEventRO which state (isOpen) will be updated if necessary
	 * @return true if the new QueueEntry (with its patientRO) was successfully added to the Queue and saved in the database
	 */
	public boolean pushQueueEntry(PatientRO patientRO, CalendarEventRO calendarEventRO) {
		QueueEntry queueEntry = createQueueEntry(patientRO);
		return pushQueueEntry(queueEntry, calendarEventRO);
	}

	private QueueEntry createQueueEntry(PatientRO patientRO) {
		QueueEntry queueEntry = new QueueEntry();
		queueEntry.setPatient((Patient) patientRO);
		queueEntry.setArrivalTime(LocalDateTime.now());
		return queueEntry;
	}
	
	/**
	 * Returns the QueueEntry that's at the front of the queue and removes it from the queue, as well as from the database.
	 * To only read but not remove the QueueEntry, use {@link #peekQueueEntry()} instead.
	 * 
	 * @return null if the Queue's list of QueueEntries is empty.
	 */
	public QueueEntryRO popQueueEntry() {
		QueueEntryRO queueEntryRO = _queue.popQueueEntry();
		if(queueEntryRO != null) {
			QueueDao.getInstance().makePersistent(_queue);
		}
		return queueEntryRO;
	}
	
	/**
	 * Returns the QueueEntry that's at the front of the queue.
	 * 
	 * @return null if the Queue's list of QueueEntries is empty.
	 * @see #popQueueEntry(CalendarEventRO)
	 */
	public QueueEntryRO peekQueueEntry() {
		return _queue.peek();
	}
}
