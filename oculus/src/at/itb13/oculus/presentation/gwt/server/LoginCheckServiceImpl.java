package at.itb13.oculus.presentation.gwt.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginCheckServiceImpl extends RemoteServiceServlet implements
		LoginCheckService {
	private static final Logger _logger = LogManager.getLogger(LoginCheckServiceImpl.class.getName());

	@Override
	public Boolean isLoginCredentialsValid(String email, String password) {
		
		return ControllerFacade.getInstance().getNewAppointment().isLoginCredentialsValid(email, password);
	}

}
