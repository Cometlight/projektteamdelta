package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "diagnosis", catalog = "oculus_d")
public class Diagnosis implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(Diagnosis.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _diagnosisId;
	private Doctor _doctor;
	private String _title;
	private String _description;
	private Set<ExaminationProtocol> _examinationProtocols = new HashSet<ExaminationProtocol>(0);
	private Set<VisualAid> _visualAids = new HashSet<VisualAid>(0);
	private Set<WorkDisability> _workDisabilities = new HashSet<WorkDisability>(0);
	private Set<Medicine> _medicines = new HashSet<Medicine>(0);

	public Diagnosis() { }

	public Diagnosis(Doctor doctor, String title, String description,
			Set<ExaminationProtocol> examinationprotocols,
			Set<VisualAid> visualaids, Set<WorkDisability> workdisabilities,
			Set<Medicine> medicines) {
		_doctor = doctor;
		_title = title;
		_description = description;
		_examinationProtocols = examinationprotocols;
		_visualAids = visualaids;
		_workDisabilities = workdisabilities;
		_medicines = medicines;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "diagnosisId", unique = true, nullable = false)
	public Integer getDiagnosisId() {
		return _diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		_diagnosisId = diagnosisId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	public Doctor getDoctor() {
		return _doctor;
	}

	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	@Column(name = "title")
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<ExaminationProtocol> getExaminationprotocols() {
		return _examinationProtocols;
	}

	public void setExaminationprotocols(
			Set<ExaminationProtocol> examinationprotocols) {
		_examinationProtocols = examinationprotocols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<VisualAid> getVisualaids() {
		return _visualAids;
	}

	public void setVisualaids(Set<VisualAid> visualaids) {
		_visualAids = visualaids;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<WorkDisability> getWorkdisabilities() {
		return _workDisabilities;
	}

	public void setWorkdisabilities(Set<WorkDisability> workdisabilities) {
		_workDisabilities = workdisabilities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<Medicine> getMedicines() {
		return _medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		_medicines = medicines;
	}
}
