package at.itb13.oculustests.application.queue;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import at.itb13.oculus.application.queue.QueueController;
import at.itb13.oculus.domain.Queue;

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

}
