package at.itb13.teamF.adapter;

import java.util.Collection;
import java.util.Date;

import at.itb13.oculus.domain.Patient;
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
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 18.05.2015
 * 
 */
public class PatientAdapter implements IAdapter, IPatient{
	Patient _patient;

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getId()
	 */
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setId(int)
	 */
	@Override
	public void setId(int patientID) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getFirstName()
	 */
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getLastName()
	 */
	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setLastName(java.lang.String)
	 */
	@Override
	public IPatient setLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getGender()
	 */
	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setGender(java.lang.String)
	 */
	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getSocialInsuranceNr()
	 */
	@Override
	public String getSocialInsuranceNr() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setSocialInsuranceNr(java.lang.String)
	 */
	@Override
	public void setSocialInsuranceNr(String svn) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getIDoctor()
	 */
	@Override
	public IDoctor getIDoctor() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setIDoctor(at.oculus.teamf.domain.entity.interfaces.IDoctor)
	 */
	@Override
	public void setIDoctor(IDoctor idoctor) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCalendarEvents()
	 */
	@Override
	public Collection<ICalendarEvent> getCalendarEvents()
			throws CouldNotGetCalendarEventsException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCalendarEvents(java.util.Collection)
	 */
	@Override
	public void setCalendarEvents(Collection<ICalendarEvent> calendarEvents) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getBirthDay()
	 */
	@Override
	public Date getBirthDay() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setBirthDay(java.util.Date)
	 */
	@Override
	public void setBirthDay(Date birthDay) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getStreet()
	 */
	@Override
	public String getStreet() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setStreet(java.lang.String)
	 */
	@Override
	public void setStreet(String street) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPostalCode()
	 */
	@Override
	public String getPostalCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setPostalCode(java.lang.String)
	 */
	@Override
	public void setPostalCode(String postalCode) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCity()
	 */
	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCity(java.lang.String)
	 */
	@Override
	public void setCity(String city) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getCountryIsoCode()
	 */
	@Override
	public String getCountryIsoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setCountryIsoCode(java.lang.String)
	 */
	@Override
	public void setCountryIsoCode(String countryIsoCode) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPhone()
	 */
	@Override
	public String getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getEmail()
	 */
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getAllergy()
	 */
	@Override
	public String getAllergy() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setAllergy(java.lang.String)
	 */
	@Override
	public void setAllergy(String allergy) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getChildhoodAilments()
	 */
	@Override
	public String getChildhoodAilments() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setChildhoodAilments(java.lang.String)
	 */
	@Override
	public void setChildhoodAilments(String childhoodAilments) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getMedicineIntolerance()
	 */
	@Override
	public String getMedicineIntolerance() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setMedicineIntolerance(java.lang.String)
	 */
	@Override
	public void setMedicineIntolerance(String medicineIntolerance) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getExaminationProtocol()
	 */
	@Override
	public Collection<IExaminationProtocol> getExaminationProtocol()
			throws CouldNotGetExaminationProtolException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#setExaminationProtocol(java.util.Collection)
	 */
	@Override
	public void setExaminationProtocol(
			Collection<IExaminationProtocol> protocols) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#addExaminationProtocol(at.oculus.teamf.domain.entity.interfaces.IExaminationProtocol)
	 */
	@Override
	public void addExaminationProtocol(IExaminationProtocol examinationProtocol)
			throws CouldNotAddExaminationProtocol {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getDiagnoses()
	 */
	@Override
	public Collection<IDiagnosis> getDiagnoses()
			throws CouldNotGetDiagnoseException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getMedicine()
	 */
	@Override
	public Collection<IMedicine> getMedicine()
			throws CouldNotGetMedicineException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getExaminationResults()
	 */
	@Override
	public Collection<IExaminationResult> getExaminationResults()
			throws CouldNotGetExaminationResultException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getPrescriptions()
	 */
	@Override
	public Collection<IPrescription> getPrescriptions()
			throws CouldNotGetPrescriptionException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.oculus.teamf.domain.entity.interfaces.IPatient#getVisualAid()
	 */
	@Override
	public Collection<IVisualAid> getVisualAid()
			throws CouldNotGetVisualAidException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see at.itb13.teamF.interfaces.IAdapter#getDomainObject()
	 */
	@Override
	public Object getDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the _patient
	 */
	public Patient get_patient() {
		return _patient;
	}

	/**
	 * @param _patient the _patient to set
	 */
	public void set_patient(Patient _patient) {
		this._patient = _patient;
	}
	
}
