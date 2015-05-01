package at.itb13.oculus.presentation.view;


import java.util.List;

import javafx.fxml.FXML;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.presentation.OculusMain;


public class TabCalendarController {
	
	private OculusMain _main;
	private List<CalendarEventRO> _calEvents;
	
	 public void setMain(OculusMain main) {
	        _main = main;
	  }
	 @FXML
	 private void initialize() {
		 setCalendarEvents();
//		 for(CalendarEventRO cal : _calEvents){
//			 //create new CalendarEvent with dinamic size and set them to right position
//		 }
		 
	 }
	 
	 private void setCalendarEvents(){
		 //_calEvents = getAllCalendarEventsInTimeSpan...
	 }
}