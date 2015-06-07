package at.itb13.oculus.presentation.gwt.server;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckService;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginCheckServiceImpl extends RemoteServiceServlet implements
		LoginCheckService {

	@Override
	public Patient isLoginCredentialsValid(String email, String password) {
		
		return ControllerFacade.getInstance().getNewAppointment().isLoginCredentialsValid(email, password);
	}

}
