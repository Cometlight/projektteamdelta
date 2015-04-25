package at.itb13.oculustests.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.QueueEntry;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 25 Apr 2015
 */
public class Queue_UnitTests {

	@Test
	public void ConstructorWithoutParameter() {
		Queue q = new Queue();

		assertEquals(true, q != null);
	}

	public void ConstructorWithNullParameters() {
		Queue q = new Queue(null, null);

		assertEquals(true, q != null);
		assertEquals(true, q.getDoctor() == null);
		assertEquals(true, q.getOrthoptist() == null);
	}

	@Test
	public void ConstructorWithDoctorWithoutOrthoptist() {
		Doctor doctor = new Doctor();
		Queue q = new Queue(doctor, null);
		assertEquals(true, q.getDoctor() != null);
		assertEquals(true, q.getOrthoptist() == null);
	}

	@Test
	public void ConstructorWithoutDoctorWithOrthoptist() {
		Orthoptist orthoptist = new Orthoptist();
		Queue q = new Queue(null, orthoptist);
		assertEquals(true, q.getDoctor() == null);
		assertEquals(true, q.getOrthoptist() != null);
	}

	@Test(expected = NullPointerException.class)
	public void ConstructorWithQueueEntriesNull() {
		Queue q = new Queue(null, null, null);
		assertEquals(true, q.getDoctor() == null);
		assertEquals(true, q.getOrthoptist() == null);
		assertEquals(true, q.getQueueEntries() == null);

	}

	@Test
	public void pushQueueEntry_QueueEntry() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		QueueEntry queueEntry = new QueueEntry();
		q.pushQueueEntry(queueEntry);

		assertEquals(true, q.getQueueEntries().size() == 1);
	}

	@Test
	public void pushQueueEntry_Patient() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		Patient p = new Patient();
		q.pushQueueEntry(p, null);

		assertEquals(true, q.getQueueEntries().size() == 1);
	}

	@Test
	public void pushQueueEntry_Patient_ArrivalTime() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		Patient p = new Patient();
		q.pushQueueEntry(p, null, LocalDateTime.now());

		assertEquals(true, q.getQueueEntries().size() == 1);
	}

	@Test
	public void peek() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		QueueEntry queueEntry = new QueueEntry();
		q.pushQueueEntry(queueEntry);

		assertEquals(true, q.peek() != null);
	}

	@Test
	public void popQueueEntry() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		QueueEntry queueEntry = new QueueEntry();
		q.pushQueueEntry(queueEntry);

		assertEquals(true, q.popQueueEntry() != null);
	}

	@Test
	public void removeQueueEntry_QueueEntry_IsTrue() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();

		Queue q = new Queue(null, null, list);
		assertEquals(true, q.getQueueEntries() != null);
		QueueEntry queueEntry = new QueueEntry();
		q.pushQueueEntry(queueEntry);

		assertEquals(true, q.removeQueueEntry(queueEntry));
	}

	@Test
	public void removeQueueEntry_QueueEntry_IsFalse() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);
		Patient p = null;
		QueueEntry qe = null;
		assertEquals(false, q.removeQueueEntry(p));
		assertEquals(false, q.removeQueueEntry(qe));
	}

	@Test
	public void move_NullParameter() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);
		assertEquals(false, q.move(null, true));
	}

	@Test
	public void move_moveUp() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);

		QueueEntry queueEntry1 = new QueueEntry();
		queueEntry1.setQueueEntryId(1);
		q.pushQueueEntry(queueEntry1);

		QueueEntry queueEntry2 = new QueueEntry();
		queueEntry2.setQueueEntryId(2);
		q.pushQueueEntry(queueEntry2);

		assertEquals(true, q.move(2, true));
	}

	@Test
	public void move_moveDown() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);

		QueueEntry queueEntry1 = new QueueEntry();
		queueEntry1.setQueueEntryId(1);
		q.pushQueueEntry(queueEntry1);

		QueueEntry queueEntry2 = new QueueEntry();
		queueEntry2.setQueueEntryId(2);
		q.pushQueueEntry(queueEntry2);

		assertEquals(true, q.move(1, false));
	}

	@Test
	public void containsPatient_NULL_NONEXISTENTID() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);
		assertEquals(false, q.containsPatient(null));
		assertEquals(false, q.containsPatient(1337));
	}

	@Test
	public void containsQueueEntry() {
		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);

		QueueEntry queueEntry1 = new QueueEntry();
		queueEntry1.setQueueEntryId(1);
		q.pushQueueEntry(queueEntry1);

		QueueEntry queueEntry2 = new QueueEntry();
		queueEntry2.setQueueEntryId(2);
		q.pushQueueEntry(queueEntry2);

		assertEquals(true, q.contains(1));
		assertEquals(true, q.contains(2));
	}

	@Test
	public void getQueueEntryById() {

		List<QueueEntry> list = new LinkedList<QueueEntry>();
		Queue q = new Queue(null, null, list);

		QueueEntry queueEntry1 = new QueueEntry();
		queueEntry1.setQueueEntryId(1);
		q.pushQueueEntry(queueEntry1);

		QueueEntry queueEntry2 = new QueueEntry();
		queueEntry2.setQueueEntryId(2);
		q.pushQueueEntry(queueEntry2);

		assertEquals(true, q.getQueueEntryById(1) != null);
		assertEquals(true, q.getQueueEntryById(2) != null);
		assertEquals(true, q.getQueueEntryById(null) == null);
	}

	@Test(expected = NullPointerException.class)
	public void representsSameQueueByID() {
		Queue q = new Queue(null, null);
		assertEquals(false, q.representsSameQueueByID(null));
	}

	//
	 @Test
	 public void getQueueEntries() {
			List<QueueEntry> list = new LinkedList<QueueEntry>();
			Queue q = new Queue(null, null, list);

			QueueEntry queueEntry1 = new QueueEntry();
			queueEntry1.setQueueEntryId(1);
			q.pushQueueEntry(queueEntry1);

			QueueEntry queueEntry2 = new QueueEntry();
			queueEntry2.setQueueEntryId(2);
			q.pushQueueEntry(queueEntry2);
			
			assertEquals(true, q.getQueueEntries() != null);
	 }
	
	 @Test
	 public void setQueueEntries() {
			List<QueueEntry> list = new LinkedList<QueueEntry>();
			
			QueueEntry queueEntry1 = new QueueEntry();
			queueEntry1.setQueueEntryId(1);

			QueueEntry queueEntry2 = new QueueEntry();
			queueEntry2.setQueueEntryId(2);
			
			list.add(queueEntry1);
			list.add(queueEntry2);
			Queue q = new Queue(null, null);

			q.setQueueEntries(list);
			
			assertEquals(true, q.getQueueEntries() != null);
	 }
}
