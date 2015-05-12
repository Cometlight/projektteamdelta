package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.interfaces.IPatient;
import at.itb13.oculus.technicalServices.PersistenceFacade;
import at.itb13.oculus.technicalServices.exceptions.PersistenceFacadeException;

/**
 * JunitTests for PerstistenceFacade
 * 
 * @author Andrew Sparr
 * @date 11.05.2015
 */
public class PersistenceFacade_UnitTests {

	@Test
	public void searchFor() throws PersistenceFacadeException {
		Collection<IPatient> patientList = PersistenceFacade.getInstance().searchFor(IPatient.class, "Donald");
//		for(IPatient p : patientList){
//			System.out.println(p.getFirstName());
//		}
		
		
		assertEquals(1, patientList.size());

		Collection<IPatient> patientList2 = PersistenceFacade.getInstance().searchFor(IPatient.class, "Donald Duck");
		assertEquals(1, patientList2.size());
		
//		((Patient)p).setFirstName("Ronald");
//		PersistenceFacade.getInstance().makePersistent(p);
//		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
//		System.out.println(p2.getFirstName());
//		assertEquals(p2.getFirstName(), "Ronald");
	}
	
	
	
	@Test
	public void getByID() {
		IPatient p = PersistenceFacade.getInstance().getById(1, IPatient.class);
		((Patient)p).setFirstName("Ronald");
		PersistenceFacade.getInstance().makePersistent(p);
		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
//		System.out.println(p2.getFirstName());
		assertEquals(p2.getFirstName(), "Ronald");
	}
	
	@Test
	public void makePersistent() {
		IPatient p = PersistenceFacade.getInstance().getById(1, IPatient.class);
		((Patient)p).setFirstName("Donald");
		PersistenceFacade.getInstance().makePersistent(p);
		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
//		System.out.println(p2.getFirstName());
		assertEquals(p2.getFirstName(), "Donald");
	}
	@Test
	public void getAll() {
		List<IPatient>pp = null;
		pp = PersistenceFacade.getInstance().getAll(IPatient.class);
		assertEquals(true, pp!=null);
		assertEquals(true, !pp.isEmpty());
//		System.out.println(pp.size());
	}
	
}