package at.itb13.oculus.presentation.view.calendar;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.presentation.OculusMain;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.05.2015
 */
public class CalendarEventController {
	private static final Logger _logger = LogManager.getLogger(OculusMain.class.getName());

	@FXML
	private Hyperlink _patientLabel;
	
	private ICalendarEvent _calEvent;
	
	public CalendarEventController() { }
	
	public CalendarEventController(ICalendarEvent calendarEvent) {
		setCalEvent(calendarEvent);
	}
	
	@FXML
	private void initialize() { }
	
	public void setCalEvent(ICalendarEvent calendarEvent){
		_calEvent = calendarEvent;
		fillLabels();
	}
	
	private void fillLabels(){
		if(_calEvent != null){
			if(_calEvent.getPatient() != null){
				_patientLabel.setText(_calEvent.getPatient().getFirstName() +" "+_calEvent.getPatient().getLastName());
			}else{
				_patientLabel.setText(_calEvent.getPatientName());
			}
			
			
		}
		
	}
	@FXML
	private void moreInfoLinkControl(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/calendar/EventInformation.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Appointment Information");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			EventInformationController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEvent(_calEvent);
			controller.showAppointInfo();
			dialogStage.showAndWait();
			_logger.info("showNewAppointmentDialog successful");
					
		} catch (IOException ex) {
			_logger.error("showNewAppointmentDialog failed", ex);
						
		}
	}
	
	//Problem: PatientRO 
//	@FXML
//	private void moreInfoLinkControl(){
//		try {
//			
//			// Load the fxml file and create a new stage for the popup dialog.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(OculusMain.class
//					.getResource("view/calendar/EventInformation.fxml"));
//			AnchorPane page = (AnchorPane) loader.load();
//
//			// Create the dialog Stage.
//			Stage dialogStage = new Stage();
//			dialogStage.setTitle("Appointment Informations");
//			dialogStage.initModality(Modality.WINDOW_MODAL);
//			//dialogStage.initOwner(_primaryStage);
//			Scene scene = new Scene(page);
//			dialogStage.setScene(scene);
//
//			// Set the person into the controller.
//			EventInformationController controller = loader.getController();
//			controller.setDialogStage(dialogStage);
//			controller.setEvent(_calEvent);
//			controller.setPatient(_calEvent.getPatient());
//			controller.showPatientRecord();
//			controller.showAppointmentInformation();
//
//			// Show the dialog and wait until the user closes it
//			dialogStage.showAndWait();
//
//			_logger.info("showNewAppointmentDialog successful");
//		
//		} catch (IOException ex) {
//			_logger.error("showNewAppointmentDialog failed", ex);
//			
//		}
//	}	
}
