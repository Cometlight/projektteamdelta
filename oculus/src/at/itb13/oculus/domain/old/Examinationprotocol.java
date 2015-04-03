package at.itb13.oculus.domain.old;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 03.04.2015
 */
public class Examinationprotocol implements java.io.Serializable {

	private Integer examinationProtocolId;
	private Diagnosis diagnosis;
	private Patient patient;
	private User user;
	private Date startProtocol;
	private Date endProtocol;
	private String description;
	private Set<Examinationresult> examinationresults = new HashSet<Examinationresult>(
			0);
	private Set<Examinationprotocolservicecode> examinationprotocolservicecodes = new HashSet<Examinationprotocolservicecode>(
			0);
	private Set<Referralletter> referralletters = new HashSet<Referralletter>(0);

	public Examinationprotocol() {
	}

	public Examinationprotocol(
			Diagnosis diagnosis,
			Patient patient,
			User user,
			Date startProtocol,
			Date endProtocol,
			String description,
			Set<Examinationresult> examinationresults,
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes,
			Set<Referralletter> referralletters) {
		this.diagnosis = diagnosis;
		this.patient = patient;
		this.user = user;
		this.startProtocol = startProtocol;
		this.endProtocol = endProtocol;
		this.description = description;
		this.examinationresults = examinationresults;
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
		this.referralletters = referralletters;
	}

	public Integer getExaminationProtocolId() {
		return this.examinationProtocolId;
	}

	public void setExaminationProtocolId(Integer examinationProtocolId) {
		this.examinationProtocolId = examinationProtocolId;
	}

	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartProtocol() {
		return this.startProtocol;
	}

	public void setStartProtocol(Date startProtocol) {
		this.startProtocol = startProtocol;
	}

	public Date getEndProtocol() {
		return this.endProtocol;
	}

	public void setEndProtocol(Date endProtocol) {
		this.endProtocol = endProtocol;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Examinationresult> getExaminationresults() {
		return this.examinationresults;
	}

	public void setExaminationresults(Set<Examinationresult> examinationresults) {
		this.examinationresults = examinationresults;
	}

	public Set<Examinationprotocolservicecode> getExaminationprotocolservicecodes() {
		return this.examinationprotocolservicecodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

	public Set<Referralletter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<Referralletter> referralletters) {
		this.referralletters = referralletters;
	}

}
