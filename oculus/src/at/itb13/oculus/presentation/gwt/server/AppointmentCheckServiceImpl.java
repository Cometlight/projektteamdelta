package at.itb13.oculus.presentation.gwt.server;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.rpc.AppointmentCheckService;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;

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
		public CalendarEvent getPossibleAppointment(String weekday, String from, String to, Date start, Date end, 
											boolean isSameDay, String appointmentType) {
			return ControllerFacade.getInstance().getNewAppointment().
				   getPossibleAppointment(weekday,from,to,start,end, isSameDay, appointmentType);
		}	
		
		@Override
		public List<String> getEventTypes() {
			
			List<String> eventTypesStrings = new LinkedList<>();
			ControllerFacade.getInstance().getAllEventTypes().forEach(e -> eventTypesStrings.add(e.getEventTypeName()));
			return eventTypesStrings;
		}

		@Override
		public Boolean isInWorkingHours(String weekday, String from, String to) {
			DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime fromLT = LocalDateTime.parse(from, formatterTime);
			LocalDateTime toLT = LocalDateTime.parse(from, formatterTime);
			return true;
//			return ControllerFacade.getInstance().getNewAppointment().isInWorkingHours(weekday, from, to);
		}	
}
