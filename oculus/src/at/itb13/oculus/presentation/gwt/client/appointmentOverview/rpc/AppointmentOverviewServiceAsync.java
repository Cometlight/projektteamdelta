package at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc;

import at.itb13.oculus.domain.CalendarEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
public interface AppointmentOverviewServiceAsync {
	public void getPatientData (String email,AsyncCallback<String[]> callback);
	public void getPatientAppointment(String email,AsyncCallback<at.itb13.oculus.presentation.gwt.shared.CalendarEvent> callback);
	/**
	 * @param calEventId
	 * @param callback
	 */
	public void deleteAppointment(int calEventId,
			AsyncCallback<Boolean> callback);
}
