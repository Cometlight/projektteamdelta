package at.itb13.oculus.domain.readonlyinterfaces;

import java.time.LocalDateTime;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
public interface QueueEntryRO {
	Integer getQueueEntryId();
	PatientRO getPatient();
	LocalDateTime getArrivalTime();
}
