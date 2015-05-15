package at.itb13.oculus.presentation.view.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.calendar.CalendarController;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.exceptions.SaveException;
import at.itb13.oculus.application.receptionist.PatientSearch;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.util.CalendarEventTypeStringConverter;
import at.itb13.oculus.presentation.util.CalendarStringConverter;
import at.itb13.oculus.presentation.util.DoctorStringConverter;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.PatientRecordController;
import at.itb13.teamD.domain.interfaces.ICalendar;
import at.itb13.teamD.domain.interfaces.IEventType;
import at.itb13.teamD.domain.interfaces.IPatient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 04.05.2015
 */
public class NewAppointmentController {
	
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
	
	private boolean okClicked = false;
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
                		_selectedpatient.setText(newValue.getFirstName() + " " + newValue.getLastName() + " " + newValue.getDateOfBirth());
                		_patientRecordButton.setDisable(false);
        				_patient = _patientTableView.getSelectionModel().getSelectedItem();
        				_patientName = "";
                	}
                });
		
		setItemsToDoctorBox();
		setItemsToTypeBox();
		initSpinner();
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
	 * fills the combobox doctor with calendars
	 */
	private void setItemsToDoctorBox() {
		_doctorBox.setConverter(new CalendarStringConverter());
		List<ICalendar> calContrs = ControllerFacade.getInstance().getNewAppointmentController().getAllCalendars(); 		
		for(ICalendar c : calContrs){
			_doctorBox.getItems().add(c);
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
        	if((newValue!= null)&&(!newValue.equals(""))){
        		_startTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(newValue));
        	}else{
        		_startTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(oldValue));

        	}
        });
                    
        _startTimeSpinnerMin.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        	if((newValue!= null)&&(!newValue.equals(""))){
        		_startTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(newValue));
        	}else{
        		_startTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(oldValue));

        	}
        });
        	
        _endTimeSpinnerHour.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        	if((newValue!= null)&&(!newValue.equals(""))){
        		_endTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(newValue));
        	}else{
        		_endTimeSpinnerHour.getValueFactory().setValue(Integer.parseInt(oldValue));

        	}
        });
        	
        _endTimeSpinnerMin.getEditor().textProperty().addListener((observable, oldValue, newValue) ->   {
        	if((newValue!= null)&&(!newValue.equals(""))){
        		_endTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(newValue));
        	}else{
        		_endTimeSpinnerMin.getValueFactory().setValue(Integer.parseInt(oldValue));

        	}
        });
        	
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
		return okClicked;
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
	 @SuppressWarnings("unchecked")
	@FXML
	 private void searchControl(){
		 clearPatientData();
		 PatientSearch p = ControllerFacade.getInstance().getPatientSearch();
		 List<IPatient> patients = new ArrayList<>();
		 try {			
			patients =  (List<IPatient>) p.searchPatient(_patientSearchField.getText());
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
			loader.setLocation(OculusMain.class
					.getResource("view/calendar/SimplePatientRecord.fxml"));
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

		if (_patient == null
				&& _patientName.length() < 1) {
			errorMessage += "No Patient selected\n";
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
			alert.setHeaderText("Overlapping Appointment");
			alert.setContentText("The new Appointment overlaps an other Appointment. Do you want to continue?");
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
			alert.setHeaderText("Appointment not in workinghour");
			alert.setContentText("The new Appointment is not in workinghour. Do you want to continue?");
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
