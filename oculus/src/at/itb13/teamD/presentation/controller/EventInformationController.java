package at.itb13.teamD.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.presentation.OculusMain;
import at.itb13.teamD.domain.interfaces.ICalendarEvent;
import at.itb13.teamD.domain.interfaces.IPatient;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 10.05.2015
 */
public class EventInformationController {
	@SuppressWarnings("unused")
	private static final Logger _logger = LogManager.getLogger(OculusMain.class.getName());

	@FXML
	private Label _descriptionLabel;
	@FXML
	private Label _dateTimeLabel;
	@FXML
	private Label _patientLabel;
	@FXML
	private Label _eventTypeLabel;
	@FXML
	private Label _doctorLabel;
	@FXML
	private Label _sinLabel;
	@FXML
	private Label _dateOfBirthLabel;
	
	//@FXML
	//private BorderPane _patientRecordBorderPane;
	
	private Stage _dialogStage;
	private IPatient _patient;
	private ICalendarEvent _event;
	
	@FXML
	private void initialize() {
//		showPatientRecord();
//		showAppointmentInformation();
	}
	
	public void setPatient(IPatient patient){
		_patient = patient;
	}
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		_dialogStage.getIcons().add(new Image("file:ApplicationResources/Images/Auge.png"));
		
	}
	public void setEvent(ICalendarEvent event){
		_event = event;
	}
		
	
	//--> TODO: find a better option to this!
//	public void showPatientRecord() {
//		try {
//			// Load person overview.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(OculusMain.class
//					.getResource("view/PatientRecord.fxml"));
//			AnchorPane overview = (AnchorPane) loader.load();
//
//			// Set person overview into the center of root layout.
//			_patientRecordBorderPane.setCenter(overview);
//
//			// Give the controller access to the main app.
//			PatientRecordController controller = loader.getController();
//			if(_patient != null) {
//				controller.showPatientMasterData((PatientRO) _patient);
//				controller.showAnamanesis((PatientRO) _patient);
//				controller.showAppointments((PatientRO) _patient);
//				_logger.info("showPatientRecord");
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			_logger.error("showPatientRecord failed", ex);
//		}
//	}
	
//	public void showAppointmentInformation() {
//		
//		if (_event != null) {
//			_descriptionLabel.setText(_event.getDescription());
//			_dateTimeLabel.setText(_event.getEventStart().toString());
//			EventType type = (EventType) _event.getEventType();
//			_eventTypeLabel.setText(type.getEventTypeName());
//			//TODO Interfaces
////			if(_event.getCalendar().getDoctor() != null){
////				_doctorLabel.setText(_event.getCalendar().getDoctor().getUser().getFirstName() + " " +_event.getCalendar().getDoctor().getUser().getLastName());
////			}else if(_event.getCalendar().getOrthoptist() != null){
////				_doctorLabel.setText(_event.getCalendar().getOrthoptist().getUser().getFirstName() +" " + _event.getCalendar().getOrthoptist().getUser().getLastName());
////
////			}else{
////				_doctorLabel.setText("");
////			}
//			if (_event.getPatient() == null) {
//				_patientNotInDatabaseLabel
//						.setText("New patient!\nPlease add the patient by clicking on \"Add Patient\".\nPatient Name:");
//				_patientLabel.setText(_event.getPatientName());
//				_addPatientButton.setDisable(false);
//				_addPatientButton.setVisible(true);
//				_queueBox.setDisable(true);
//				_insertQueueButton.setDisable(true);
//			} else {
//				_patientNotInDatabaseLabel.setText("");
//				_patientLabel.setText("");
//				_addPatientButton.setDisable(true);
//				_addPatientButton.setVisible(false);
//				Boolean inQueue = false;
//				//TODO--> INTERFACES
////				List<QueueController> queCon = ControllerFacade.getInstance().getAllQueueController();
////				for(QueueController controller: queCon){
////					if(controller.isPatientInQueue(_event.getPatient())){
////						inQueue = true;
////						break;
////					}
////				}
//				if(!inQueue){
//					_queueBox.setDisable(false);
//					_insertQueueButton.setDisable(false);
//				}
//			}
//			
//			
//		} else {
//			_descriptionLabel.setText("");
//			_dateTimeLabel.setText("");
//			_eventTypeLabel.setText("");
//			_doctorLabel.setText("");
//			_patientNotInDatabaseLabel.setText("");
//			_patientLabel.setText("");
//			_addPatientButton.setDisable(true);
//			_addPatientButton.setVisible(false);
//			_queueBox.setDisable(true);
//			_insertQueueButton.setDisable(true);
//		}
//	}
	
	public void showAppointInfo(){
		if(_event != null){
			_patient = _event.getPatient();
			if(_patient != null){
				
				_patientLabel.setText(((_patient.getFirstName() == null) ? "" : _patient.getFirstName()) + " " + ((_patient.getLastName() == null) ? "" : _patient.getLastName()));
				_sinLabel.setText((_patient.getSocialInsuranceNr() == null) ? "" : _patient.getSocialInsuranceNr());
				_dateOfBirthLabel.setText((_patient.getDateOfBirth() == null) ? "" : _patient.getDateOfBirth().toString());
			}
			else{
				_patientLabel.setText((_event.getPatientName() == null) ? "" : _event.getPatientName());
				_sinLabel.setText("");
				_dateOfBirthLabel.setText("");
			
			}
			_dateTimeLabel.setText(((_event.getEventStart()== null) ? "" :_event.getEventStart()) + " - "+ ((_event.getEventEnd() == null) ? "" :_event.getEventEnd()));
			_descriptionLabel.setText((_event.getDescription() == null) ? "" : _event.getDescription());
			_eventTypeLabel.setText((_event.getEventType() == null) ? "" :_event.getEventType().getEventTypeName());
			_doctorLabel.setText((_event.getCalendar() == null) ? "" :_event.getCalendar().getTitle());
		}else{
			_patientLabel.setText("");
			_dateTimeLabel.setText("");
			_descriptionLabel.setText("");
			_eventTypeLabel.setText("");
			_doctorLabel.setText(""); 
			_sinLabel.setText("");
			_dateOfBirthLabel.setText("");	
		}
	}
}
