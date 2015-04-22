package at.itb13.oculus.application.queue;

import java.util.List;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueEntryRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
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
	 * Inserts queueEntryRO to the last position in the Queue's QueueEntryList and updates the database accordingly.
	 * 
	 * @param queueEntryRO the QueueEntry to be inserted
	 * @return true if the queueEntry was successfully added to the Queue and saved in the database
	 * @see #pushQueueEntry(QueueEntryRO, CalendarEventRO)
	 */
	public boolean pushQueueEntry(PatientRO patientRO) {
		_queue.pushQueueEntry((Patient) patientRO);
		return QueueDao.getInstance().makePersistent(_queue);
	}
	
	/**
	 * Returns the QueueEntry that's at the front of the queue and removes it from the queue, as well as from the database.
	 * May update the calendarEventRo's state (isOpen) if it's not the general orthoptist queue. TODO: Or not? -> delete?
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
	
	/**
	 * 
	 * @param queueEntryRO the QueueEntryRO that should be moved. Must currently be located in the QueueControllers Queue!
	 * @param moveUp if true, the queueEntryRO moves 1 step up (forward, towards the start). If false, the queueEntryRO is moved 1 step "down", toward the end of the queue.
	 * @return true if the QueueEntry was successfully moved and this change was saved to the database.
	 */
	public boolean moveQueueEntry(QueueEntryRO queueEntryRO, boolean moveUp) {
		if(_queue.move(queueEntryRO.getQueueEntryId(), moveUp)) {
			return QueueDao.getInstance().makePersistent(_queue);
		}
		return false;
	}
	
	/**
	 * TODO
	 * 
	 * @param patientRO
	 * @return
	 */
	public boolean isPatientInQueue(PatientRO patientRO) {
		return _queue.containsPatient(patientRO.getPatientId());
	}
}
