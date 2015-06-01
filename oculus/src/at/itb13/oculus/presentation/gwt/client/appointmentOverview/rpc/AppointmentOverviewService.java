package at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPatientAppointment(Patient pa);
	public boolean deleteAppointment(int calEventId);
}
