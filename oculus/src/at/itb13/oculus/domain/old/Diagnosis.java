package at.itb13.oculus.domain.old;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Diagnosis implements java.io.Serializable {

	private Integer diagnosisId;
	private Doctor doctor;
	private String title;
	private String description;
	private Set<Examinationprotocol> examinationprotocols = new HashSet<Examinationprotocol>(
			0);
	private Set<Visualaid> visualaids = new HashSet<Visualaid>(0);
	private Set<Workdisability> workdisabilities = new HashSet<Workdisability>(
			0);
	private Set<Medicine> medicines = new HashSet<Medicine>(0);

	public Diagnosis() {
	}

	public Diagnosis(Doctor doctor, String title, String description,
			Set<Examinationprotocol> examinationprotocols,
			Set<Visualaid> visualaids, Set<Workdisability> workdisabilities,
			Set<Medicine> medicines) {
		this.doctor = doctor;
		this.title = title;
		this.description = description;
		this.examinationprotocols = examinationprotocols;
		this.visualaids = visualaids;
		this.workdisabilities = workdisabilities;
		this.medicines = medicines;
	}

	public Integer getDiagnosisId() {
		return this.diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Examinationprotocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<Examinationprotocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

	public Set<Visualaid> getVisualaids() {
		return this.visualaids;
	}

	public void setVisualaids(Set<Visualaid> visualaids) {
		this.visualaids = visualaids;
	}

	public Set<Workdisability> getWorkdisabilities() {
		return this.workdisabilities;
	}

	public void setWorkdisabilities(Set<Workdisability> workdisabilities) {
		this.workdisabilities = workdisabilities;
	}

	public Set<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

}
