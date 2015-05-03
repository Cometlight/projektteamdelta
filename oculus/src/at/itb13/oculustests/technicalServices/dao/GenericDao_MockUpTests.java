package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
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
