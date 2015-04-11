package at.itb13.oculus.domain;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "patient", catalog = "oculusdb")
public class Patient implements java.io.Serializable {

	//TODO What should be logged in this class?
	private static final Logger _logger = LogManager.getLogger(Patient.class
			.getName());

	private Integer _patientId;
	private Doctor _doctor;
	private String _socialInsuranceNr;
	private String _firstName;
	private String _lastName;
	private Date _birthDay;
	private String _gender;
	private String _street;
	private String _postalCode;
	private String _city;
	private String _countryIsoCode;
	private String _phone;
	private String _email;
	private String _allergy;
	private String _childhoodAilments;
	private String _medicineIntolerance;
	private Set<CalendarEvent> _calendarevents = new HashSet<CalendarEvent>(0);
	private Set<Prescription> _prescriptions = new HashSet<Prescription>(0);
	//TODO Are queues for patient necessary?
	// private Set<Queue> _queues = new HashSet<Queue>(0);
	private Set<ReferralLetter> _referralletters = new HashSet<ReferralLetter>(
			0);
	private Set<ExaminationProtocol> _examinationprotocols = new HashSet<ExaminationProtocol>(
			0);

	public Patient() {
	}

// TODO Is this constructor still necessary?
//	public Patient(String firstName, String lastName, String gender) {
//		_firstName = firstName;
//		_lastName = lastName;
//		_gender = gender;
//	}

	public Patient(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, Date birthDay, String gender, String street,
			String postalCode, String city, String countryIsoCode,
			String phone, String email) {
		
		if (socialInsuranceNr.isEmpty()) {
			socialInsuranceNr = null;
		}
		if (street.isEmpty()) {
			street = null;
		}
		if (postalCode.isEmpty()) {
			postalCode = null;
		}
		if (city.isEmpty()) {
			city = null;
		}
		if (countryIsoCode.isEmpty()) {
			countryIsoCode = null;
		}
		if (phone.isEmpty()) {
			phone = null;
		}
		if (email.isEmpty()) {
			email = null;
		}
		
		if (doctor == null && socialInsuranceNr == null && birthDay == null
				&& street == null && postalCode == null && city == null
				&& countryIsoCode == null && phone == null && email == null) {
			
			_firstName = firstName;
			_lastName = lastName;
			_gender = gender;
			
		} else {
			_doctor = doctor;
			_socialInsuranceNr = socialInsuranceNr;
			_firstName = firstName;
			_lastName = lastName;
			_birthDay = birthDay;
			_gender = gender;
			_street = street;
			_postalCode = postalCode;
			_city = city;
			_countryIsoCode = countryIsoCode;
			_phone = phone;
			_email = email;
		}
	}

	public Patient(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, Date birthDay, String gender, String street,
			String postalCode, String city, String countryIsoCode,
			String phone, String email, String allergy,
			String childhoodAilments, String medicineIntolerance,
			Set<CalendarEvent> calendarevents, Set<Prescription> prescriptions,
			Set<Queue> queues, Set<ReferralLetter> referralletters,
			Set<ExaminationProtocol> examinationprotocols) {
		_doctor = doctor;
		_socialInsuranceNr = socialInsuranceNr;
		_firstName = firstName;
		_lastName = lastName;
		_birthDay = birthDay;
		_gender = gender;
		_street = street;
		_postalCode = postalCode;
		_city = city;
		_countryIsoCode = countryIsoCode;
		_phone = phone;
		_email = email;
		_allergy = allergy;
		_childhoodAilments = childhoodAilments;
		_medicineIntolerance = medicineIntolerance;
		_calendarevents = calendarevents;
		_prescriptions = prescriptions;
		// _queues = queues;
		_referralletters = referralletters;
		_examinationprotocols = examinationprotocols;
	}

//	/**
//	 * 
//	 * @return
//	 */
//	// ################## TODO: Wieso nicht einfach den Konstruktor nehmen?
//	// Zumindest scheint mir sollte diese Funktion static sein, wenn sie n�tig
//	// ist.
//	public Patient buildPatient(Doctor doctor, String socialInsuranceNr,
//			String firstName, String lastName, Date birthday, String gender,
//			String street, String postalCode, String city,
//			String countryIsoCode, String phone, String email) {
//		Patient p = null;
//		if (socialInsuranceNr.isEmpty()) {
//			socialInsuranceNr = null;
//		}
//		if (street.isEmpty()) {
//			street = null;
//		}
//		if (postalCode.isEmpty()) {
//			postalCode = null;
//		}
//		if (city.isEmpty()) {
//			city = null;
//		}
//		if (countryIsoCode.isEmpty()) {
//			countryIsoCode = null;
//		}
//		if (phone.isEmpty()) {
//			phone = null;
//		}
//		if (email.isEmpty()) {
//			email = null;
//		}
//		if (doctor == null && socialInsuranceNr == null && birthday == null
//				&& street == null && postalCode == null && city == null
//				&& countryIsoCode == null && phone == null && email == null) {
//			p = new Patient(firstName, lastName, gender);
//		} else {
//			p = new Patient(doctor, socialInsuranceNr, firstName, lastName,
//					birthday, gender, street, postalCode, city, countryIsoCode,
//					phone, email);
//		}
//		return p;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "patientId", unique = true, nullable = false)
	public Integer getPatientId() {
		return _patientId;
	}

