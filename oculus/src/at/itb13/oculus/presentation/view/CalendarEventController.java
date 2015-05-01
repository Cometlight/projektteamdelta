package at.itb13.oculus.presentation.view;

import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.presentation.OculusMain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.05.2015
 */
public class CalendarEventController {

	@FXML
	private Label _patientLabel;
	@FXML
	private Label _timeLabel;
	@FXML
	private Label _typeLabel;
	@FXML
	private Label _reasonLabel;
	private CalendarEventRO _calEvent;
	
	private OculusMain _main;
	
	
	@FXML
	private void initialize(){
		fillLabels();
	}
	
	public void setMain(OculusMain main){
		_main = main;
	}
	
	public void setCalEvent(CalendarEventRO calendarEvent){
		_calEvent = calendarEvent;
	}
	
	private void fillLabels(){
		if(_calEvent != null){
			if(_calEvent.getPatient() != null){
				_patientLabel.setText(_calEvent.getPatient().getFirstName() +" "+_calEvent.getPatient().getLastName());
			}else{
				_patientLabel.setText(_calEvent.getPatientName());
			}
			_timeLabel.setText(_calEvent.getEventStart().toString());
			_reasonLabel.setText(_calEvent.getDescription());
			_typeLabel.setText(_calEvent.getEventType().getEventTypeName());
		}
		
	}
	
}
