package at.itb13.teamD.presentation.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.teamD.application.ControllerFacade;
import at.itb13.teamD.application.exceptions.InvalidInputException;
import at.itb13.teamD.application.exceptions.SaveException;
import at.itb13.teamD.application.interfaces.INewAppointmentController;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamD.presentation.converter.CalendarEventTypeStringConverter;
import at.itb13.teamD.presentation.converter.CalendarStringConverter;

/**
 * @author Caroline Meusburger
 * @since 04.05.2015
 */
public class NewAppointmentController {
	private static final Logger _logger = LogManager.getLogger(NewAppointmentController.class.getName());
	
	private static final String SIMPLE_PATIENT_RECORD_FXML = "/at/itb13/teamD/presentation/view/SimplePatientRecord.fxml";
	@FXML
	private TextField _patientSearchField;
	@FXML
	private Button _searchPatientButton;
	@FXML
	private TableView<IPatient> _patientTableView;
	@FXML
	private TableColumn<IPatient, String> _firstNameColumn;	
	@FXML
	private TableColumn<IPatient, String> _lastNameColumn;	
	@FXML
	private TableColumn<IPatient, String> _SSNColumn;
	@FXML
	private TableColumn<IPatient, String> _birthdayColumn;
	@FXML
	private Label _selectedpatient;
	@FXML
	private Button _patientRecordButton;
	@FXML
	private DatePicker _datePicker;
	@FXML
	private Spinner<Integer> _startTimeSpinnerHour;
	@FXML
	private Spinner<Integer> _startTimeSpinnerMin;
	@FXML
	private Spinner<Integer> _endTimeSpinnerHour;
	@FXML
	private Spinner<Integer> _endTimeSpinnerMin;
	@FXML
	private ComboBox<IEventType> _typeBox;
	@FXML
	private ComboBox<ICalendar> _doctorBox;
	@FXML
	private TextArea _resonText;
	private Stage _dialogStage;
	private LocalDateTime _startDate;
	private LocalDateTime _endDate;
	private IPatient _patient;
	private String _patientName;
	private ICalendar _calendar;
	
	private boolean _okClicked = false;
	private ObservableList<IPatient> _patientData = FXCollections.observableArrayList();
	
	@FXML
	private void initialize(){
		

		   // Initialize the person table with the three columns.
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<IPatient, String>("firstName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<IPatient, String>("lastName"));
        _SSNColumn.setCellValueFactory(new PropertyValueFactory<IPatient, String>("socialInsuranceNr"));
        _birthdayColumn.setCellValueFactory(new PropertyValueFactory<IPatient, String>("dateOfBirth"));
	 
        // Listen for selection changes and show the person details when changed.
        _patientTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                	if(_patientTableView.getSelectionModel().getSelectedItem() != null){
                		_selectedpatient.setText(((newValue.getFirstName() == null) ? "" : newValue.getFirstName()) + " " + ((newValue.getLastName() == null) ? "" :  newValue.getLastName()) + " " + ((newValue.getDateOfBirth() == null) ? "" :newValue.getDateOfBirth()));
                		_patientRecordButton.setDisable(false);
        				_patient = _patientTableView.getSelectionModel().getSelectedItem();
        				_patientName = "";
        				setAssignedDoctor();
                	}
                });
        
