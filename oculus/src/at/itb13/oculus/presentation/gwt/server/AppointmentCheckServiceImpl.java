package at.itb13.oculus.presentation.gwt.server;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * TODO: Insert description here.
 *
 * @author Florin Metzler
 * @since 29.05.2015
 */
public class AppointmentCheckServiceImpl extends RemoteServiceServlet implements AppointmentCheckService{
	private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private static final Logger _logger = LogManager.getLogger(AppointmentCheckServiceImpl.class.getName());
		
		@Override
		public String getPossibleAppointment(String weekday, String from, String to, Date start, Date end, String socialInsuranceNumber, String appointmentType) {
			return ControllerFacade.getInstance().getNewAppointment().getPossibleAppointment(weekday,from,to,start,end, socialInsuranceNumber, appointmentType);
		}	
}
