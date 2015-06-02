package at.itb13.oculus.presentation.gwt.server;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
		public String getPossibleAppointment(String weekday, String from, String to, Date start, Date end, 
											boolean isSameDay, String appointmentType) {
			return ControllerFacade.getInstance().getNewAppointment().
				   getPossibleAppointment(weekday,from,to,start,end, isSameDay, appointmentType).toString();
		}	
		
		@Override
		public List<String> getEventTypes() {
			
			List<String> eventTypesStrings = new LinkedList<>();
			ControllerFacade.getInstance().getAllEventTypes().forEach(e -> eventTypesStrings.add(e.getEventTypeName()));
			return eventTypesStrings;
		}	
}
