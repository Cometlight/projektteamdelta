package at.itb13.oculus.domain.readonlyinterfaces;

import java.util.List;

import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Orthoptist;
import at.itb13.oculus.domain.QueueEntry;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
public interface QueueRO {
	Doctor getDoctor();
	Orthoptist getOrthoptist();
	List<QueueEntry> getQueueEntries();
	boolean contains(Integer queueEntryID);
	boolean containsPatient(Integer patientID);
}
