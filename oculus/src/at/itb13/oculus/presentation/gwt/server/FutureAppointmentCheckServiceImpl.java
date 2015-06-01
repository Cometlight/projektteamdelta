package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.login.rpc.FutureAppointmentCheckService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FutureAppointmentCheckServiceImpl extends RemoteServiceServlet
		implements FutureAppointmentCheckService {

	@Override
	public Boolean hasFutureAppointment() {
		return ControllerFacade.getInstance().getNewAppointment().hasFutureAppointment();
	}
	
}
