package at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc;

import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
public interface AppointmentChoiceServiceAsync {

	/**
	 * @param patient
	 * @param calendarEvent
	 */
	void addAppointment(Patient patient,
			at.itb13.oculus.presentation.gwt.shared.CalendarEvent calendarEvent, AsyncCallback<Boolean> callback);
	
}
