package at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
@RemoteServiceRelativePath("AppointmentOverviewService")
public interface AppointmentOverviewService extends RemoteService{
	public String[] getPatientData (String email);
}
