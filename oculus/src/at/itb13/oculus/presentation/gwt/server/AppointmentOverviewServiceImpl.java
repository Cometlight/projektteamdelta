package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
@SuppressWarnings("serial")
public class AppointmentOverviewServiceImpl extends RemoteServiceServlet
	implements AppointmentOverviewService{
	
	public String[] getPatientData (String email){
		
	return ControllerFacade.getInstance().getNewAppointment().getPatientData(email);
	}
	public String[] getPatientAppointment (String email){
		return ControllerFacade.getInstance().getNewAppointment().getPatientAppointment(email);
	}
}