        _doctorBox.setConverter(new CalendarStringConverter());
		initSpinner();
		initDatePicker();
	}
	
	public void init(List<ICalendar> calendars) {
		_doctorBox.getItems().setAll(calendars);
		setItemsToTypeBox();
	}
	
	private void initDatePicker() {
		_datePicker.setConverter(new StringConverter<LocalDate>() {
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		});
	}
	
	public ObservableList<IPatient> getPatientData() {
		return _patientData;
	}

	public void addPatientData(IPatient pa) {
		_patientData.add(pa);
	}

	public void clearPatientData() {
		_patientData.clear();
	}
	
	/**
	 * Sets the datePicker and the start time spinner to dateTime
	 * @param dateTime The LocalDateTime to be set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		_logger.info("Setting datePicker and startTimeSpinner to " + dateTime);
		_datePicker.setValue(dateTime.toLocalDate());
		_startTimeSpinnerHour.getValueFactory().setValue(dateTime.getHour());
		_startTimeSpinnerMin.getValueFactory().setValue(dateTime.getMinute());
	}
	
	private void setAssignedDoctor(){
		if((_patient != null)&&(_patient.getDoctor() != null)&&(_patient.getDoctor().getICalendar() != null)){
			_doctorBox.getSelectionModel().select(_patient.getDoctor().getICalendar());
		}	
	}
	
	private void setItemsToTypeBox(){
		_typeBox.setConverter(new CalendarEventTypeStringConverter());
		List<IEventType> types = ControllerFacade.getInstance().getAllEventTypes();
		for(IEventType t : types){
			_typeBox.getItems().add(t);
		}
	}
	
	private void initSpinner(){
		
		
		SpinnerValueFactory<Integer> sHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		SpinnerValueFactory<Integer> sMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		SpinnerValueFactory<Integer> eHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		SpinnerValueFactory<Integer> eMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		
		//start Hour
		_startTimeSpinnerHour.setValueFactory(sHours);
		SpinnerValueFactory.IntegerSpinnerValueFactory startHourFactory = (IntegerSpinnerValueFactory) _startTimeSpinnerHour.getValueFactory();  
        startHourFactory.setMax(23); 
        startHourFactory.setValue(0);
        startHourFactory.setWrapAround(true);
        _startTimeSpinnerHour.setEditable(true); 
        
        //End Hour
        _endTimeSpinnerHour.setValueFactory(eHours);
        SpinnerValueFactory.IntegerSpinnerValueFactory endHourFactory =(IntegerSpinnerValueFactory) _endTimeSpinnerHour.getValueFactory();
        endHourFactory.setMax(23); 
        endHourFactory.setValue(0);
        endHourFactory.setWrapAround(true);
        _endTimeSpinnerHour.setEditable(true);
		
		//start minutes
        _startTimeSpinnerMin.setValueFactory(sMin);
        SpinnerValueFactory.IntegerSpinnerValueFactory startMinFactory = (IntegerSpinnerValueFactory) _startTimeSpinnerMin.getValueFactory();
        startMinFactory.setMax(59);
        startMinFactory.setValue(0);
        startMinFactory.setWrapAround(true);
        _startTimeSpinnerMin.setEditable(true);
        
      //end minutes
        _endTimeSpinnerMin.setValueFactory(eMin);
        SpinnerValueFactory.IntegerSpinnerValueFactory endMinFactory = (IntegerSpinnerValueFactory) _endTimeSpinnerMin.getValueFactory();
        endMinFactory.setMax(59);
        endMinFactory.setValue(0);
        endMinFactory.setWrapAround(true);
        _endTimeSpinnerMin.setEditable(true);
        
        _startTimeSpinnerHour.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        	
        	String old = String.valueOf(_startTimeSpinnerHour.getValueFactory().getValue());
        	try{
	        	if((newValue!= null)&&(!newValue.equals("")&&(isNumber(newValue)))){
	        		_startTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(newValue));
	        	}else{
	        		
            		_startTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(old));
            		_startTimeSpinnerHour.getEditor().setText(old);
            		return;
	        	}
        	}catch (NumberFormatException e){
        	}
        });
                    
        _startTimeSpinnerMin.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        	String old = String.valueOf(_startTimeSpinnerMin.getValueFactory().getValue());
        	try{
	        	if((newValue!= null)&&(!newValue.equals("")&&(isNumber(newValue)))){
	        		_startTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(newValue));
	        	}else{
	        		
            		_startTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(old));
            		_startTimeSpinnerMin.getEditor().setText(old);
            		return;
	        	}
        	}catch (NumberFormatException e){
        	}
        	
        });
        	
        _endTimeSpinnerHour.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        	String old = String.valueOf(_endTimeSpinnerHour.getValueFactory().getValue());
        	try{
	        	if((newValue!= null)&&(!newValue.equals("")&&(isNumber(newValue)))){
	        		_endTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(newValue));
	        	}else{
	        		
            		_endTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(old));
            		_endTimeSpinnerHour.getEditor().setText(old);
            		return;
	        	}
        	}catch (NumberFormatException e){
        	}
        });
        	
        _endTimeSpinnerMin.getEditor().textProperty().addListener((observable, oldValue, newValue) ->   {
        	String old = String.valueOf(_endTimeSpinnerMin.getValueFactory().getValue());
        	try{
	        	if((newValue!= null)&&(!newValue.equals("")&&(isNumber(newValue)))){
	        		_endTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(newValue));
	        	}else{
	        		
            		_endTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(old));
            		_endTimeSpinnerMin.getEditor().setText(old);
            		return;
	        	}
        	}catch (NumberFormatException e){
        	}
        });
        	
	}
	
	/**
	 * @param newValue
	 * @return
	 */
	private boolean isNumber(String value) {
		   if ((value == null)||(value.equals(""))) {
	            return false;
	        }
	        try {
	            new Integer(value);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }		
	}

	@FXML
	private void withoutPatientButtonControl(){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Appointment for new Patient");
		dialog.setHeaderText("Enter the name of the new Patient");
		dialog.setContentText("Please enter the name of the new Patient: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    _selectedpatient.setText(result.get());
		    _patientTableView.getSelectionModel().clearSelection();
		    _patientRecordButton.setDisable(true);
		    _patient = null;
		    _patientName = _selectedpatient.getText();
		   
		}
		
	}

	/**
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		_dialogStage.getIcons().add(new Image("file:ApplicationResources/Images/Auge.png"));
	}
	
	public boolean isOkClicked() {
		return _okClicked;
	}
	private void calcDate(){
		LocalTime startTime = LocalTime.of(_startTimeSpinnerHour.getValue(), _startTimeSpinnerMin.getValue());
		_startDate = LocalDateTime.of(_datePicker.getValue(), startTime);
		LocalTime endTime = LocalTime.of(_endTimeSpinnerHour.getValue(), _endTimeSpinnerMin.getValue());
		_endDate = LocalDateTime.of(_datePicker.getValue(), endTime);
	}
	 /**
	  * Controls the "search" Button, for searching by Name and Social Insurance Number.
	  * It gets a List of Patients from the PatientSearch Class and creates new Patients with Property.
	  * The Patient Data are shown in the GUI
	  */
	@FXML
	 private void searchControl(){
		 clearPatientData();
		 INewAppointmentController controller = ControllerFacade.getInstance().getNewAppointmentController();
		 List<IPatient> patients = new ArrayList<>();
		 try {			
			patients =  (List<IPatient>) controller.searchPatient(_patientSearchField.getText());
			if(patients.size() > 0){
				for(IPatient pa : patients){
					addPatientData(pa);
				}
				_patientTableView.setItems(_patientData);
				
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Patient found");
				alert.setHeaderText("Sorry, the Patient could not be found.");
		        alert.setContentText("Please make sure you have entered the right name or Social Insurance Number.");
		        alert.showAndWait();
			}

		} catch (InvalidInputException e) {		
		//	_logger.warn(e);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Patient found");
			alert.setHeaderText("Sorry, the Patient could not be found.");
	        alert.setContentText("Please make sure you have entered the right name or Social Insurance Number.");
	        alert.showAndWait();
		}
	 }
