package at.itb13.oculus.presentation.gwt.server;

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
		public List<CalendarEvent> getPossibleAppointments(String weekday1, String from1, String to1, 
				 											String weekday2, String from2, String to2, 
				 											String weekday3, String from3, String to3,
				 											boolean isAdded1, boolean isAdded2,
				 											Date start, Date end, String appointmentType){
			
			List<CalendarEvent> list = new LinkedList<>();
			
			list.add(ControllerFacade.getInstance().getNewAppointment().
								   getPossibleAppointment(weekday1,from1,to1,start,end,appointmentType));
			
			if(isAdded1){
				list.add(ControllerFacade.getInstance().getNewAppointment().
						   getPossibleAppointment(weekday2,from2,to2,start,end,appointmentType));
				if(isAdded2){
					list.add(ControllerFacade.getInstance().getNewAppointment().
							   getPossibleAppointment(weekday3,from3,to3,start,end,appointmentType));
				}
			}
			return list;
		}
		
		@Override
		public List<String> getEventTypes() {
			
			List<String> eventTypesStrings = new LinkedList<>();
			ControllerFacade.getInstance().getAllEventTypes().forEach(e -> eventTypesStrings.add(e.getEventTypeName()));
			return eventTypesStrings;
		}	
}
