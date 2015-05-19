package at.itb13.teamF.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.ExaminationResult;
import at.itb13.oculus.domain.Medicine;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.User;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.CouldNotGetExaminationResultException;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol;
import at.oculus.teamf.domain.entity.interfaces.IExaminationResult;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatient;

/**
 * TODO: Insert description here.
 * 
 * @author Andrew Sparr
 * @date 18.05.2015
 */
public class ExaminationProtocolAdapter implements IAdapter,
		IExaminationProtocol {
	private ExaminationProtocol _examinationProtocol;

	public ExaminationProtocolAdapter() {
	}

	public ExaminationProtocolAdapter(ExaminationProtocol examinationProtocol) {
		_examinationProtocol = examinationProtocol;
	}

	@Override
	public int getId() {
		return _examinationProtocol.getExaminationProtocolId();
	}

	@Override
	public void setId(int id) {
		_examinationProtocol.setExaminationProtocolId(id);
	}

	@Override
	public Date getStartTime() {
		LocalDateTime localDateTime = _examinationProtocol.getStartProtocol();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault())
				.toInstant());
		return date;
	}

	@Override
	public void setStartTime(Date startTime) {
		LocalDateTime localDateTime = startTime.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDateTime();
		_examinationProtocol.setStartProtocol(localDateTime);
	}

	@Override
	public Date getEndTime() {
		LocalDateTime localDateTime = _examinationProtocol.getEndProtocol();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault())
				.toInstant());
		return date;
	}

	@Override
	public void setEndTime(Date endTime) {
		LocalDateTime localDateTime = endTime.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDateTime();
		_examinationProtocol.setStartProtocol(localDateTime);
	}

	@Override
	public String getDescription() {
		return _examinationProtocol.getDescription();
	}

	@Override
	public void setDescription(String description) {
		_examinationProtocol.setDescription(description);
	}

	@Override
	public IDoctor getDoctor() {
		Doctor doctor = _examinationProtocol.getUser().getDoctor();
		if (doctor != null) {
			return new DoctorAdapter(doctor);
		} else {
			return null;
		}
	}

	@Override
	public void setDoctor(IDoctor doctor) {
		DoctorAdapter doctorAdapter = (DoctorAdapter) doctor;
		Doctor doc = (Doctor) doctorAdapter.getDomainObject();
		_examinationProtocol.getUser().setDoctor(doc);
	}

	@Override
	public IOrthoptist getOrthoptist() {
		Orthoptist orthoptist = _examinationProtocol.getUser().getOrthoptist();
		if (orthoptist != null) {
			return new OrthoptistAdapter(orthoptist);
		} else {
			return null;
		}
	}

	@Override
	public void setOrthoptist(IOrthoptist orthoptist) {
		OrthoptistAdapter orthoptistAdapter = (OrthoptistAdapter) orthoptist;
		Orthoptist ort = (Orthoptist) orthoptistAdapter.getDomainObject();
		_examinationProtocol.getUser().setOrthoptist(ort);
	}

	@Override
	public IDiagnosis getDiagnosis() {
		Diagnosis diagnosis = _examinationProtocol.getDiagnosis();
		return new DiagnosisAdapter(diagnosis);
	}

	@Override
	public void setDiagnosis(IDiagnosis diagnosis) {
		DiagnosisAdapter diagnosisAdapter = (DiagnosisAdapter) diagnosis;
		Diagnosis diag = (Diagnosis) diagnosisAdapter.getDomainObject();
		_examinationProtocol.setDiagnosis(diag);
	}

	@Override
	public IPatient getPatient() {
		Patient patient = _examinationProtocol.getPatient();
		return new PatientAdapter(patient);
	}

	@Override
	public void setPatient(IPatient patient) {
		PatientAdapter patientAdapter = (PatientAdapter) patient;
		Patient pat = (Patient) patientAdapter.getDomainObject();
		_examinationProtocol.setPatient(pat);
	}

	@Override
	public Collection<IExaminationResult> getExaminationResults()
			throws CouldNotGetExaminationResultException {
		// FIXME
		Set<ExaminationResult> examinationResults = _examinationProtocol
				.getExaminationResults();
		Set<IExaminationResult> iExaminationResults = new HashSet<>(0);

		for (ExaminationResult ex : examinationResults) {
			iExaminationResults.add(new ExaminationResultAdapter(ex));
		}
		
		return iExaminationResults;
	}

	@Override
	public Object getDomainObject() {
		return _examinationProtocol;
	}

}