	public void setPatientId(Integer patientId) {
		_patientId = patientId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	public Doctor getDoctor() {
		return _doctor;
	}

	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	@Column(name = "socialInsuranceNr", length = 10)
	public String getSocialInsuranceNr() {
		return _socialInsuranceNr;
	}

	public void setSocialInsuranceNr(String socialInsuranceNr) {
		_socialInsuranceNr = socialInsuranceNr;
	}

	@Column(name = "firstName", nullable = false, length = 30)
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 30)
	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDay", length = 10)
	public Date getBirthDay() {
		return _birthDay;
	}

	public void setBirthDay(Date birthDay) {
		_birthDay = birthDay;
	}

	@Column(name = "gender", nullable = false, length = 2)
	public String getGender() {
		return _gender;
	}

	public void setGender(String gender) {
		_gender = gender;
	}

	@Column(name = "street")
	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		_street = street;
	}

	@Column(name = "postalCode", length = 20)
	public String getPostalCode() {
		return _postalCode;
	}

	public void setPostalCode(String postalCode) {
		_postalCode = postalCode;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	@Column(name = "countryIsoCode", length = 2)
	public String getCountryIsoCode() {
		return _countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		_countryIsoCode = countryIsoCode;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	@Column(name = "allergy", length = 65535)
	public String getAllergy() {
		return _allergy;
	}

	public void setAllergy(String allergy) {
		_allergy = allergy;
	}

	@Column(name = "childhoodAilments", length = 65535)
	public String getChildhoodAilments() {
		return _childhoodAilments;
	}

	public void setChildhoodAilments(String childhoodAilments) {
		_childhoodAilments = childhoodAilments;
	}

	@Column(name = "medicineIntolerance", length = 65535)
	public String getMedicineIntolerance() {
		return _medicineIntolerance;
	}

	public void setMedicineIntolerance(String medicineIntolerance) {
		_medicineIntolerance = medicineIntolerance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<CalendarEvent> getCalendarevents() {
		return _calendarevents;
	}

	public void setCalendarevents(Set<CalendarEvent> calendarevents) {
		_calendarevents = calendarevents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Prescription> getPrescriptions() {
		return _prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		_prescriptions = prescriptions;
	}

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	// public Set<Queue> getQueues() {
	// return _queues;
	// }
	//
	// public void setQueues(Set<Queue> queues) {
	// _queues = queues;
	// }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<ReferralLetter> getReferralletters() {
		return _referralletters;
	}

	public void setReferralletters(Set<ReferralLetter> referralletters) {
		_referralletters = referralletters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<ExaminationProtocol> getExaminationprotocols() {
		return _examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<ExaminationProtocol> examinationprotocols) {
		_examinationprotocols = examinationprotocols;
	}

}
