package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
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
	
	/*
	 * @see at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService#deleteAppointment(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public boolean deleteAppointment(int calEventId) {
		
		return ControllerFacade.getInstance().getNewAppointment().deleteAppointment(calEventId);
	}
	/*
	 * @see at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService#getPatientAppointment(at.itb13.oculus.presentation.gwt.shared.Patient)
	 */
	@Override
	public at.itb13.oculus.presentation.gwt.shared.CalendarEvent getPatientAppointment(
			Patient pa) {
		// TODO Auto-generated method stub
		return null;
	}
}
