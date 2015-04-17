package at.itb13.oculus.presentation.view;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.doctor.DoctorRequest;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.CalendarEventRO;
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
	private TableView<CalendarEventRO> _appointmentTable;	
	@FXML
	private TableColumn<CalendarEventRO, String> _timeColumn;	
	@FXML
	private TableColumn<CalendarEventRO, String> _patientColumn;	
	@FXML
	private TableColumn<CalendarEventRO, String> _otherColumn;
	
	private ObservableList<CalendarEventRO> _appointments = FXCollections.observableArrayList();
	
	@FXML
	private Label _description;
	@FXML
	private Label _patient;
	@FXML
	private Label _eventType;
	
	
	//general Attributs
	private OculusMain _main;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {
		
		getTodaysCalendarEvents();
		_appointmentTable.setItems(_appointments);
		
		  _timeColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("eventStart"));
	      _patientColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("patientId"));
	      _otherColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("patientName"));
		
	}
	
	private void getTodaysCalendarEvents(){
		List<CalendarController> listCalCo = ControllerFacade.getInstance().getAllCalendarController();
		LocalDateTime start = LocalDateTime.of(2014, 1, 1, 1, 0);
		LocalDate startofend = LocalDate.now();
		LocalDateTime end = LocalDateTime.of(startofend, LocalTime.MAX);
		
		List<CalendarEventRO> events = new LinkedList<>();
		try {			
			
			// With list instead:
			for(CalendarController calCo : listCalCo) {
				events.addAll(calCo.getCalendarEventsInTimespan(start, LocalDateTime.now()));
			}
			
			for(CalendarEventRO e : events){
				_appointments.add(e);
				
			}
			
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public ObservableList<CalendarEventRO> getAppointments() {
	        return _appointments;
	    }
	 
	 public void addAppointment(CalendarEventRO e){
		 _appointments.add(e);
	 }
	public void clearAppointments() {
		_appointments.clear();
			
	}
	

}
