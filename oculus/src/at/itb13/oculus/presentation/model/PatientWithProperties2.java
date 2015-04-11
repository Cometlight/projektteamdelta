package at.itb13.oculus.presentation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.domain.Queue;
import at.itb13.oculus.domain.ReferralLetter;

/**
 * TODO: Is this class really a part of the domain layer? Or rather of the presentation layer? -- Daniel
 */
@Entity
@Table(name = "patient", catalog = "oculusdb")
public class PatientWithProperties2 implements java.io.Serializable {

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
	private Set<CalendarEvent> calendarevents = new HashSet<CalendarEvent>(0);
	private Set<Prescription> prescriptions = new HashSet<Prescription>(0);
	private Set<Queue> queues = new HashSet<Queue>(0);
	private Set<ReferralLetter> referralletters = new HashSet<ReferralLetter>(0);
	private Set<ExaminationProtocol> examinationprotocols = new HashSet<ExaminationProtocol>(0);
	private Patient _patient;

	public PatientWithProperties2() {
	}
	public PatientWithProperties2(Patient patient){
		_patient = patient;
		patientId = new SimpleIntegerProperty(patient.getPatientId());
	//	doctor = new SimpleObjectProperty(patient.getDoctor());
		socialInsuranceNr = new SimpleStringProperty(patient.getSocialInsuranceNr());
		firstName = new SimpleStringProperty(patient.getFirstName());
		lastName = new SimpleStringProperty(patient.getLastName());

	//	birthDay= new SimpleObjectProperty<>(patient.getBirthDay());
		gender = new SimpleStringProperty(patient.getGender());
		street = new SimpleStringProperty(patient.getStreet());
		postalCode = new SimpleStringProperty(patient.getPostalCode());
		city = new SimpleStringProperty(patient.getCity());
		countryIsoCode = new SimpleStringProperty(patient.getCountryIsoCode());
		phone = new SimpleStringProperty(patient.getPhone());
		email = new SimpleStringProperty(patient.getEmail());
		allergy = new SimpleStringProperty(patient.getAllergy());
		childhoodAilments = new SimpleStringProperty(patient.getChildhoodAilments());
		medicineIntolerance = new SimpleStringProperty(patient.getMedicineIntolerance());
		
	}

	public PatientWithProperties2(String firstName, String lastName, String gender) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.gender.set(gender);
	}

	public PatientWithProperties2(Doctor doctor, String socialInsuranceNr, String firstName,
			String lastName, Date birthDay, String gender, String street,
			String postalCode, String city, String countryIsoCode,
			String phone, String email, String allergy,
			String childhoodAilments, String medicineIntolerance,
			Set<CalendarEvent> calendarevents, Set<Prescription> prescriptions,
			Set<Queue> queues, Set<ReferralLetter> referralletters,
			Set<ExaminationProtocol> examinationprotocols) {
	//	this.doctor.set(doctor);
		this.socialInsuranceNr.set(socialInsuranceNr);
		this.firstName.set(firstName);
		this.lastName.set(lastName);
	//	this.birthDay.set(birthDay);
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


	public Integer getPatientId(){
		return _patient.getPatientId();
	}

	public void setPatientId(Integer patientId) {
		_patient.setPatientId(patientId);
		this.patientId.set(patientId);
	}

	public Doctor getDoctor() {
		return _patient.getDoctor();
	}

	public void setDoctor(Doctor doctor) {
		_patient.setDoctor(doctor);
		this.doctor.set(doctor);
	}

	
	public String getSocialInsuranceNr() {
		return _patient.getSocialInsuranceNr();
	}

	public void setSocialInsuranceNr(String socialInsuranceNr) {
		_patient.setSocialInsuranceNr(socialInsuranceNr);
		this.socialInsuranceNr.set(socialInsuranceNr);
	}
	
	 public StringProperty SocialInsuranceNrProperty() {
	        return socialInsuranceNr;
	    }

	public String getFirstName() {
		return _patient.getFirstName();
	}

	public void setFirstName(String firstName) {
		_patient.setFirstName(firstName);
		this.firstName.set(firstName);
	}
	
	 public StringProperty firstNameProperty() {
	        return firstName;
	    }

	
	public String getLastName() {
		return _patient.getLastName();
	}

	public void setLastName(String lastName) {
		_patient.setLastName(lastName);
		this.lastName.set(lastName);
	}
	 public StringProperty lastNameProperty() {
	        return lastName;
	    }

	public Date getBirthDay() {
		return _patient.getBirthDay();
	}

	public void setBirthDay(Date birthDay) {
		_patient.setBirthDay(birthDay);
		this.birthDay.set(birthDay);
	}
	
	 public ObjectProperty<Date> birthDayProperty() {
	        return birthDay;
	    }

	public String getGender() {
		return _patient.getGender();
	}

	public void setGender(String gender) {
		_patient.setGender(gender);
		this.gender.set(gender);
	}
	 public StringProperty genderProperty() {
	        return gender;
	    }

	public String getStreet() {
		return _patient.getStreet();
	}

	public void setStreet(String street) {
		_patient.setStreet(street);
		this.street.set(street);
	}
	 public StringProperty StreetProperty() {
	        return street;
	    }

	public String getPostalCode() {
		return _patient.getPostalCode();
	}

	public void setPostalCode(String postalCode) {
		_patient.setPostalCode(postalCode);
		this.postalCode.set(postalCode);
	}

	public String getCity() {
		return _patient.getCity();
	}

	public void setCity(String city) {
		_patient.setCity(city);
		this.city.set(city);
	}

	public String getCountryIsoCode() {
		return _patient.getCountryIsoCode();
	}

	public void setCountryIsoCode(String countryIsoCode) {
		_patient.setCountryIsoCode(countryIsoCode);
		this.countryIsoCode.set(countryIsoCode);
	}

	public String getPhone() {
		return _patient.getPhone();
	}

	public void setPhone(String phone) {
		_patient.setPhone(phone);
		this.phone.set(phone);
	}

	public String getEmail() {
		return _patient.getEmail();
	}

	public void setEmail(String email) {
		_patient.setEmail(email);
		this.email.set(email);
	}

	public String getAllergy() {
		return _patient.getAllergy();
	}

	public void setAllergy(String allergy) {
		_patient.setAllergy(allergy);
		this.allergy.set(allergy);
	}

	public String getChildhoodAilments() {
		return _patient.getChildhoodAilments();
	}

	public void setChildhoodAilments(String childhoodAilments) {
		_patient.setChildhoodAilments(childhoodAilments);
		this.childhoodAilments.set(childhoodAilments);
	}

	public String getMedicineIntolerance() {
		return _patient.getMedicineIntolerance();
	}

	public void setMedicineIntolerance(String medicineIntolerance) {
		_patient.setMedicineIntolerance(medicineIntolerance);
		this.medicineIntolerance.set(medicineIntolerance);
	}

	public Set<CalendarEvent> getCalendarevents() {
		return this.calendarevents;
	}

	public void setCalendarevents(Set<CalendarEvent> calendarevents) {
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

	public Set<ReferralLetter> getReferralletters() {
		return this.referralletters;
	}

	public void setReferralletters(Set<ReferralLetter> referralletters) {
		this.referralletters = referralletters;
	}

	public Set<ExaminationProtocol> getExaminationprotocols() {
		return this.examinationprotocols;
	}

	public void setExaminationprotocols(
			Set<ExaminationProtocol> examinationprotocols) {
		this.examinationprotocols = examinationprotocols;
	}

}
