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

	void getPossibleAppointments(String weekday1, String from1, String to1, 
			 					String weekday2, String from2, String to2, 
			 					String weekday3, String from3, String to3,
			 					boolean isAdded1, boolean isAdded2,
			 					Date start, Date end, String appointmentType,
			 					AsyncCallback<List<CalendarEvent>> callback);	
	
	void getEventTypes(AsyncCallback<List<String>> callback);

	void isInWorkingHours(String weekday, String from, String to,
			AsyncCallback<Boolean> callback);

}
