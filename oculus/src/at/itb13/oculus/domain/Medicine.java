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
@Table(name = "medicine", catalog = "oculus_d")
public class Medicine implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(Medicine.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _medicineId;
	private Diagnosis _diagnosis;
	private String _name;
	private String _dose;
	private Set<PrescriptionEntry> _prescriptionEntries = new HashSet<>(0);

	public Medicine() { }

	public Medicine(Diagnosis diagnosis, String name) {
		_diagnosis = diagnosis;
		_name = name;
	}

	public Medicine(Diagnosis diagnosis, String name, String dose,
			Set<PrescriptionEntry> prescriptionentries) {
		_diagnosis = diagnosis;
		_name = name;
		_dose = dose;
		_prescriptionEntries = prescriptionentries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "medicineId", unique = true, nullable = false)
	public Integer getMedicineId() {
		return _medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		_medicineId = medicineId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId", nullable = false)
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@Column(name = "dose")
	public String getDose() {
		return _dose;
	}

	public void setDose(String dose) {
		_dose = dose;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicine")
	public Set<PrescriptionEntry> getPrescriptionEntries() {
		return _prescriptionEntries;
	}

	public void setPrescriptionEntries(Set<PrescriptionEntry> prescriptionentries) {
		_prescriptionEntries = prescriptionentries;
	}

}
