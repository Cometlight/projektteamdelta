package at.itb13.oculus.domain;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import at.itb13.oculus.technicalServices.ReloaderOLD_TODELETE;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 11.04.2015
 */
public class QueueEntry {
	
	private static final Logger _logger = LogManager.getLogger(QueueEntry.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Integer _queueEntryId;
	private Patient _patient;
	private Date _arrivalTime;
	
	public QueueEntry() { }
	
	public QueueEntry(Integer queueEntryId, Patient patient, Date arrivalTime) {
		_queueEntryId = queueEntryId;
		_patient = patient;
		_arrivalTime = arrivalTime;
	}
	
	/**
	 * @return the queueEntryId
	 */
	public Integer getQueueEntryId() {
		return _queueEntryId;
	}
	
	/**
	 * @param queueEntryId the queueEntryId to set
	 */
	public void setQueueEntryId(Integer queueEntryId) {
		_queueEntryId = queueEntryId;
	}
	
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
//		if(!Reloader.getInstance().isLoaded(_patient)) { TODO: DELETE
//			_patient = Reloader.getInstance().reload(Patient.class, _patient.getPatientId());
//		}
		return _patient;
	}
	
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		_patient = patient;
	}
	
	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return _arrivalTime;
	}
	
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		_arrivalTime = arrivalTime;
	}
}