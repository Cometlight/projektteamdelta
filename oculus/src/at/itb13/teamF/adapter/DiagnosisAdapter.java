package at.itb13.teamF.adapter;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
import at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol;
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
		if(doctor != null){
			return new DoctorAdapter(doctor);
		}
		return null;
	}

	@Override
	public void setDoctor(IDoctor doctor) {
		if(doctor != null){
			DoctorAdapter doctorAdapter = (DoctorAdapter) doctor;
			Doctor doc = (Doctor) doctorAdapter.getDomainObject();
			_diagnosis.setDoctor(doc);
		}
	}

	@Override
	public Collection<IMedicine> getMedicine()
			throws CouldNotGetMedicineException {
//		Collection<IMedicine> iMedicineCollection = new HashSet<>();
//		Collection<Medicine> medicineCollection = _diagnosis.getMedicines();
//		for(Medicine med : medicineCollection){
//			iMedicineCollection.add((IMedicine) med);
//		}
//		
//		return iMedicineCollection;
		
		if(_diagnosis.getMedicines() != null){
		
		Set<Medicine> set = _diagnosis.getMedicines();
		Collection<IMedicine> coll = new HashSet<>();
		for(Medicine med : set) {
			MedicineAdapter medAda = new MedicineAdapter(med);
			coll.add(medAda);
		}
		return coll;
		}else{
			return null;
		}
	}

	@Override
	public void addMedicine(IMedicine medicine)
			throws CouldNotAddMedicineException {
		if(medicine != null){
			Collection<Medicine> coll;
			if(_diagnosis.getMedicines() != null){
				coll = _diagnosis.getMedicines();
			}else{
				coll = new HashSet<Medicine>();
			}
			MedicineAdapter medAda = (MedicineAdapter) medicine;
			coll.add((Medicine) medAda.getDomainObject());
			
			_diagnosis.setMedicines((Set<Medicine>) coll);

		}

	}

	@Override
	public void addVisualAid(IVisualAid visualAid)
			throws CouldNotAddVisualAidException {
		if(visualAid != null){
			Collection<VisualAid> coll;
			if(_diagnosis.getVisualaids() != null){
				coll = _diagnosis.getVisualaids();
			}
			else{
				coll = new LinkedList<VisualAid>();
				_diagnosis.setVisualaids((Set<VisualAid>) coll);
			}
			VisualAidAdapter visAda = (VisualAidAdapter) visualAid;
			coll.add((VisualAid) visAda.getDomainObject());
		}
	}

	@Override
	public Collection<IVisualAid> getVisualAid()
			throws CouldNotGetVisualAidException {
//		Collection<IVisualAid> visualAids = new HashSet<>();
//		Collection<VisualAid> coll = _diagnosis.getVisualaids();
//		for(VisualAid entry : coll){
//			visualAids.add((IVisualAid) entry.getDiagnosis());
//		}
//		return visualAids;
		
		if(_diagnosis.getVisualaids() != null){
			
			Set<VisualAid> set = _diagnosis.getVisualaids();
			Collection<IVisualAid> coll = new HashSet<>();
			for(VisualAid aid : set) {
				VisualAidAdapter aidAda = new VisualAidAdapter(aid);
				coll.add(aidAda);
			}
			return coll;
		}else{
			return null;
		}
	}

	@Override
	public Object getDomainObject() {
		return _diagnosis;
	}
}
