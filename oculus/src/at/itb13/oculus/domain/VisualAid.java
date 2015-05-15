package at.itb13.oculus.domain;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "visualaid", catalog = "oculus_d")
public class VisualAid implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(VisualAid.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _visualAidId;
	private Diagnosis _diagnosis;

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

}
