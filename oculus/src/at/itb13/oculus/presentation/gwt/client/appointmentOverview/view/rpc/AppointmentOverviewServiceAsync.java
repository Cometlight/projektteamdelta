package at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import at.itb13.oculus.domain.Patient;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschl�ger
 * @date 29.05.2015
 * 
 */
public interface AppointmentOverviewServiceAsync {
	public void getPatientData (String email,AsyncCallback<Patient> callback);
}
