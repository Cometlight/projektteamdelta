package at.itb13.teamF.adapter;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.domain.Prescription;
import at.itb13.teamF.interfaces.IAdapter;
import at.oculus.teamf.domain.entity.exception.CouldNotAddExaminationProtocol;
import at.oculus.teamf.domain.entity.exception.CouldNotGetCalendarEventsException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetDiagnoseException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetExaminationProtolException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetExaminationResultException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetMedicineException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetPrescriptionException;
import at.oculus.teamf.domain.entity.exception.CouldNotGetVisualAidException;
import at.oculus.teamf.domain.entity.interfaces.ICalendarEvent;
import at.oculus.teamf.domain.entity.interfaces.IDiagnosis;
import at.oculus.teamf.domain.entity.interfaces.IDoctor;
import at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol;
import at.oculus.teamf.domain.entity.interfaces.IExaminationResult;
import at.oculus.teamf.domain.entity.interfaces.IMedicine;
import at.oculus.teamf.domain.entity.interfaces.IPatient;
import at.oculus.teamf.domain.entity.interfaces.IPrescription;
import at.oculus.teamf.domain.entity.interfaces.IVisualAid;

/**
 * Adapter for the IPatient interface from TeamF.
 *
 * @author Florin Metzler
 * @since 18.05.2015
 */
public class PatientAdapter implements IPatient, IAdapter {
	private Patient _patient;
	
	public PatientAdapter() { }
	
