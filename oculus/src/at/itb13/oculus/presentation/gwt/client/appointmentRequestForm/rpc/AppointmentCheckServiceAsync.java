package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 29.05.2015
 */
public interface AppointmentCheckServiceAsync {
	void getPossibleAppointment(String weekday, String from, String to, Date start, 
								Date end, String socialInsuranceNumber, AsyncCallback<String> callback);
}
