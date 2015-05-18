package at.itb13.teamF.adapter;

import java.util.Collection;
import java.util.Date;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Medicine;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Prescription;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.CantGetPresciptionEntriesException;
import at.oculus.teamf.domain.entity.exception.CouldNotAddPrescriptionEntryException;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IPrescription;
import at.oculus.teamf.domain.entity.interfaces.IPrescriptionEntry;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class PrescriptionAdapter implements IAdapter, IPrescription {

	private Prescription _prescription;
	
	public PrescriptionAdapter() {
	}

	public PrescriptionAdapter(Prescription prescription) {
		_prescription = prescription;
	}
	
	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDomain#getId()
	 */
	@Override
	public int getId() {
		return _prescription.getPrescriptionId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IDomain#setId(int)
	 */
	@Override
	public void setId(int id) {
		_prescription.setPrescriptionId(id);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#getPatient()
	 */
	@Override
	public IPatient getPatient() {
		Patient patient = _prescription.getPatient();
		return new PatientAdapter(patient);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#setPatient(at.oculus.teamf.domain.entity.interfaces.IPatient)
	 */
	@Override
	public void setPatient(IPatient iPatient) {
		PatientAdapter patientAdapter = (PatientAdapter) iPatient;
		Patient patient = (Patient) patientAdapter.getDomainObject();
		_prescription.setPatient(patient);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#setLastPrint(java.util.Date)
	 */
	@Override
	public void setLastPrint(Date lastPrint) {
		// TODO Auto-generated method stub

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#getLastPrint()
	 */
	@Override
	public Date getLastPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#getPrescriptionEntries()
	 */
	@Override
	public Collection<IPrescriptionEntry> getPrescriptionEntries()
			throws CantGetPresciptionEntriesException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPrescription#addPrescriptionEntry(at.oculus.teamf.domain.entity.interfaces.IPrescriptionEntry)
	 */
	@Override
	public void addPrescriptionEntry(IPrescriptionEntry prescriptionEntry)
			throws CouldNotAddPrescriptionEntryException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
