// default package
// Generated 03.04.2015 15:26:50 by Hibernate Tools 4.3.1

/**
 * Referralletter generated by hbm2java
 */
public class Referralletter implements java.io.Serializable {

	private Integer referralLetterId;
	private Examinationprotocol examinationprotocol;
	private Patient patient;
	private String externalInstitute;
	private String reason;

	public Referralletter() {
	}

	public Referralletter(String externalInstitute, String reason) {
		this.externalInstitute = externalInstitute;
		this.reason = reason;
	}

	public Referralletter(Examinationprotocol examinationprotocol,
			Patient patient, String externalInstitute, String reason) {
		this.examinationprotocol = examinationprotocol;
		this.patient = patient;
		this.externalInstitute = externalInstitute;
		this.reason = reason;
	}

	public Integer getReferralLetterId() {
		return this.referralLetterId;
	}

	public void setReferralLetterId(Integer referralLetterId) {
		this.referralLetterId = referralLetterId;
	}

	public Examinationprotocol getExaminationprotocol() {
		return this.examinationprotocol;
	}

	public void setExaminationprotocol(Examinationprotocol examinationprotocol) {
		this.examinationprotocol = examinationprotocol;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getExternalInstitute() {
		return this.externalInstitute;
	}

	public void setExternalInstitute(String externalInstitute) {
		this.externalInstitute = externalInstitute;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}