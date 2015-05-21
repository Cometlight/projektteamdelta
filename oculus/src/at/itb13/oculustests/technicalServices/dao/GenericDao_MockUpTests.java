package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;

/**
 * @author Andrew Sparr
 * @date 3 May 2015
 */
public class GenericDao_MockUpTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@SuppressWarnings("unchecked")
	@Test
	public void findById() {
		GenericDao<Patient> gd = Mockito.mock(GenericDao.class);
		Integer integer = null;
		assertEquals(true, gd.findById(integer) == null);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void list() {
		GenericDao<Patient> gd = Mockito.mock(GenericDao.class);
		assertEquals(true, gd.list() != null);
	}

	@Test
	public void findAll() {
		GenericDao<Patient> gd = Mockito.mock(GenericDao.class);
		assertEquals(true, gd.findAll() != null);
	}

	@Test
	public void makePersistent1() {
		GenericDao<Patient> gd = Mockito.mock(GenericDao.class);
		LinkedList<Patient> list = null;
		assertEquals(false, gd.makePersistent(list));
	}
}
