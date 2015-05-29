package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.rpc.AppointmentOverviewService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * TODO: Insert description here.
 * 
 * @author Karin Trommelschläger
 * @date 29.05.2015
 * 
 */
public class AppointmentOverviewServiceImpl extends RemoteServiceServlet
	implements AppointmentOverviewService{
	
	public Patient getPatientData (String email){
	return ControllerFacade.getInstance().getNewAppointment().getPatientData(email);
	}
}
