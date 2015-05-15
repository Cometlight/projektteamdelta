package at.itb13.oculus.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "referralletter", catalog = "oculus_d")
public class ReferralLetter implements java.io.Serializable {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(ReferralLetter.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _referralLetterId;
	private ExaminationProtocol _examinationprotocol;
	private Patient _patient;
	private String _externalInstitute;
	private String _reason;

	public ReferralLetter() { }

	public ReferralLetter(String externalInstitute, String reason) {
		_externalInstitute = externalInstitute;
		_reason = reason;
	}

	public ReferralLetter(ExaminationProtocol examinationprotocol,
			Patient patient, String externalInstitute, String reason) {
		_examinationprotocol = examinationprotocol;
		_patient = patient;
		_externalInstitute = externalInstitute;
		_reason = reason;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "referralLetterId", unique = true, nullable = false)
	public Integer getReferralLetterId() {
		return _referralLetterId;
	}

	public void setReferralLetterId(Integer referralLetterId) {
		_referralLetterId = referralLetterId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examinationProtocolId")
	public ExaminationProtocol getExaminationprotocol() {
		return _examinationprotocol;
	}

	public void setExaminationprotocol(ExaminationProtocol examinationprotocol) {
		_examinationprotocol = examinationprotocol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}

	@Column(name = "externalInstitute", nullable = false, length = 65535)
	public String getExternalInstitute() {
		return _externalInstitute;
	}

	public void setExternalInstitute(String externalInstitute) {
		_externalInstitute = externalInstitute;
	}

	@Column(name = "reason", nullable = false, length = 65535)
	public String getReason() {
		return _reason;
	}

	public void setReason(String reason) {
		_reason = reason;
	}

}