	public PatientAdapter(Patient patient) {
		_patient =  patient;
	}
	
	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		return _patient;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getId()
	 */
	@Override
	public int getId() {
		return _patient.getPatientId();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setId(int)
	 */
	@Override
	public void setId(int patientID) {
		_patient.setPatientId(patientID);

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return _patient.getFirstName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		_patient.setFirstName(firstName);

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getLastName()
	 */
	@Override
	public String getLastName() {
		return _patient.getLastName();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setLastName(java.lang.String)
	 */
	@Override
	public IPatient setLastName(String lastName) {
		_patient.setLastName(lastName);
		return this;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getGender()
	 */
	@Override
	public String getGender() {
		return _patient.getGender().toString();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setGender(java.lang.String)
	 */
	@Override
	public void setGender(String gender) {
		String male = "M";
		String female = "F";
		if(male.equalsIgnoreCase(gender.toString())){
			_patient.setGender(Gender.M);
		}
		if(female.equalsIgnoreCase(gender.toString())){
			_patient.setGender(Gender.F);
		}
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getSocialInsuranceNr()
	 */
	@Override
	public String getSocialInsuranceNr() {
		return _patient.getSocialInsuranceNr();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setSocialInsuranceNr(java.lang.String)
	 */
	@Override
	public void setSocialInsuranceNr(String svn) {
		_patient.setSocialInsuranceNr(svn);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getIDoctor()
	 */
	@Override
	public IDoctor getIDoctor() {
		return (IDoctor) _patient.getDoctor();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setIDoctor(at.oculus.teamf.domain.entity.interfaces.IDoctor)
	 */
	@Override
	public void setIDoctor(IDoctor idoctor) {
		_patient.setDoctor((Doctor) idoctor);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCalendarEvents()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ICalendarEvent> getCalendarEvents()
			throws CouldNotGetCalendarEventsException {
		Set<CalendarEvent> set = _patient.getCalendarevents();
		Collection<ICalendarEvent> coll = new HashSet<>();
		coll.addAll((Collection<? extends ICalendarEvent>) set);
		return coll;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCalendarEvents(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setCalendarEvents(Collection<ICalendarEvent> calendarEvents) {
		Set<CalendarEvent> events = new HashSet<>((Set<? extends CalendarEvent>) calendarEvents);
		_patient.setCalendarevents(events);

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getBirthDay()
	 */
	@Override
	public Date getBirthDay() {
		LocalDate localDate = _patient.getDateOfBirth();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setBirthDay(java.util.Date)
	 */
	@Override
	public void setBirthDay(Date birthDay) {
		LocalDate date = birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		_patient.setDateOfBirth(date);

	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getStreet()
	 */
	@Override
	public String getStreet() {
		return _patient.getStreet();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setStreet(java.lang.String)
	 */
	@Override
	public void setStreet(String street) {
		_patient.setStreet(street);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPostalCode()
	 */
	@Override
	public String getPostalCode() {
		return _patient.getPostalCode();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setPostalCode(java.lang.String)
	 */
	@Override
	public void setPostalCode(String postalCode) {
		_patient.setPostalCode(postalCode);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCity()
	 */
	@Override
	public String getCity() {
		return _patient.getCity();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCity(java.lang.String)
	 */
	@Override
	public void setCity(String city) {
		_patient.setCity(city);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCountryIsoCode()
	 */
	@Override
	public String getCountryIsoCode() {
		return _patient.getCountryIsoCode();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCountryIsoCode(java.lang.String)
	 */
	@Override
	public void setCountryIsoCode(String countryIsoCode) {
		_patient.setCountryIsoCode(countryIsoCode);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPhone()
	 */
	@Override
	public String getPhone() {
		return _patient.getPhone();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) {
		_patient.setPhone(phone);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getEmail()
	 */
	@Override
	public String getEmail() {
		return _patient.getEmail();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		_patient.setEmail(email);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getAllergy()
	 */
	@Override
	public String getAllergy() {
		return _patient.getAllergy();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setAllergy(java.lang.String)
	 */
	@Override
	public void setAllergy(String allergy) {
		_patient.setAllergy(allergy);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getChildhoodAilments()
	 */
	@Override
	public String getChildhoodAilments() {
		return _patient.getChildhoodAilments();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setChildhoodAilments(java.lang.String)
	 */
	@Override
	public void setChildhoodAilments(String childhoodAilments) {
		_patient.setChildhoodAilments(childhoodAilments);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getMedicineIntolerance()
	 */
	@Override
	public String getMedicineIntolerance() {
		return _patient.getMedicineIntolerance();
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setMedicineIntolerance(java.lang.String)
	 */
	@Override
	public void setMedicineIntolerance(String medicineIntolerance) {
		_patient.setMedicineIntolerance(medicineIntolerance);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getExaminationProtocol()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<IExaminationProtocol> getExaminationProtocol()
			throws CouldNotGetExaminationProtolException {
		Set<ExaminationProtocol> set = _patient.getExaminationprotocols();
		Collection<IExaminationProtocol> coll = new HashSet<>();
		coll.addAll((Collection<? extends IExaminationProtocol>) set);
		return coll;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setExaminationProtocol(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setExaminationProtocol(
			Collection<IExaminationProtocol> protocols) {
		Set<ExaminationProtocol> examinationProtocols = new HashSet<>((Set<? extends ExaminationProtocol>) protocols);
		_patient.setExaminationprotocols(examinationProtocols);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#addExaminationProtocol(at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol)
	 */
	@Override
	public void addExaminationProtocol(IExaminationProtocol examinationProtocol)
			throws CouldNotAddExaminationProtocol {
		Collection<ExaminationProtocol> coll = _patient.getExaminationprotocols();
		coll.add((ExaminationProtocol) examinationProtocol);
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getDiagnoses()
	 */
	@Override
	public Collection<IDiagnosis> getDiagnoses()
			throws CouldNotGetDiagnoseException {
		Collection<IDiagnosis> results = new HashSet<>();
		Collection<ExaminationProtocol> coll = _patient.getExaminationprotocols();
		for(ExaminationProtocol entry : coll){
			results.add((IDiagnosis) entry.getDiagnosis());
		}
		return results;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getMedicine()
	 */
	@Override
	public Collection<IMedicine> getMedicine()
			throws CouldNotGetMedicineException {
		Collection<IMedicine> results = new HashSet<>();
		Collection<IDiagnosis> diagnosis = new HashSet<>();
		Collection<ExaminationProtocol> coll = _patient.getExaminationprotocols();
		for(ExaminationProtocol entry : coll){
			diagnosis.add((IDiagnosis) entry.getDiagnosis());
		}
		for(IDiagnosis entry : diagnosis){
			results = entry.getMedicine();
		}
		return results;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getExaminationResults()
	 */
	@Override
	public Collection<IExaminationResult> getExaminationResults()
			throws CouldNotGetExaminationResultException {
		Collection<IExaminationResult> results = new HashSet<>();
		Collection<ExaminationProtocol> coll = _patient.getExaminationprotocols();
		for(ExaminationProtocol entry : coll){
			results.add((IExaminationResult) entry.getExaminationResults());
		}
		return results;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPrescriptions()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<IPrescription> getPrescriptions()
			throws CouldNotGetPrescriptionException {
		Set<Prescription> set = _patient.getPrescriptions();
		Collection<IPrescription> coll = new HashSet<>();
		coll.addAll((Collection<? extends IPrescription>) set);
		return coll;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getVisualAid()
	 */
	@Override
	public Collection<IVisualAid> getVisualAid()
			throws CouldNotGetVisualAidException {
		Collection<IVisualAid> results = new HashSet<>();
		Collection<IDiagnosis> diagnosis = new HashSet<>();
		Collection<ExaminationProtocol> coll = _patient.getExaminationprotocols();
		for(ExaminationProtocol entry : coll){
			diagnosis.add((IDiagnosis) entry.getDiagnosis());
		}
		for(IDiagnosis entry : diagnosis){
			results = entry.getVisualAid();
		}
		return results;
	}


}
