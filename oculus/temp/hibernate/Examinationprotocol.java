// default package
// Generated 14.04.2015 20:32:57 by Hibernate Tools 4.3.1

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

/**
 * Examinationprotocol generated by hbm2java
 */
@Entity
@Table(name = "examinationprotocol", catalog = "oculusdb")
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationProtocolId", unique = true, nullable = false)
	public Integer getExaminationProtocolId() {
		return this.examinationProtocolId;
	}

	public void setExaminationProtocolId(Integer examinationProtocolId) {
		this.examinationProtocolId = examinationProtocolId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId")
	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startProtocol", length = 19)
	public Date getStartProtocol() {
		return this.startProtocol;
	}

	public void setStartProtocol(Date startProtocol) {
		this.startProtocol = startProtocol;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endProtocol", length = 19)
	public Date getEndProtocol() {
		return this.endProtocol;
	}

	public void setEndProtocol(Date endProtocol) {
		this.endProtocol = endProtocol;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<Examinationresult> getExaminationresults() {
		return this.examinationresults;
	}

	public void setExaminationresults(Set<Examinationresult> examinationresults) {
		this.examinationresults = examinationresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<Examinationprotocolservicecode> getExaminationprotocolservicecodes() {
		return this.examinationprotocolservicecodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<Examinationprotocolservicecode> examinationprotocolservicecodes) {
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<Referralletter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<Referralletter> referralletters) {
		this.referralletters = referralletters;
	}

}
