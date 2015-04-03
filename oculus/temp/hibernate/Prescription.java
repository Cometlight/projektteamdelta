// default package
// Generated 03.04.2015 15:26:50 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Prescription generated by hbm2java
 */
public class Prescription implements java.io.Serializable {

	private Integer prescriptionId;
	private Patient patient;
	private Date issueDate;
	private Set<Medicine> medicines = new HashSet<Medicine>(0);

	public Prescription() {
	}

	public Prescription(Patient patient, Date issueDate, Set<Medicine> medicines) {
		this.patient = patient;
		this.issueDate = issueDate;
		this.medicines = medicines;
	}

	public Integer getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Set<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

}