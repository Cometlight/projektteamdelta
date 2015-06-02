package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 29.05.2015
 */
public interface AppointmentCheckServiceAsync {
	void getPossibleAppointment(String weekday, String from, String to, Date start, 
								Date end,  boolean isSameDay, String appointmentType, AsyncCallback<String> callback);

	void getEventTypes(AsyncCallback<List<String>> callback);

}
