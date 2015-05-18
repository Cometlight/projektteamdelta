package at.itb13.teamF.factories;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.domain.PrescriptionEntry;
import at.itb13.oculus.domain.User;
import at.itb13.oculus.domain.VisualAid;
import at.itb13.teamF.adapter.DiagnosisAdapter;
import at.itb13.teamF.adapter.DoctorAdapter;
import at.itb13.teamF.adapter.ExaminationProtocolAdapter;
import at.itb13.teamF.adapter.OrthoptistAdapter;
import at.itb13.teamF.adapter.PatientAdapter;
import at.itb13.teamF.adapter.PrescriptionAdapter;
import at.itb13.teamF.adapter.PrescriptionEntryAdapter;
import at.itb13.teamF.adapter.VisualAidAdapter;
import at.oculus.teamf.domain.entity.factory.IFactoryTB2;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol;
import at.oculus.teamf.domain.entity.interfaces.IOrthoptist;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IPrescription;
import at.oculus.teamf.domain.entity.interfaces.IPrescriptionEntry;
import at.oculus.teamf.domain.entity.interfaces.IVisualAid;

/**
 * The great implementation of IFactoryTB2.
 * 
 * @author Daniel Scheffknecht
 * @date May 18, 2015
 */
public class FactoryTeamF implements IFactoryTB2 {

	/*
	 * @see at.oculus.teamf.domain.entity.factory.IFactoryTB2#createVisualAid()
	 */
	@Override
	public IVisualAid createVisualAid() {
		VisualAid visualAid = new VisualAid();
		return new VisualAidAdapter(visualAid);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.factory.IFactoryTB2#createDiagnos(java.lang.String, java.lang.String, at.oculus.teamf.domain.entity.interfaces.IDoctor)
	 */
	@Override
	public IDiagnosis createDiagnos(String title, String description,
			IDoctor idoctor) {
		
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setTitle(title);
		diagnosis.setDescription(description);
		
		Doctor doctor = (Doctor)((DoctorAdapter)idoctor).getDomainObject();
		diagnosis.setDoctor(doctor);
				
		return new DiagnosisAdapter(diagnosis);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.factory.IFactoryTB2#createPrescription()
	 */
	@Override
	public IPrescription createPrescription() {
		Prescription prescription = new Prescription();
		return new PrescriptionAdapter(prescription);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.factory.IFactoryTB2#createPrescriptionEntry()
	 */
	@Override
	public IPrescriptionEntry createPrescriptionEntry() {
		PrescriptionEntry prescriptionEntry = new PrescriptionEntry();
		return new PrescriptionEntryAdapter(prescriptionEntry);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.factory.IFactoryTB2#createExaminationProtocol(int, java.util.Date, java.util.Date, java.lang.String, at.oculus.teamf.domain.entity.interfaces.IPatient, at.oculus.teamf.domain.entity.interfaces.IDoctor, at.oculus.teamf.domain.entity.interfaces.IOrthoptist, at.oculus.teamf.domain.entity.interfaces.IDiagnosis)
	 */
	@Override
	public IExaminationProtocol createExaminationProtocol(int id,
			Date startTime, Date endTime, String description, IPatient ipatient,
			IDoctor idoctor, IOrthoptist iorthoptist, IDiagnosis idiagnosis) {
		
		ExaminationProtocol examinationProtocol = new ExaminationProtocol();
		examinationProtocol.setExaminationProtocolId(id);
		
		LocalDateTime localDateTimeStart = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		examinationProtocol.setStartProtocol(localDateTimeStart);
		
		LocalDateTime localDateTimeEnd = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		examinationProtocol.setEndProtocol(localDateTimeEnd);
		
		examinationProtocol.setDescription(description);
		
		Patient patient = (Patient)((PatientAdapter)ipatient).getDomainObject();
		examinationProtocol.setPatient(patient);
		
		User user = null;
		if(idoctor != null) {
			Doctor doctor = (Doctor)((DoctorAdapter)idoctor).getDomainObject();
			user = doctor.getUser();
		} else if (iorthoptist != null) {
			Orthoptist orthoptist = (Orthoptist)((OrthoptistAdapter)iorthoptist).getDomainObject();
			user = orthoptist.getUser();
		}
		examinationProtocol.setUser(user);
		
		if(idiagnosis != null) {
			Diagnosis diagnosis = (Diagnosis)((DiagnosisAdapter)idiagnosis).getDomainObject();
			examinationProtocol.setDiagnosis(diagnosis);
		}
		
		return new ExaminationProtocolAdapter(examinationProtocol);
	}

}
