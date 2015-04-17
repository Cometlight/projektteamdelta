package at.itb13.oculus.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.ExaminationProtocolRO;
import at.itb13.oculus.technicalServices.util.LocalDateTimePersistenceConverter;

/**
 * Examinationprotocol generated by hbm2java
 */
@Entity
@Table(name = "examinationprotocol", catalog = "oculusdb")
public class ExaminationProtocol implements java.io.Serializable, ExaminationProtocolRO {

	private static final Logger _logger = LogManager.getLogger(ExaminationProtocol.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Integer examinationProtocolId;
	private Diagnosis diagnosis;
	private Patient patient;
	private User user;
	private LocalDateTime startProtocol;
	private LocalDateTime endProtocol;
	private String description;
	private Set<ExaminationResult> examinationresults = new HashSet<ExaminationResult>(
			0);
	private Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes = new HashSet<ExaminationProtocolServiceCode>(
			0);
	private Set<ReferralLetter> referralletters = new HashSet<ReferralLetter>(0);

	public ExaminationProtocol() {
	}

	public ExaminationProtocol(
			Diagnosis diagnosis,
			Patient patient,
			User user,
			LocalDateTime startProtocol,
			LocalDateTime endProtocol,
			String description,
			Set<ExaminationResult> examinationresults,
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes,
			Set<ReferralLetter> referralletters) {
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
	
	@Transient
	private static List<ExaminationProtocol> sortExaminationProtocolsByStartDate(
			Set<ExaminationProtocol> examinationProtocols) {
		
		return null;
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

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "startProtocol", length = 19)
	public LocalDateTime getStartProtocol() {
		return this.startProtocol;
	}

	public void setStartProtocol(LocalDateTime startProtocol) {
		this.startProtocol = startProtocol;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "endProtocol", length = 19)
	public LocalDateTime getEndProtocol() {
		return this.endProtocol;
	}

	public void setEndProtocol(LocalDateTime endProtocol) {
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
	public Set<ExaminationResult> getExaminationresults() {
		return this.examinationresults;
	}

	public void setExaminationresults(Set<ExaminationResult> examinationresults) {
		this.examinationresults = examinationresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<ExaminationProtocolServiceCode> getExaminationprotocolservicecodes() {
		return this.examinationprotocolservicecodes;
	}

	public void setExaminationprotocolservicecodes(
			Set<ExaminationProtocolServiceCode> examinationprotocolservicecodes) {
		this.examinationprotocolservicecodes = examinationprotocolservicecodes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<ReferralLetter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<ReferralLetter> referralletters) {
		this.referralletters = referralletters;
	}

}
