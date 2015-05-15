package at.itb13.oculus.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.technicalServices.converter.LocalDatePersistenceConverter;
import at.itb13.oculus.technicalServices.converter.LocalDateTimePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "visualaid", catalog = "oculus_d")
public class VisualAid implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(VisualAid.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _visualAidId;
	private Diagnosis _diagnosis;
	private LocalDateTime _issueDate;
	private LocalDateTime _lastPrintDate;
	private String _description;
	private Double _dioptreLeft;
	private Double _dioptreRight;
	
	public VisualAid() {
	}

	public VisualAid(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "visualAidId", unique = true, nullable = false)
	public Integer getVisualAidId() {
		return _visualAidId;
	}

	public void setVisualAidId(Integer visualAidId) {
		_visualAidId = visualAidId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId")
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "issueDate", length = 19)
	public LocalDateTime getIssueDate() {
		return _issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		_issueDate = issueDate;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "lastPrint", length = 19)
	public LocalDateTime getLastPrintDate() {
		return _lastPrintDate;
	}

	public void setLastPrintDate(LocalDateTime lastPrintDate) {
		_lastPrintDate = lastPrintDate;
	}
	
	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@Column(name = "dioptreLeft", precision=4, scale=2)
	public Double getDioptreLeft() {
		return _dioptreLeft;
	}

	public void setDioptreLeft(Double dioptreLeft) {
		_dioptreLeft = dioptreLeft;
	}

	@Column(name = "dioptreRight", precision=4, scale=2)
	public Double getDioptreRight() {
		return _dioptreRight;
	}

	public void setDioptreRight(Double dioptreRight) {
		_dioptreRight = dioptreRight;
	}
	
	
}
