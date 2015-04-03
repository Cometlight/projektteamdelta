package at.itb13.oculus.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Medicine implements java.io.Serializable {

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

	public Integer getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public Set<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

}
