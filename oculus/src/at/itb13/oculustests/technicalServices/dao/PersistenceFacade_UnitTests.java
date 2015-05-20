package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacade;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamD.technicalServices.exceptions.PersistenceFacadeException;
import at.itb13.teamD.technicalServices.persistenceFacade.PersistenceFacadeProvider;

/**
 * JunitTests for PerstistenceFacade
 * 
 * @author Andrew Sparr, Karin Trommelschläger
 * @date 11.05.2015
 */
public class PersistenceFacade_UnitTests {

	@Test
	public void searchFor() throws PersistenceFacadeException {
		PersistenceFacadeProvider.setPersistenceFacade(new PersistenceFacade());
		Collection<IPatient> patientList = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class, "Donald");
		assertEquals(1, patientList.size());
		
		//Zweiter Test
		Collection<IPatient> patientList2 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "Donald Duck");
		assertEquals(1, patientList2.size());
		Collection<IPatient> patientList3 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "Jane Doe");
		assertEquals(1, patientList3.size());
		Collection<IPatient> patientList4 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "John");
		assertEquals(2, patientList4.size());
	}
	
	@Test
	public void getByIDAndMakePersistent() {
		String name;
	PersistenceFacadeProvider.setPersistenceFacade(new PersistenceFacade());
		IPatient p = PersistenceFacadeProvider.getPersistenceFacade().getById(1, IPatient.class);
		name = p.getFirstName();
		((Patient)p).setFirstName("Ronald");
		PersistenceFacadeProvider.getPersistenceFacade().makePersistent(p);
		IPatient p2 = PersistenceFacadeProvider.getPersistenceFacade().getById(1, IPatient.class);
		assertEquals(p2.getFirstName(), "Ronald");
		((Patient)p).setFirstName(name);
		PersistenceFacadeProvider.getPersistenceFacade().makePersistent(p);
	}

//not needed anymore, because this case is already tested in method above
//	@Test
//	public void makePersistent() {
//		IPatient p = PersistenceFacadeProvider.getPersistenceFacade().getById(1, IPatient.class);
//		((Patient)p).setFirstName("Donald");
//		PersistenceFacade.getInstance().makePersistent(p);
//		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
////		System.out.println(p2.getFirstName());
//		assertEquals(p2.getFirstName(), "Donald");
//	}
	
	@Test
	public void getAll() {
		List<IPatient>pp = null;
		pp = PersistenceFacadeProvider.getPersistenceFacade().getAll(IPatient.class);
		assertEquals(true, pp!=null);
		assertEquals(true, !pp.isEmpty());
	}
	
}
