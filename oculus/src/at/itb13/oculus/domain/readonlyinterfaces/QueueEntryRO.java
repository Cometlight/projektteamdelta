package at.itb13.oculus.domain.readonlyinterfaces;

import java.time.LocalDateTime;

import at.itb13.oculus.domain.Patient;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date 14.04.2015
 */
public interface QueueEntryRO {
	Integer getQueueEntryId();
	Patient getPatient();
	LocalDateTime getArrivalTime();
}
