package at.itb13.oculus.domain.readonlyinterfaces;

import java.time.LocalDate;
import java.util.Set;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.ExaminationProtocol;
import at.itb13.oculus.domain.Prescription;
import at.itb13.oculus.domain.ReferralLetter;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
public interface PatientRO {
	Integer getPatientId();
	Doctor getDoctor();
	String getSocialInsuranceNr();
	String getFirstName();
	String getLastName();
	LocalDate getBirthDay();
	String getGender();
	String getStreet();
	String getPostalCode();
	String getCity();
	String getCountryIsoCode();
	String getPhone();
	String getEmail();
	String getAllergy();
	String getChildhoodAilments();
	String getMedicineIntolerance();
	Set<CalendarEvent> getCalendarevents();
	Set<Prescription> getPrescriptions();
	Set<ReferralLetter> getReferralletters();
	Set<ExaminationProtocol> getExaminationprotocols();
}
