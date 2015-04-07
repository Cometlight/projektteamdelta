package at.itb13.oculus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

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
 * TODO: Is this class really a part of the domain layer? Or rather of the presentation layer? -- Daniel
 */
@Entity
@Table(name = "patient", catalog = "oculusdb")
public class PatientWithProperties implements java.io.Serializable {

	private IntegerProperty patientId;
	private ObjectProperty<Doctor> doctor;
	private StringProperty socialInsuranceNr;
	private StringProperty firstName;
	private StringProperty lastName;
	private ObjectProperty<Date> birthDay;
	private StringProperty gender;
	private StringProperty street;
	private StringProperty postalCode;
	private StringProperty city;
	private StringProperty countryIsoCode;
	private StringProperty phone;
	private StringProperty email;
	private StringProperty allergy;
	private StringProperty childhoodAilments;
	private StringProperty medicineIntolerance;
	private Set<Calendarevent> calendarevents = new HashSet<Calendarevent>(0);
	private Set<Prescription> prescriptions = new HashSet<Prescription>(0);
	private Set<Queue> queues = new HashSet<Queue>(0);
	private Set<Referralletter> referralletters = new HashSet<Referralletter>(0);
	private Set<Examinationprotocol> examinationprotocols = new HashSet<Examinationprotocol>(
			0);

	public PatientWithProperties() {
	}

	public PatientWithProperties(String firstName, String lastName, String gender) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.gender.set(gender);
	}

	public PatientWithProperties(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, Date birthDay, String gender, String street,
			String postalCode, String city, String countryIsoCode,
			String phone, String email, String allergy,
			String childhoodAilments, String medicineIntolerance,
			Set<Calendarevent> calendarevents, Set<Prescription> prescriptions,
			Set<Queue> queues, Set<Referralletter> referralletters,
			Set<Examinationprotocol> examinationprotocols) {
		this.doctor.set(doctor);
		this.socialInsuranceNr.set(socialInsuranceNr);
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.birthDay.set(birthDay);
		this.gender.set(gender);
		this.street.set(street);
		this.postalCode.set(postalCode);
		this.city.set(city);
		this.countryIsoCode.set(countryIsoCode);
		this.phone.set(phone);
		this.email.set(email);
		this.allergy.set(allergy);
		this.childhoodAilments.set(childhoodAilments);
		this.medicineIntolerance.set(medicineIntolerance);
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
		return this.patientId.get();
	}

	public void setPatientId(Integer patientId) {
		this.patientId.set(patientId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	public Doctor getDoctor() {
		return this.doctor.get();
	}

	public void setDoctor(Doctor doctor) {
		this.doctor.set(doctor);
	}

	@Column(name = "socialInsuranceNr", length = 10)
	public String getSocialInsuranceNr() {
		return this.socialInsuranceNr.get();
	}

	public void setSocialInsuranceNr(String socialInsuranceNr) {
		this.socialInsuranceNr.set(socialInsuranceNr);
	}
	
	 public StringProperty SocialInsuranceNrProperty() {
	        return socialInsuranceNr;
	    }

	@Column(name = "firstName", nullable = false, length = 30)
	public String getFirstName() {
		return this.firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	 public StringProperty firstNameProperty() {
	        return firstName;
	    }

	@Column(name = "lastName", nullable = false, length = 30)
	public String getLastName() {
		return this.lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	 public StringProperty lastNameProperty() {
	        return lastName;
	    }

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDay", length = 10)
	public Date getBirthDay() {
		return this.birthDay.get();
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay.set(birthDay);
	}
	
	 public ObjectProperty<Date> birthDayProperty() {
	        return birthDay;
	    }

	@Column(name = "gender", nullable = false, length = 2)
	public String getGender() {
		return this.gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}
	 public StringProperty genderProperty() {
	        return gender;
	    }

	@Column(name = "street")
	public String getStreet() {
		return this.street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}
	 public StringProperty StreetProperty() {
	        return street;
	    }

	@Column(name = "postalCode", length = 20)
	public String getPostalCode() {
		return this.postalCode.get();
	}

	public void setPostalCode(String postalCode) {
		this.postalCode.set(postalCode);
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	@Column(name = "countryIsoCode", length = 2)
	public String getCountryIsoCode() {
		return this.countryIsoCode.get();
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode.set(countryIsoCode);
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	@Column(name = "allergy", length = 65535)
	public String getAllergy() {
		return this.allergy.get();
	}

	public void setAllergy(String allergy) {
		this.allergy.set(allergy);
	}

	@Column(name = "childhoodAilments", length = 65535)
	public String getChildhoodAilments() {
		return this.childhoodAilments.get();
	}

	public void setChildhoodAilments(String childhoodAilments) {
		this.childhoodAilments.set(childhoodAilments);
	}

	@Column(name = "medicineIntolerance", length = 65535)
	public String getMedicineIntolerance() {
		return this.medicineIntolerance.get();
	}

	public void setMedicineIntolerance(String medicineIntolerance) {
		this.medicineIntolerance.set(medicineIntolerance);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Calendarevent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<Calendarevent> calendarevents) {
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
	public Set<Referralletter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<Referralletter> referralletters) {
		this.referralletters = referralletters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Examinationprotocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<Examinationprotocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
