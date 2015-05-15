package at.itb13.oculustests.technicalServices.dao;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.exceptions.PersistenceFacadeException;
import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacade;
import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacadeProvider;
import at.itb13.teamD.domain.interfaces.IPatient;

/**
 * JunitTests for PerstistenceFacade
 * 
 * @author Andrew Sparr
 * @date 11.05.2015
 */
public class PersistenceFacade_UnitTests {

	@Test
	public void searchFor() throws PersistenceFacadeException {
		PersistenceFacadeProvider.setPersistenceFacade(new PersistenceFacade());
		Collection<IPatient> patientList = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class, "Donald");
//		new PersistenceFacadeFactory();
//		Collection<IPatient> patientList = APersistenceFacadeFactory.getPersistenceFacadeFactory().getPersistenceFacade().searchFor(IPatient.class,  "Donald");
		
//		for(IPatient p : patientList){
//			System.out.println(p.getFirstName());
//		}
		
		
		assertEquals(1, patientList.size());

//		Collection<IPatient> patientList2 = PersistenceFacade.getInstance().searchFor(IPatient.class, "Donald Duck");
		
		//Zweiter Test
		Collection<IPatient> patientList2 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "Donald Duck");
		assertEquals(1, patientList2.size());
		
		
		Collection<IPatient> patientList3 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "Lara Mey");
		assertEquals(1, patientList3.size());
		
		Collection<IPatient> patientList4 = PersistenceFacadeProvider.getPersistenceFacade().searchFor(IPatient.class,  "John");
		assertEquals(2, patientList4.size());
		
//		((Patient)p).setFirstName("Ronald");
//		PersistenceFacade.getInstance().makePersistent(p);
//		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
//		System.out.println(p2.getFirstName());
//		assertEquals(p2.getFirstName(), "Ronald");
	}
	
	
	
	@Test
	public void getByID() {
	PersistenceFacadeProvider.setPersistenceFacade(new PersistenceFacade());
		IPatient p = PersistenceFacadeProvider.getPersistenceFacade().getById(1, IPatient.class);
		((Patient)p).setFirstName("Ronald");
		PersistenceFacadeProvider.getPersistenceFacade().makePersistent(p);
		IPatient p2 = PersistenceFacadeProvider.getPersistenceFacade().getById(1, IPatient.class);
////		System.out.println(p2.getFirstName());
		assertEquals(p2.getFirstName(), "Ronald");
	}
//	
//	@Test
//	public void makePersistent() {
//		IPatient p = PersistenceFacade.getInstance().getById(1, IPatient.class);
//		((Patient)p).setFirstName("Donald");
//		PersistenceFacade.getInstance().makePersistent(p);
//		IPatient p2 = PersistenceFacade.getInstance().getById(1, IPatient.class);
////		System.out.println(p2.getFirstName());
//		assertEquals(p2.getFirstName(), "Donald");
//	}
//	@Test
//	public void getAll() {
//		List<IPatient>pp = null;
//		pp = PersistenceFacade.getInstance().getAll(IPatient.class);
//		assertEquals(true, pp!=null);
//		assertEquals(true, !pp.isEmpty());
////		System.out.println(pp.size());
//	}
	
}
