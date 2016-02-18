package at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc;

import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
@RemoteServiceRelativePath("AppointmentChoiceService")
public interface AppointmentChoiceService extends RemoteService{
	boolean addAppointment(Patient patient,
			at.itb13.oculus.presentation.gwt.shared.CalendarEvent calendarEvent);
	
}
