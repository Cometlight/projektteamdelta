package at.itb13.oculus.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
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
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.ExaminationProtocolRO;
import at.itb13.oculus.technicalServices.converter.LocalDateTimePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "examinationprotocol", catalog = "oculus_d")
public class ExaminationProtocol implements java.io.Serializable, ExaminationProtocolRO {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(ExaminationProtocol.class.getName());
	private static final long serialVersionUID = 1L;
	
	private Integer _examinationProtocolId;
	private Diagnosis _diagnosis;
	private Patient _patient;
	private User _user;
	private LocalDateTime _startProtocol;
	private LocalDateTime _endProtocol;
	private String _description;
	private Set<ExaminationResult> _examinationResults = new HashSet<ExaminationResult>(0);
	private Set<ExaminationProtocolServiceCode> _examinationProtocolServiceCodes = new HashSet<ExaminationProtocolServiceCode>(0);
	private Set<ReferralLetter> _referralLetters = new HashSet<ReferralLetter>(0);

	public ExaminationProtocol() { }

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
		_diagnosis = diagnosis;
		_patient = patient;
		_user = user;
		_startProtocol = startProtocol;
		_endProtocol = endProtocol;
		_description = description;
		_examinationResults = examinationresults;
		_examinationProtocolServiceCodes = examinationprotocolservicecodes;
		_referralLetters = referralletters;
	}
	
	/**
	 * Gives back a sorted Linked List<ExaminationProtocols>, the newest ExaminationProtocol is the first Element.
	 * 
	 * @param Set <ExaminationProtocol> examinationProtocols
	 * @return Linked List <ExaminationProtocol>
	 */
	@Transient
	public static List<ExaminationProtocol> sortExaminationProtocolsByStartDate(
			Set<ExaminationProtocol> examinationProtocols) {
			List<ExaminationProtocol> unsortedExaminationProtocols = new LinkedList<ExaminationProtocol>(examinationProtocols);
			List<ExaminationProtocol> sortedExaminationProtocols = new LinkedList<ExaminationProtocol>();
			ExaminationProtocol epMax = null;
			while (!unsortedExaminationProtocols.isEmpty()){
				epMax = unsortedExaminationProtocols.get(0);
				for (ExaminationProtocol ep:unsortedExaminationProtocols){
					if (epMax.getStartProtocol().isBefore(ep.getStartProtocol())){
						epMax = ep;
					}
				}
				sortedExaminationProtocols.add(epMax);
				unsortedExaminationProtocols.remove(epMax);
			}
			// TODO: could be made more efficient; see CalendarEvent.sortCalendarEventsByStartDate
		return sortedExaminationProtocols;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "examinationProtocolId", unique = true, nullable = false)
	public Integer getExaminationProtocolId() {
		return _examinationProtocolId;
	}

	public void setExaminationProtocolId(Integer examinationProtocolId) {
		_examinationProtocolId = examinationProtocolId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosisId")
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "startProtocol", length = 19)
	public LocalDateTime getStartProtocol() {
		return _startProtocol;
	}

	public void setStartProtocol(LocalDateTime startProtocol) {
		_startProtocol = startProtocol;
	}

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "endProtocol", length = 19)
	public LocalDateTime getEndProtocol() {
		return _endProtocol;
	}

	public void setEndProtocol(LocalDateTime endProtocol) {
		_endProtocol = endProtocol;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<ExaminationResult> getExaminationResults() {
		return _examinationResults;
	}

	public void setExaminationResults(Set<ExaminationResult> examinationResults) {
		_examinationResults = examinationResults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<ExaminationProtocolServiceCode> getExaminationProtocolServiceCodes() {
		return _examinationProtocolServiceCodes;
	}

	public void setExaminationProtocolServiceCodes(Set<ExaminationProtocolServiceCode> examinationProtocolServiceCodes) {
		_examinationProtocolServiceCodes = examinationProtocolServiceCodes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationprotocol")
	public Set<ReferralLetter> getReferralLetters() {
		return _referralLetters;
	}

	public void setReferralLetters(Set<ReferralLetter> referralLetters) {
		_referralLetters = referralLetters;
	}

}
