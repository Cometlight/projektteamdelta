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
		assertEquals(true, (PatientDao.getInstance() != null));
		
		assertEquals(true, (PatientDao.getInstance().findAll() != null));
		assertEquals(true, (PatientDao.getInstance().findByFirstName("Test").isEmpty()));
		assertEquals(true, (PatientDao.getInstance().findById(null) == null));
		assertEquals(true, (PatientDao.getInstance().findByLastName(null)).isEmpty());
		assertEquals(true, (PatientDao.getInstance().findByLastName(null)).isEmpty());
		assertEquals(true, (PatientDao.getInstance().findByFullName(null, null).isEmpty()));
		assertEquals(true, (PatientDao.getInstance().findBySocialInsuranceNr(null)) == null);
		assertEquals(true, (PatientDao.getInstance().list().isEmpty()));
	}

}
