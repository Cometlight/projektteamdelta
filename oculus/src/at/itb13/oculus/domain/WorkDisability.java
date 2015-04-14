package at.itb13.oculus.domain;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.util.LocalDatePersistenceConverter;

/**
 * Workdisability generated by hbm2java
 */
@Entity
@Table(name = "workdisability", catalog = "oculusdb")
public class WorkDisability implements java.io.Serializable {

	private static final Logger _logger = LogManager.getLogger(WorkDisability.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Integer workDisabilityId;
	private Diagnosis diagnosis;
	private LocalDate startDisability;
	private LocalDate endDisability;
	private String reason;
	private Boolean bedRest;

	public WorkDisability() {
	}

	public WorkDisability(Diagnosis diagnosis, LocalDate startDisability,
			LocalDate endDisability, String reason, Boolean bedRest) {
		this.diagnosis = diagnosis;
		this.startDisability = startDisability;
		this.endDisability = endDisability;
		this.reason = reason;
		this.bedRest = bedRest;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "workDisabilityId", unique = true, nullable = false)
	public Integer getWorkDisabilityId() {
		return this.workDisabilityId;
	}

	public void setWorkDisabilityId(Integer workDisabilityId) {
		this.workDisabilityId = workDisabilityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId")
	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name = "startDisability", length = 10)
	public LocalDate getStartDisability() {
		return this.startDisability;
	}

	public void setStartDisability(LocalDate startDisability) {
		this.startDisability = startDisability;
	}

	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name = "endDisability", length = 10)
	public LocalDate getEndDisability() {
		return this.endDisability;
	}

	public void setEndDisability(LocalDate endDisability) {
		this.endDisability = endDisability;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "bedRest")
	public Boolean getBedRest() {
		return this.bedRest;
	}

	public void setBedRest(Boolean bedRest) {
		this.bedRest = bedRest;
	}

}