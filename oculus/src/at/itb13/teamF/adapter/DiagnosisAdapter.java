package at.itb13.teamF.adapter;

import java.util.Collection;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Medicine;
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
		// TODO Auto-generated method stubhock 
		return null;
	}

	@Override
	public void addMedicine(IMedicine medicine)
			throws CouldNotAddMedicineException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVisualAid(IVisualAid visualAid)
			throws CouldNotAddVisualAidException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<IVisualAid> getVisualAid()
			throws CouldNotGetVisualAidException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDomainObject() {
		return _diagnosis;
	}
}
