package at.itb13.teamF.adapter;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Medicine;
import at.itb13.oculus.domain.PrescriptionEntry;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IMedicine;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class MedicineAdapter implements IAdapter, IMedicine {
	private Medicine _medicine;

	public MedicineAdapter() {
	}

	public MedicineAdapter(Medicine medicine) {
		_medicine = medicine;
	}

	@Override
	public int getId() {
		return _medicine.getMedicineId();
	}

	@Override
	public void setId(int id) {
		_medicine.setMedicineId(id);
	}

	@Override
	public IDiagnosis getDiagnosis() {
		Diagnosis diagnosis = _medicine.getDiagnosis();
		return new DiagnosisAdapter(diagnosis);
	}

	@Override
	public void setDiagnosis(IDiagnosis diagnosis) {
		DiagnosisAdapter diagnosisAdapter = (DiagnosisAdapter) diagnosis;
		Diagnosis diag = (Diagnosis) diagnosisAdapter.getDomainObject();
		_medicine.setDiagnosis(diag);
		
	}

	@Override
	public String getName() {
		return _medicine.getName();
	}

	@Override
	public void setName(String name) {
		_medicine.setName(name);
	}

	@Override
	public String getDose() {
		return _medicine.getDose();
	}

	@Override
	public void setDose(String dose) {
		_medicine.setDose(dose);
	}

	@Override
	public Object getDomainObject() {
		return _medicine;
	}

}
