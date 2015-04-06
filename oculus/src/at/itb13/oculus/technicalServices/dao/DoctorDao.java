package at.itb13.oculus.technicalServices.dao;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.itb13.oculus.domain.Diagnosis;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.technicalServices.GenericDao;
import at.itb13.oculus.technicalServices.HibernateUtil;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 06.04.2015
 */
public class DoctorDao extends GenericDao<Doctor> {

	public DoctorDao() {
		super(Doctor.class);
	}
	
	public Set<Patient> loadPatients(Doctor doctor) {
		try {
			loadCollection(doctor, doctor.getPatients());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doctor.getPatients();
	}
	
	public Set<Diagnosis> loadDiagnoses(Doctor doctor) {
		try {
			loadCollection(doctor, doctor.getDiagnosises());// TODO: rename getDiagnosises to getDiagnoses ??
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doctor.getDiagnosises();
	}
	
	// TODO: Why does a doctor contain a list of "doctors"?
	// TODO: Why does a doctor can contain several queues and not only one? --> I, Daniel, have asked D. Griesa
}
