package at.itb13.oculus.presentation.view.calendar;

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
import at.itb13.oculus.domain.interfaces.ICalendar;
import at.itb13.oculus.domain.interfaces.IEventType;
import at.itb13.oculus.domain.interfaces.IPatient;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.util.CalendarEventTypeStringConverter;
import at.itb13.oculus.presentation.util.CalendarStringConverter;
import at.itb13.oculus.presentation.util.DoctorStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	private Button _deletePatientButton;
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
                (observable, oldValue, newValue) -> _selectedpatient.setText(newValue.getFirstName() + " " + newValue.getLastName() + " " + newValue.getDateOfBirth()));
		
		
		setItemsToDoctorBox();
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
		List<ICalendar> calContrs = ControllerFacade.getInstance().getNewAppointmentController().getAllCalendars(); 		for(ICalendar c : calContrs){
		_doctorBox.getItems().add(c);
		}
	}
	
	private void setItemsToTypeBox(){
		_typeBox.setConverter(new CalendarEventTypeStringConverter());
		_typeBox.getItems().addAll(ControllerFacade.getInstance().getAllEventTypes());
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
		}
	}

	/**
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		
	}
	
	public boolean isOkClicked() {
		return okClicked;
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
	 
	 @FXML
	 private void saveButtonControl(){
		 if(inputIsValid()){
			 LocalTime startTime = LocalTime.of(_startTimeSpinnerHour.getValue(), _startTimeSpinnerMin.getValue());
			 LocalDateTime start = LocalDateTime.of(_datePicker.getValue(), startTime);
			 
			 LocalTime endTime = LocalTime.of(_endTimeSpinnerHour.getValue(), _endTimeSpinnerMin.getValue());
			 LocalDateTime end = LocalDateTime.of(_datePicker.getValue(), endTime);
					
			 try {
				 if(_patientTableView.getSelectionModel().getSelectedItem() != null){
					 ControllerFacade.getInstance().getNewAppointmentController().newCalendarEvent(_doctorBox.getSelectionModel().getSelectedItem(), _typeBox.getSelectionModel().getSelectedItem(), start, end, _resonText.getText(), _patientTableView.getSelectionModel().getSelectedItem());
				 }else{
					 ControllerFacade.getInstance().getNewAppointmentController().newCalendarEvent(_doctorBox.getSelectionModel().getSelectedItem(), _typeBox.getSelectionModel().getSelectedItem(), start, end, _resonText.getText(), _selectedpatient.getText());
				 }
			} catch (SaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 /**
	 * @return
	 */
	private boolean inputIsValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@FXML
	 private void cancelButtonControl(){
		 _dialogStage.close();
	 }

}
