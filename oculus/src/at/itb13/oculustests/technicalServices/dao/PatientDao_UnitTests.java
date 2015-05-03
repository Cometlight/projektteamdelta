package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import at.itb13.oculus.technicalServices.dao.PatientDao;

/**
 * Unittesting for class PatientDao
 * 
 * @author Andrew Sparr
 * @date 13.04.2015
 */
public class PatientDao_UnitTests {

	@Test
	public void getInstance() {
		assertEquals(true, (PatientDao.getInstance() != null));
	}
	
	@Test
	public void findAll() {
		assertEquals(true, (PatientDao.getInstance().findAll() != null));
	}
	@Test
	public void findByFirstName() {
		assertEquals(true, (PatientDao.getInstance().findByFirstName("Test").isEmpty()));
	}
	@Test (expected = NullPointerException.class)
	public void findById() {
		PatientDao.getInstance().findById(null);
	}
	@Test
	public void findByLastName() {
		assertEquals(true, (PatientDao.getInstance().findByLastName(null)).isEmpty());
	}
	@Test
	public void findByFullName() {
		assertEquals(true, (PatientDao.getInstance().findByFullName(null, null).isEmpty()));
	}
	
	@Test
	public void findBySocialInsuranceNr() {
		assertEquals(true, (PatientDao.getInstance().findBySocialInsuranceNr(null)) == null);
	}
	
}
