package at.itb13.oculus.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
@Entity
@Table(name = "prescription", catalog = "oculus_d")
public class Prescription implements java.io.Serializable {
	private static final Logger _logger = LogManager.getLogger(Prescription.class.getName());
	
	private Integer prescriptionId;
	private Patient patient;
	private Date issueDate;
	private Set<PrescriptionEntry> prescriptionentries = new HashSet<PrescriptionEntry>(
			0);

	public Prescription() {
	}

	public Prescription(Patient patient, Date issueDate,
			Set<PrescriptionEntry> prescriptionentries) {
		this.patient = patient;
		this.issueDate = issueDate;
		this.prescriptionentries = prescriptionentries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prescriptionId", unique = true, nullable = false)
	public Integer getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "issueDate", length = 19)
	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prescription")
	public Set<PrescriptionEntry> getPrescriptionentries() {
		return this.prescriptionentries;
	}

	public void setPrescriptionentries(
			Set<PrescriptionEntry> prescriptionentries) {
		this.prescriptionentries = prescriptionentries;
	}

}
