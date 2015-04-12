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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Medicine generated by hbm2java
 */
@Entity
@Table(name = "medicine", catalog = "oculusdb")
public class Medicine implements java.io.Serializable {

	private static final Logger _logger = LogManager.getLogger(Medicine.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Integer medicineId;
	private Diagnosis diagnosis;
	private String name;
	private String dose;
	private Set<Prescription> prescriptions = new HashSet<Prescription>(0);

	public Medicine() {
	}

	public Medicine(Diagnosis diagnosis, String name) {
		this.diagnosis = diagnosis;
		this.name = name;
	}

	public Medicine(Diagnosis diagnosis, String name, String dose,
			Set<Prescription> prescriptions) {
		this.diagnosis = diagnosis;
		this.name = name;
		this.dose = dose;
		this.prescriptions = prescriptions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "medicineId", unique = true, nullable = false)
	public Integer getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId", nullable = false)
	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dose")
	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "medicines")
	public Set<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

}
