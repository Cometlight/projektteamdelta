package at.itb13.oculustests.application.queue;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.domain.readonlyinterfaces.QueueRO;
import at.itb13.teamD.application.exceptions.InvalidInputException;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 3 May 2015
 */
public class QueueController_UnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void constructorWithNullQueue() {
		QueueController qC = new QueueController(null);
		assertEquals(true, qC != null);
		assertEquals(true, qC.getQueue() == null);
	}

	@Test
	public void constructorWithMockedQueue() {
		Queue q = Mockito.mock(Queue.class);
		QueueController qc = new QueueController(q);
		assertEquals(true, qc != null);
		assertEquals(true, qc.getQueue() != null);
	}

	@Test
	public void pushQueueEntry() throws InvalidInputException {
		// Queue q = Mockito.mock(Queue.class);
		// QueueController qc = new QueueController(q);
		QueueController qc = Mockito.mock(QueueController.class);
		PatientRO pRO = Mockito.mock(PatientRO.class);
		CalendarEventRO cRO = Mockito.mock(CalendarEventRO.class);
		assertEquals(false, qc.pushQueueEntry(pRO, cRO));
	}

	@Test
	public void popQueueEntry() {
		QueueController qc = Mockito.mock(QueueController.class);
		assertEquals(true, qc.popQueueEntry() == null);
	}

	@Test
	public void peekQueueEntry() {
		QueueController qc = Mockito.mock(QueueController.class);
		assertEquals(true, qc.peekQueueEntry() == null);
	}

	@Test
	public void removeQueueEntry() {
		QueueController qc = Mockito.mock(QueueController.class);
		PatientRO pRO = Mockito.mock(PatientRO.class);
		assertEquals(false, qc.removeQueueEntry(pRO));
	}

	@Test
	public void isPatientInQueue() {
		QueueController qc = Mockito.mock(QueueController.class);
		PatientRO pRO = Mockito.mock(PatientRO.class);
		assertEquals(false, qc.isPatientInQueue(pRO));
	}

	@Test
	public void representsSameQueueByID() {
		QueueController qc = Mockito.mock(QueueController.class);
		QueueRO pRO = Mockito.mock(QueueRO.class);
		assertEquals(false, qc.representsSameQueueByID(pRO));
	}

}
