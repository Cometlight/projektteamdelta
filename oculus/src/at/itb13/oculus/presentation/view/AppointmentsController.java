package at.itb13.oculus.presentation.view;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.doctor.DoctorRequest;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.EventType;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class AppointmentsController {
	private static final Logger _logger = LogManager.getLogger(AppointmentsController.class.getName());
	
	@FXML
	private TableView<CalendarEvent> _appointmentTable;	
	@FXML
	private TableColumn<CalendarEvent, String> _timeColumn;	
	@FXML
	private TableColumn<CalendarEvent, String> _patientColumn;	
	@FXML
	private TableColumn<CalendarEvent, String> _otherColumn;

	
	@FXML
	private Label _description;
	@FXML
	private Label _patientNotInDatabase;
	@FXML
	private Label _patient;
	@FXML
	private Label _eventType;
	@FXML
	private Button _addPatient;
	
	@FXML
	private ComboBox _queueBox;
	@FXML
	private Button _insertQueueButton;
	
	

	private ObservableList<CalendarEvent> _appointments = FXCollections.observableArrayList();

	private OculusMain _main;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {
		
		getTodaysCalendarEvents();
		_appointmentTable.setItems(_appointments);
		
		  _timeColumn.setCellValueFactory(new PropertyValueFactory<CalendarEvent, String>("eventStart"));
	      _patientColumn.setCellValueFactory(new PropertyValueFactory<CalendarEvent, String>("patientId"));
	      _otherColumn.setCellValueFactory(new PropertyValueFactory<CalendarEvent, String>("patientName"));
	      
	     // showAppointmentInformation(null);
	      
	      _appointmentTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showAppointmentInformation(newValue));
		
	}
	
	private void getTodaysCalendarEvents(){
		List<CalendarController> listCalCo = ControllerFacade.getInstance().getAllCalendarController();
		LocalDateTime start = LocalDateTime.of(2014, 1, 1, 1, 0);
		LocalDate startofend = LocalDate.now();
		LocalDateTime end = LocalDateTime.of(startofend, LocalTime.MAX);
		
		List<CalendarEvent> events = new LinkedList<>();
		try {			
			
			// With list instead:
			for(CalendarController calCo : listCalCo) {
				events.addAll(calCo.getCalendarEventsInTimespan(start, LocalDateTime.now()));
			}
			
			for(CalendarEvent e : events){
				_appointments.add(e);
				
			}
			
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public ObservableList<CalendarEvent> getAppointments() {
	        return _appointments;
	    }
	 
	 public void addAppointment(CalendarEvent e){
		 _appointments.add(e);
	 }
	public void clearAppointments() {
		_appointments.clear();
			
	}
	
	private void showAppointmentInformation(CalendarEvent event) {
		_description.setText(event.getDescription());
		if(event.getPatient() == null){
			_patientNotInDatabase.setText("Patient is not in Database.\nPatient Name");
			_addPatient.setDisable(false);
			_addPatient.setVisible(true);
			_queueBox.setDisable(true);
			_insertQueueButton.setDisable(true);
		}else{
			_patientNotInDatabase.setText("");
			_addPatient.setDisable(true);
			_addPatient.setVisible(false);
			_queueBox.setDisable(false);
			_insertQueueButton.setDisable(false);
		}
		_patient.setText(event.getPatientName());
		EventType type = event.getEventtype();
		
		_eventType.setText(type.getEventTypeName());
	}
	
	@FXML
	 private void addPatientControl(){
					
			_main.showNewPatientDialog();
			CalendarController calco = ControllerFacade.getInstance().getCalendarController(null, null);
			calco.connectCalendarEventwithPatient( _appointmentTable.getSelectionModel().getSelectedItem(), _main.getCreatedPatient());
			
		 }
	

}
