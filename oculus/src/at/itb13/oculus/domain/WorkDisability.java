package at.itb13.oculus.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.converter.LocalDatePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "workdisability", catalog = "oculus_d")
public class WorkDisability implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(WorkDisability.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _workDisabilityId;
	private Diagnosis _diagnosis;
	private LocalDate _startDisability;
	private LocalDate _endDisability;
	private String _reason;
	private Boolean _bedRest;

	public WorkDisability() {
	}

	public WorkDisability(Diagnosis diagnosis, LocalDate startDisability,
			LocalDate endDisability, String reason, Boolean bedRest) {
		_diagnosis = diagnosis;
		_startDisability = startDisability;
		_endDisability = endDisability;
		_reason = reason;
		_bedRest = bedRest;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "workDisabilityId", unique = true, nullable = false)
	public Integer getWorkDisabilityId() {
		return _workDisabilityId;
	}

	public void setWorkDisabilityId(Integer workDisabilityId) {
		_workDisabilityId = workDisabilityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId")
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name = "startDisability", length = 10)
	public LocalDate getStartDisability() {
		return _startDisability;
	}

	public void setStartDisability(LocalDate startDisability) {
		_startDisability = startDisability;
	}

	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name = "endDisability", length = 10)
	public LocalDate getEndDisability() {
		return _endDisability;
	}

	public void setEndDisability(LocalDate endDisability) {
		_endDisability = endDisability;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return _reason;
	}

	public void setReason(String reason) {
		_reason = reason;
	}

	@Column(name = "bedRest")
	public Boolean getBedRest() {
		return _bedRest;
	}

	public void setBedRest(Boolean bedRest) {
		_bedRest = bedRest;
	}

}
