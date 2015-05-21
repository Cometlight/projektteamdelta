package at.itb13.teamF.adapter;

import java.sql.Timestamp;
import java.util.Collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.patientqueue.CouldNotAddPatientToQueueException;
import at.oculus.teamf.domain.entity.exception.patientqueue.CouldNotRemovePatientFromQueueException;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IPatientQueue;
import at.oculus.teamf.domain.entity.interfaces.IQueueEntry;

/**
 * Implementation of IPatientQueue of Team F.
 * Throws NotImplementedException as the queue is not even used 
 * by the received classes from Team F.
 * 
 * @author Karin Trommelschläger
 * @date 18.05.2015
 * 
 */
public class QueueAdapter implements IAdapter, IPatientQueue{

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatientQueue#getEntries()
	 */
	@Override
	public Collection<IQueueEntry> getEntries() {
		throw new NotImplementedException();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatientQueue#addPatient(at.oculus.teamf.domain.entity.interfaces.IPatient, java.sql.Timestamp)
	 */
	@Override
	public void addPatient(IPatient patient, Timestamp arrivaltime)
			throws CouldNotAddPatientToQueueException {
		throw new NotImplementedException();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatientQueue#removePatient(at.oculus.teamf.domain.entity.interfaces.IPatient)
	 */
	@Override
	public void removePatient(IPatient patient)
			throws CouldNotRemovePatientFromQueueException {
		throw new NotImplementedException();
	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		throw new NotImplementedException();
	}

}
