package at.itb13.teamF.adapter;

import at.itb13.oculus.domain.Medicine;
import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.domain.PrescriptionEntry;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IMedicine;
import at.oculus.teamf.domain.entity.interfaces.IPrescription;
import at.oculus.teamf.domain.entity.interfaces.IPrescriptionEntry;

/**
 * Adapterclass for the domainclass PrescriptionEntry.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class PrescriptionEntryAdapter implements IAdapter, IPrescriptionEntry {
	private PrescriptionEntry _prescriptionEntry;

	public PrescriptionEntryAdapter() {
	}

	public PrescriptionEntryAdapter(PrescriptionEntry prescriptionEntry) {
		_prescriptionEntry = prescriptionEntry;
	}

	@Override
	public int getId() {
		return _prescriptionEntry.getPrescriptionEntryId();
	}

	@Override
	public void setId(int id) {
		_prescriptionEntry.setPrescriptionEntryId(id);
	}

	@Override
	public IPrescription getPrescription() {
		Prescription prescription =  _prescriptionEntry.getPrescription();
		
		if (prescription != null){
			return new PrescriptionAdapter(prescription);
		}
		
		return null;
	}

	@Override
	public IMedicine getMedicine() {
		Medicine medicine = _prescriptionEntry.getMedicine();
		
		if(medicine != null){
			return new MedicineAdapter(medicine);	
		}
		
		return null;
	}

	@Override
	public void setMedicine(IMedicine medicine) {
		if(medicine != null){
			MedicineAdapter medicineAdapter = (MedicineAdapter) medicine;
			Medicine med = (Medicine) medicineAdapter.getDomainObject();
			if(med != null){
				_prescriptionEntry.setMedicine(med);	
			}
		}
	}

	@Override
	public Object getDomainObject() {
		return _prescriptionEntry;
	}

}
