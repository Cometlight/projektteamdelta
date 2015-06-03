package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc;

import java.util.Date;
import java.util.List;

import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 29.05.2015
 */
public interface AppointmentCheckServiceAsync {
	void getPossibleAppointment(String weekday, String from, String to, Date start, 
								Date end,  boolean isSameDay, String appointmentType, AsyncCallback<CalendarEvent> callback);

	void getEventTypes(AsyncCallback<List<String>> callback);

}
