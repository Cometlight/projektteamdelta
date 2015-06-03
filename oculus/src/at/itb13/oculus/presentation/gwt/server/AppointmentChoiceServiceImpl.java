package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceService;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.06.2015
 */
public class AppointmentChoiceServiceImpl extends RemoteServiceServlet
		implements AppointmentChoiceService {

	/*
	 * @see at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceService#addAppointment(at.itb13.oculus.presentation.gwt.shared.Patient, at.itb13.oculus.presentation.gwt.shared.CalendarEvent)
	 */
	@Override
	public boolean addAppointment(Patient patient, CalendarEvent calendarEvent) {
		
		return ControllerFacade.getInstance().getNewAppointment().addAppointment(patient, calendarEvent);
	}

}
