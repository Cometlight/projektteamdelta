package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 13.04.2015
 */
public class PatientDao_UnitTests {

	@Test
	public void testIfNull() {
		PatientDao patDao = new PatientDao();
		assertEquals(true, (patDao != null));
		
		assertEquals(true, (patDao.findAll() != null));
		assertEquals(true, (patDao.findByFirstName("Test").isEmpty()));
		assertEquals(true, (patDao.findById(null) == null));
		assertEquals(true, (patDao.findByLastName(null)).isEmpty());
		assertEquals(true, (patDao.findByLastName(null)).isEmpty());
		assertEquals(true, (patDao.findByFullName(null, null).isEmpty()));
		assertEquals(true, (patDao.findBySocialInsuranceNr(null)) == null);
		assertEquals(true, (patDao.list().isEmpty()));
	}

}
