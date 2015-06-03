package at.itb13.oculus.presentation.gwt.server;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
			
			if(weekday1.equals(weekday2)){
				
			}
			
			List<LocalDateTime> ldt = createLocalDateTimeOfStrings(weekday1, from1, to1);
			list.add(ControllerFacade.getInstance().getNewAppointment().
								   getPossibleAppointment(ldt.get(0), ldt.get(1),start,end,appointmentType));
			
			if(isAdded1){
				ldt = createLocalDateTimeOfStrings(weekday2, from2, to2);
				list.add(ControllerFacade.getInstance().getNewAppointment().
						   getPossibleAppointment(ldt.get(0), ldt.get(1),start,end,appointmentType));
				if(isAdded2){
					ldt = createLocalDateTimeOfStrings(weekday3, from3, to3);
					list.add(ControllerFacade.getInstance().getNewAppointment().
							   getPossibleAppointment(ldt.get(0), ldt.get(1),start,end,appointmentType));
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
		
		private List<LocalDateTime> createLocalDateTimeOfStrings(String weekday, String from, String to){
			LocalTime lt1;
			LocalTime lt2;
			if(!from.isEmpty()){
				lt1 = createLocalTimeOfString(from);
			}else{
				lt1 = LocalTime.of(8, 00);
			}
			if(!to.isEmpty()){
				lt2 = createLocalTimeOfString(to);
			}else{
				lt2 = LocalTime.of(18, 00);
			}
			LocalDate ld = createLocalDateOfString(weekday);
			LocalDateTime ldt1 = LocalDateTime.of(ld, lt1);
			LocalDateTime ldt2 = LocalDateTime.of(ld, lt2);
			List<LocalDateTime> list = new LinkedList<>();
			list.add(ldt1);
			list.add(ldt2);
			return list;
		}
		
		private LocalTime createLocalTimeOfString(String time){
			String[] parts = time.split(":");
			int hour = Integer.parseInt(parts[0]);
			int minute = Integer.parseInt(parts[1]);
			LocalTime lt = LocalTime.of(hour, minute);
			return lt;
		}
		
		private LocalDate createLocalDateOfString(String weekday){
			LocalDate ld = LocalDate.now();
			while(!(weekday.equalsIgnoreCase(ld.getDayOfWeek().name()))){
				ld = ld.plusDays(1);
			}
			return ld;
		}
}
