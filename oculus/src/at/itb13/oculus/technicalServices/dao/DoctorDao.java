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
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(doctor);	// TODO: Check if merge(), lock(), or sth. similar would be more appropriate for reattaching the object;
			Hibernate.initialize(doctor.getPatients());
			
			tx.commit();
			session.flush();	// TODO: Check if flus() is needed
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return doctor.getPatients();
	}

	public Set<Diagnosis> loadDiagnoses(Doctor doctor) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.update(doctor);	// TODO: Check if merge(), lock(), or sth. similar would be more appropriate for reattaching the object;
			Hibernate.initialize(doctor.getDiagnosises());	// TODO: rename getDiagnosises to getDiagnoses ??
			
			tx.commit();
			session.flush();	// TODO: Check if flush() is needed
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return doctor.getDiagnosises();
	}
	
	// TODO: Why does a doctor contain a list of "doctors"?
	// TODO: Why does a doctor can contain several queues and not only one? --> I, Daniel, have asked D. Griesa
}
