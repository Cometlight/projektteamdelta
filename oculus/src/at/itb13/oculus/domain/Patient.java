package at.itb13.oculus.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.technicalServices.util.LocalDatePersistenceConverter;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
@Entity
@Table(name = "patient", catalog = "oculus_d")
public class Patient implements java.io.Serializable, PatientRO {
	public static final Integer SOCIAL_INSURANCE_NR_LENGTH = 10;
	
	private static final long serialVersionUID = 1L;
	private static final Logger _logger = LogManager.getLogger(Patient.class.getName());

	private Integer _patientId;
	private Doctor _doctor;
	private String _socialInsuranceNr;
	private String _firstName;
	private String _lastName;
	private LocalDate _birthDay;
	private Gender _gender;
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
	private Set<ReferralLetter> _referralletters = new HashSet<ReferralLetter>(0);
	private Set<ExaminationProtocol> _examinationprotocols = new HashSet<ExaminationProtocol>(0);
	
	public enum Gender {
		M, F
	}

	public Patient() { }

	public Patient(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, LocalDate birthDay, Gender gender, String street,
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
			String lastName, LocalDate birthDay, Gender gender, String street,
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
		_referralletters = referralletters;
		_examinationprotocols = examinationprotocols;
	}
	
	/**
	 * Checks if the provided insurance number is in a valid format.
	 * 
	 * @param socialInsuranceNr The social insurance number that should be checked.
	 * @return true, if the socialInsuranceNr is in a valid format.
	 */
	@Transient
	public static boolean isSocialInsuranceNrValid(String socialInsuranceNr) {
		String pattern = "^\\d{" + SOCIAL_INSURANCE_NR_LENGTH + "}$";
		return socialInsuranceNr != null && Pattern.matches(pattern, socialInsuranceNr);
	}
	
	@Transient
	public void addCalendarEvent(CalendarEvent event){
		_calendarevents.add(event);
	}

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

	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name = "birthDay", length = 10)
	public LocalDate getBirthDay() {
		return _birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		_birthDay = birthDay;
	}

	@Enumerated (EnumType.STRING)
	@Column(name = "gender", nullable = false)
	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
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
