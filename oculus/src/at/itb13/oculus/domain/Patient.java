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

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "patient", catalog = "oculusdb")
public class Patient implements java.io.Serializable {

	private Integer patientId;
	private Doctor doctor;
	private String socialInsuranceNr;
	private String firstName;
	private String lastName;
	private Date birthDay;
	private String gender;
	private String street;
	private String postalCode;
	private String city;
	private String countryIsoCode;
	private String phone;
	private String email;
	private String allergy;
	private String childhoodAilments;
	private String medicineIntolerance;
	private Set<CalenderEvent> calendarevents = new HashSet<CalenderEvent>(0);
	private Set<Prescription> prescriptions = new HashSet<Prescription>(0);
	private Set<Queue> queues = new HashSet<Queue>(0);
	private Set<ReferralLetter> referralletters = new HashSet<ReferralLetter>(0);
	private Set<ExaminationProtocol> examinationprotocols = new HashSet<ExaminationProtocol>(
			0);

	public Patient() {
	}

	public Patient(String firstName, String lastName, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Patient(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, Date birthDay, String gender, String street,
			String postalCode, String city, String countryIsoCode,
			String phone, String email, String allergy,
			String childhoodAilments, String medicineIntolerance,
			Set<CalenderEvent> calendarevents, Set<Prescription> prescriptions,
			Set<Queue> queues, Set<ReferralLetter> referralletters,
			Set<ExaminationProtocol> examinationprotocols) {
		this.doctor = doctor;
		this.socialInsuranceNr = socialInsuranceNr;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.gender = gender;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.countryIsoCode = countryIsoCode;
		this.phone = phone;
		this.email = email;
		this.allergy = allergy;
		this.childhoodAilments = childhoodAilments;
		this.medicineIntolerance = medicineIntolerance;
		this.calendarevents = calendarevents;
		this.prescriptions = prescriptions;
		this.queues = queues;
		this.referralletters = referralletters;
		this.examinationprotocols = examinationprotocols;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "patientId", unique = true, nullable = false)
	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Column(name = "socialInsuranceNr", length = 10)
	public String getSocialInsuranceNr() {
		return this.socialInsuranceNr;
	}

	public void setSocialInsuranceNr(String socialInsuranceNr) {
		this.socialInsuranceNr = socialInsuranceNr;
	}

	@Column(name = "firstName", nullable = false, length = 30)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 30)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDay", length = 10)
	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "gender", nullable = false, length = 2)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "street")
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "postalCode", length = 20)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "countryIsoCode", length = 2)
	public String getCountryIsoCode() {
		return this.countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "allergy", length = 65535)
	public String getAllergy() {
		return this.allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	@Column(name = "childhoodAilments", length = 65535)
	public String getChildhoodAilments() {
		return this.childhoodAilments;
	}

	public void setChildhoodAilments(String childhoodAilments) {
		this.childhoodAilments = childhoodAilments;
	}

	@Column(name = "medicineIntolerance", length = 65535)
	public String getMedicineIntolerance() {
		return this.medicineIntolerance;
	}

	public void setMedicineIntolerance(String medicineIntolerance) {
		this.medicineIntolerance = medicineIntolerance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<CalenderEvent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<CalenderEvent> calendarevents) {
		this.calendarevents = calendarevents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<ReferralLetter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<ReferralLetter> referralletters) {
		this.referralletters = referralletters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<ExaminationProtocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<ExaminationProtocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
