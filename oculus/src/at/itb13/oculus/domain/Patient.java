package at.itb13.oculus.domain;

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
	private Set<Calendarevent> calendarevents = new HashSet<Calendarevent>(0);
	private Set<Prescription> prescriptions = new HashSet<Prescription>(0);
	private Set<Queue> queues = new HashSet<Queue>(0);
	private Set<Referralletter> referralletters = new HashSet<Referralletter>(0);
	private Set<Examinationprotocol> examinationprotocols = new HashSet<Examinationprotocol>(
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
			Set<Calendarevent> calendarevents, Set<Prescription> prescriptions,
			Set<Queue> queues, Set<Referralletter> referralletters,
			Set<Examinationprotocol> examinationprotocols) {
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

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getSocialInsuranceNr() {
		return this.socialInsuranceNr;
	}

	public void setSocialInsuranceNr(String socialInsuranceNr) {
		this.socialInsuranceNr = socialInsuranceNr;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryIsoCode() {
		return this.countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAllergy() {
		return this.allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getChildhoodAilments() {
		return this.childhoodAilments;
	}

	public void setChildhoodAilments(String childhoodAilments) {
		this.childhoodAilments = childhoodAilments;
	}

	public String getMedicineIntolerance() {
		return this.medicineIntolerance;
	}

	public void setMedicineIntolerance(String medicineIntolerance) {
		this.medicineIntolerance = medicineIntolerance;
	}

	public Set<Calendarevent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<Calendarevent> calendarevents) {
		this.calendarevents = calendarevents;
	}

	public Set<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Set<Queue> getQueues() {
		return this.queues;
	}

	public void setQueues(Set<Queue> queues) {
		this.queues = queues;
	}

	public Set<Referralletter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<Referralletter> referralletters) {
		this.referralletters = referralletters;
	}

	public Set<Examinationprotocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<Examinationprotocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
