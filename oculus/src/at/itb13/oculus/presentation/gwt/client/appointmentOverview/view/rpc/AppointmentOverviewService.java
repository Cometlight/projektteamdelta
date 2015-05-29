package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.rpc;

import at.itb13.oculus.domain.Patient;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
public interface AppointmentOverviewService extends RemoteService{
	public Patient getPatientData (String email);
}
