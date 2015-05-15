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
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(Prescription.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _prescriptionId;
	private Patient _patient;
	private Date _issueDate;
	private Set<PrescriptionEntry> _prescriptionentries = new HashSet<PrescriptionEntry>(0);

	public Prescription() {
	}

	public Prescription(Patient patient, Date issueDate,
			Set<PrescriptionEntry> prescriptionentries) {
		_patient = patient;
		_issueDate = issueDate;
		_prescriptionentries = prescriptionentries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prescriptionId", unique = true, nullable = false)
	public Integer getPrescriptionId() {
		return _prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		_prescriptionId = prescriptionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "issueDate", length = 19)
	public Date getIssueDate() {
		return _issueDate;
	}

	public void setIssueDate(Date issueDate) {
		_issueDate = issueDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prescription")
	public Set<PrescriptionEntry> getPrescriptionentries() {
		return _prescriptionentries;
	}

	public void setPrescriptionentries(
			Set<PrescriptionEntry> prescriptionentries) {
		_prescriptionentries = prescriptionentries;
	}

}
