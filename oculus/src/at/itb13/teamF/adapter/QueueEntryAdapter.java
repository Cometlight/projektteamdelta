package at.itb13.teamF.adapter;

import java.sql.Timestamp;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.QueueEntry;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IQueueEntry;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 18.05.2015
 */
public class QueueEntryAdapter implements IQueueEntry, IAdapter {
	private QueueEntry _entry;
	
	private QueueEntryAdapter(){}
	private QueueEntryAdapter(QueueEntry entry){
		_entry = entry;
	}
	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getId()
	 */
	@Override
	public int getId() {
		return _entry.getQueueEntryId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#setId(int)
	 */
	@Override
	public void setId(int id) {
		_entry.setQueueEntryId(id);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getDoctor()
	 */
	@Override
	public IDoctor getDoctor() {
		return null;	//TODO
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#setDoctor(at.oculus.teamf.domain.entity.interfaces.IDoctor)
	 */
	@Override
	public void setDoctor(IDoctor doctor) {
		//TODO
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getOrthoptist()
	 */
	@Override
	public IOrthoptist getOrthoptist() {
		// TODO
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#setOrthoptist(at.oculus.teamf.domain.entity.interfaces.IOrthoptist)
	 */
	@Override
	public void setOrthoptist(IOrthoptist orthoptist) {
		// TODO

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getPatient()
	 */
	@Override
	public IPatient getPatient() {
		Patient patient = _entry.getPatient();
		return new PatientAdapter(patient);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#setPatient(at.oculus.teamf.domain.entity.interfaces.IPatient)
	 */
	@Override
	public void setPatient(IPatient patient) {
		PatientAdapter patientAdapter = (PatientAdapter)patient;
		Patient pa = (Patient)patientAdapter.getDomainObject();
		_entry.setPatient(pa);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getQueueIdParent()
	 */
	@Override
	public Integer getQueueIdParent() {
		// TODO
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#setQueueIdParent(java.lang.Integer)
	 */
	@Override
	public void setQueueIdParent(Integer queueIdParent) {
		// TODO Auto-generated method stub

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IQueueEntry#getArrivalTime()
	 */
	@Override
	public Timestamp getArrivalTime() {
		return Timestamp.valueOf(_entry.getArrivalTime());
	}

}
