package at.itb13.oculus.domain.readonlyinterfaces;

import java.util.Set;

import at.itb13.oculus.domain.Calendar;
import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.User;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 16.04.2015
 */
public interface DoctorRO {
	Integer getDoctorId();
	Calendar getCalendar();
	Doctor getDoctorSubstitute();
	User getUser();
	Set<Diagnosis> getDiagnoses();
	Set<Patient> getPatients();
}
