package at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 29.05.2015
 */
@RemoteServiceRelativePath("AppointmentChekcService")
public interface AppointmentCheckService extends RemoteService {
	Object getPossibleAppointment(String weekday, String from, String to, 
									Date start, Date end);
}