package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.technicalServices.dao.QueueDao;

/**
 * Unittests for the class QueueDao
 * 
 * @author Andrew Sparr
 * @date 25 Apr 2015
 */
public class QueueDao_UnitTests {

	@Test
	public void getInstance() {
		QueueDao q = QueueDao.getInstance();

		assertEquals(true, (q != null));
	}

	@Test
	public void findById_NULL() {
		QueueDao q = QueueDao.getInstance();

		// Search for general orthoptist queue
		assertEquals(true, (q.findById(null, null) != null));

		// Search for nonexisting doctor queue
		assertEquals(true, (q.findById(7777777, null) == null));

		// Search for nonexisting orthoptist queue
		assertEquals(true, (q.findById(null, 7777777) == null));
	}

	@Test
	public void findById_DOCTOR() {
		QueueDao queueDao = QueueDao.getInstance();

		// Search for doctor with doctor ID 1
		Queue queue = queueDao.findById(1, null);
		assertEquals(true, queue != null);
		// Doctor must not be null
		assertEquals(true, queue.getDoctor() != null);
		// Orthoptist must be null
		assertEquals(true, queue.getOrthoptist() == null);

	}

	@Test
	public void findById_ORTHOPTIST() {
		QueueDao queueDao = QueueDao.getInstance();

		// Search for orthoptist with orthoptist ID 1
		Queue queue = queueDao.findById(null, 1);
		assertEquals(true, queue != null);
		// Orthoptist must not be null
		assertEquals(true, queue.getOrthoptist() != null);
		// Doctor must be null
		// Doctor must not be null
		assertEquals(true, queue.getDoctor() == null);
	}

	@Test
	public void list() {
		QueueDao q = QueueDao.getInstance();

		assertEquals(true, (q.list() != null));
	}

	@Test
	public void findAll() {
		QueueDao q = QueueDao.getInstance();

		assertEquals(true, (q.findAll() != null));
	}

	@Test(expected = NullPointerException.class)
	public void makePersistent() {
		QueueDao q = QueueDao.getInstance();

		q.makePersistent(null, null);

	}

	@Test(expected = NullPointerException.class)
	public void makePersistentList() {
		QueueDao q = QueueDao.getInstance();
		List<Queue> list = null;
		q.makePersistent(list);

	}

	@Test(expected = NullPointerException.class)
	public void makeTransient() {
		QueueDao q = QueueDao.getInstance();

		q.makeTransient(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void makeTransientList() {
		QueueDao q = QueueDao.getInstance();
		List<Queue> list = null;
		q.makeTransient(list);
	}

	@Test
	 public void makePersistent_makeTransient() {
	 QueueDao queueDao = QueueDao.getInstance();
	 Queue queue = queueDao.findById(null, null);

	 assertEquals(true, QueueDao.getInstance().makePersistent(queue));
	 }
}
