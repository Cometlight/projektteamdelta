package at.itb13.teamF.adapter;

import java.util.Collection;
import java.util.HashSet;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Medicine;
import at.itb13.oculus.domain.VisualAid;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.CouldNotAddMedicineException;
import at.oculus.teamf.domain.entity.exception.CouldNotAddVisualAidException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetMedicineException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetVisualAidException;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IMedicine;
import at.oculus.teamf.domain.entity.interfaces.IVisualAid;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class DiagnosisAdapter implements IAdapter, IDiagnosis{
	private Diagnosis _diagnosis;
	
	public DiagnosisAdapter() {
	}

	public DiagnosisAdapter(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Override
	public int getId() {
		return _diagnosis.getDiagnosisId();
	}

	@Override
	public void setId(int id) {
		_diagnosis.setDiagnosisId(id);
	}

	@Override
	public String getTitle() {
		return _diagnosis.getTitle();
	}

	@Override
	public void setTitle(String title) {
		_diagnosis.setTitle(title);
	}

	@Override
	public String getDescription() {
		return _diagnosis.getDescription();
	}

	@Override
	public void setDescription(String description) {
		_diagnosis.setDescription(description);
	}

	@Override
	public Integer getDoctorId() {
		return _diagnosis.getDoctor().getDoctorId();
	}

	@Override
	public void setDoctorId(Integer doctorId) {
		_diagnosis.getDoctor().setDoctorId(doctorId);
	}

	@Override
	public IDoctor getDoctor() {
		Doctor doctor =_diagnosis.getDoctor();
		return new DoctorAdapter(doctor);
	}

	@Override
	public void setDoctor(IDoctor doctor) {
		DoctorAdapter doctorAdapter = (DoctorAdapter) doctor;
		Doctor doc = (Doctor) doctorAdapter.getDomainObject();
		_diagnosis.setDoctor(doc);
	}

	@Override
	public Collection<IMedicine> getMedicine()
			throws CouldNotGetMedicineException {
		Collection<IMedicine> iMedicineCollection = new HashSet<>();
		Collection<Medicine> medicineCollection = _diagnosis.getMedicines();
		for(Medicine med : medicineCollection){
			iMedicineCollection.add((IMedicine) med);
		}
		
		return iMedicineCollection;
	}

	@Override
	public void addMedicine(IMedicine medicine)
			throws CouldNotAddMedicineException {
		Collection<Medicine> coll = _diagnosis.getMedicines();
		MedicineAdapter medAda = (MedicineAdapter) medicine;
		coll.add((Medicine) medAda.getDomainObject());
	}

	@Override
	public void addVisualAid(IVisualAid visualAid)
			throws CouldNotAddVisualAidException {
		Collection<VisualAid> coll = _diagnosis.getVisualaids();
		VisualAidAdapter visAda = (VisualAidAdapter) visualAid;
		coll.add((VisualAid) visAda.getDomainObject());
	}

	@Override
	public Collection<IVisualAid> getVisualAid()
			throws CouldNotGetVisualAidException {
		Collection<IVisualAid> visualAids = new HashSet<>();
		Collection<VisualAid> coll = _diagnosis.getVisualaids();
		for(VisualAid entry : coll){
			visualAids.add((IVisualAid) entry.getDiagnosis());
		}
		return visualAids;
	}

	@Override
	public Object getDomainObject() {
		return _diagnosis;
	}
}