//	 @FXML
//	 private void patientRecordButtonControl(){
//		
//		 if(_patient != null){
//			 try {
//					// Load the fxml file and create a new stage for the popup dialog.
//					FXMLLoader loader = new FXMLLoader();
//					loader.setLocation(OculusMain.class
//							.getResource("view/PatientRecord.fxml"));
//					AnchorPane page = (AnchorPane) loader.load();
//	
//					// Create the dialog Stage.
//					Stage dialogStage = new Stage();
//					
//					dialogStage.setTitle("Patient Record" + " " +_patient.getFirstName() + " " +_patient.getLastName());
//					dialogStage.initModality(Modality.WINDOW_MODAL);
//					
//					Scene scene = new Scene(page);
//					dialogStage.setScene(scene);
//	
//					// Set the person into the controller.
//					PatientRecordController controller = loader.getController();
//					controller.setDialogStage(dialogStage);
//					controller.setPatientRO((PatientRO) _patient);
//	
//					// Show the dialog and wait until the user closes it
//					dialogStage.showAndWait();
//	
//					//_logger.info("showNewPatientDialog successful");
//				
//				} catch (IOException ex) {
//					//_logger.error("showNewPatientDialog failed", ex);
//					
//				}
//		 }
//	 }
	 
	 @FXML
	 private void patientRecordButtonControl(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(SIMPLE_PATIENT_RECORD_FXML));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			
			dialogStage.setTitle("Patient Record" + " " +_patient.getFirstName() + " " +_patient.getLastName());
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			SimplePatientRecordController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPatient(_patient);
			controller.showPatientInfo();
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			//_logger.info("showNewPatientDialog successful");
						
			} catch (IOException ex) {
				//_logger.error("showNewPatientDialog failed", ex);
							
		}
	 }
	 @FXML
	 private void calcEndTimeControl(){
		 IEventType type = _typeBox.getValue();
		 if(type != null){
			 LocalTime startTime = LocalTime.of(_startTimeSpinnerHour.getValue(), _startTimeSpinnerMin.getValue());
			
			 int diffrence = type.getEstimatedTime();
			 LocalTime endTime = startTime.plusMinutes(diffrence);
			 
			 SpinnerValueFactory<Integer> eHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
			SpinnerValueFactory<Integer> eMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
				
			   //End Hour
		        _endTimeSpinnerHour.setValueFactory(eHours);
		        SpinnerValueFactory.IntegerSpinnerValueFactory endHourFactory =(IntegerSpinnerValueFactory) _endTimeSpinnerHour.getValueFactory();
		        endHourFactory.setMax(23); 
		        endHourFactory.setValue(endTime.getHour());
		        endHourFactory.setWrapAround(true);
		        _endTimeSpinnerHour.setEditable(true);
			   //end minutes
		        _endTimeSpinnerMin.setValueFactory(eMin);
		        SpinnerValueFactory.IntegerSpinnerValueFactory endMinFactory = (IntegerSpinnerValueFactory) _endTimeSpinnerMin.getValueFactory();
		        endMinFactory.setMax(59);
		        endMinFactory.setValue(endTime.getMinute());
		        endMinFactory.setWrapAround(true);
		        _endTimeSpinnerMin.setEditable(true);
		 }
	 }
	 
	 @FXML
	 private void saveButtonControl(){
		 if(inputIsValid()){
			calcDate();
			if((continueWhenNotInWorkingHour())&&(continueWithOverlaping())){
				 try {
					 if(_patient != null){
						 ControllerFacade.getInstance().getNewAppointmentController().newCalendarEvent(_calendar, _typeBox.getSelectionModel().getSelectedItem(), _startDate, _endDate, _resonText.getText(), _patient);
					 }else if (_patientName.length() > 0){
						 ControllerFacade.getInstance().getNewAppointmentController().newCalendarEvent(_calendar, _typeBox.getSelectionModel().getSelectedItem(), _startDate, _endDate, _resonText.getText(), _patientName);
					 }
					 _okClicked = true;
					 _dialogStage.close();
				} catch (SaveException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Save Error");
					alert.setHeaderText("Save Error");
					alert.setContentText("A problem has occured. Appoint has not been saved.");
					e.printStackTrace();
				}
			}
		 }
	 }
	 /**
	 * @return
	 */
	private boolean inputIsValid() {
		String errorMessage = "";

		if (_patient == null){
			if((_patientName == null)||(_patientName.length() < 1)){
				errorMessage += "No Patient selected\n";
			}
		}
		if(_datePicker.getValue()== null){
				errorMessage += "No Day selected\n";
		}
		if (_calendar == null) {
			errorMessage += "No doctor selected!\n";
		}
		if (_typeBox.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No Event Type selected!\n";
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(_dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
	private boolean isEventAlreadyTaken(){
		calcDate();
		try {
			if(ControllerFacade.getInstance().getNewAppointmentController().isDateAlreadyTaken(_calendar,_startDate , _endDate)){
				return true;
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return false;
	}
	/**
	 * returns true if the user want to create the appointment, although there is an overlaping
	 * also returns true if there is no problem (no overlaping)
	 * @return
	 */
	private boolean continueWithOverlaping(){
		if(isEventAlreadyTaken()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Overlaping Appointment");
			alert.setHeaderText("Overlapping Appointment");
			alert.setContentText("The new appointment overlaps with another appointment. Do you want to continue?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			//	alert.close();
				return true;

			} else {
				//alert.close();
				return false;    
			}
		}
		return true;
	}
	private boolean isEventInWorkingHour(){
		calcDate();
		if(ControllerFacade.getInstance().getNewAppointmentController().isInWorkingHours(_calendar, _startDate, _endDate)){
			return true;
		}
		return false;
	}
	/**
	 * returns true if the user wants to create an CalenderEvent, although the appointment is not in workinghour
	 * return also true if there is no problem (Appointment is in workinghour)
	 * @return
	 */
	private boolean continueWhenNotInWorkingHour(){
		if(!isEventInWorkingHour()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Outside of workinghours");
			alert.setHeaderText("Appointment outside of workinghours");
			alert.setContentText("The new appointment is outside of workinghours. Do you want to continue?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			//	alert.close();
				return true;

			} else {
			//	alert.close();
				return false;    
			}
		}
		return true;
	}
	@FXML
	private void doctorBoxControl(){
		_calendar = _doctorBox.getSelectionModel().getSelectedItem();
	}

	@FXML
	 private void cancelButtonControl(){
		 _dialogStage.close();
	 }

}
