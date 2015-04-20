package at.itb13.oculus.presentation.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.CalendarEvent;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 17.04.2015
 */
public class PatientRecordController implements ControllerMainSetter {

	@FXML
	private TextField _ssnTextField;
	@FXML
	private TextField _firstNameField;
	@FXML
	private TextField _lastNameField;
	
	@FXML
	private Label _firstNameLabel;
	@FXML
	private Label _lastNameLabel;
	@FXML
	private Label _SSNLabel;
	@FXML
	private Label _birthdayLabel;
	@FXML
	private Label _genderLabel;
	@FXML
	private Label _docLabel;
	@FXML
	private Label _streetLabel;
	@FXML
	private Label _postalCodeLabel;
	@FXML
	private Label _cityLabel;
	@FXML
	private Label _countryISOLabel;
	@FXML
	private Label _phoneLabel;
	@FXML
	private Label _emailLabel;
	@FXML
	private Label _alergiesLabel;
	@FXML
	private Label _childhoodAilmentsLabel;
	@FXML
	private Label _medicineintolerranceLabel;
	
	// Appointments Tab
	@FXML
	private TableView<CalendarEventRO> _appointmentTable;
	@FXML
	private TableColumn<CalendarEventRO, String> _stateColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _dateColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _timeStartColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _timeEndColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _eventTypeColumn;
	@FXML
	private TableColumn<CalendarEventRO, String> _descriptionColumn;
	
	private ObservableList<CalendarEventRO> _appointmentsList = FXCollections.observableArrayList();
	
	//general Attributs
	private OculusMain _main;
	
	//general Methods
	public void setMain(OculusMain main) {
	       _main = main;
	        
	 }
	 @FXML
	private void initialize() {
		 showPatientMasterData(null);
		 showAnamanesis(null);
		 initAppointmentsTab();
	}
	 
	public void showPatientMasterData(PatientRO value) {
		if (value != null) {
            // Fill the labels with info from the person object.
        	_firstNameLabel.setText(value.getFirstName());
        	_lastNameLabel.setText(value.getLastName());
        	_SSNLabel.setText(value.getSocialInsuranceNr());
        	_birthdayLabel.setText((value.getBirthDay() == null) ? "" : value.getBirthDay().toString());
        	_docLabel.setText(value.getDoctor().getUser().getFirstName() + " " +value.getDoctor().getUser().getLastName());
        	_genderLabel.setText(value.getGender());	            
        	_streetLabel.setText(value.getStreet());
        	_postalCodeLabel.setText(value.getPostalCode());
        	_cityLabel.setText(value.getCity());
        	_countryISOLabel.setText(value.getCountryIsoCode());
        	_phoneLabel.setText(value.getPhone());
        	_emailLabel.setText(value.getEmail());	           
        } else {
            // Person is null, remove all the text.
            _firstNameLabel.setText("");
            _lastNameLabel.setText("");
            _SSNLabel.setText("");
            _birthdayLabel.setText("");
            _docLabel.setText("");
            _genderLabel.setText("");	            
            _streetLabel.setText("");
            _postalCodeLabel.setText("");
            _cityLabel.setText("");
            _countryISOLabel.setText("");
            _phoneLabel.setText("");
            _emailLabel.setText("");	
        }
	}
	
	public void showAnamanesis(PatientRO value) {
        if (value != null) {
            // Fill the labels with info from the person object.
        	_alergiesLabel.setText(value.getAllergy());
        	_childhoodAilmentsLabel.setText(value.getChildhoodAilments());
        	_medicineintolerranceLabel.setText(value.getMedicineIntolerance());
                      
        } else {
            // Person is null, remove all the text.
        	_alergiesLabel.setText("");
        	_childhoodAilmentsLabel.setText("");
        	_medicineintolerranceLabel.setText("");	
        }
    }
	 
	public void showAppointments(PatientRO value) {
		_appointmentsList.clear();
		_appointmentsList.addAll(
				ControllerFacade.getInstance().getPatientController().getAllCalendarEventsSorted(value));
	}
	 
	private void initAppointmentsTab() {
		// double click -> show appointment details
		_appointmentTable.setRowFactory( tv -> {
			TableRow<CalendarEventRO> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            CalendarEventRO rowData = row.getItem();
		            _main.showAppointment(rowData);
		        }
		    });
		    return row ;
		});
		
		_appointmentTable.setItems(_appointmentsList);
		
		_stateColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("open"));	// TODO: Don't write "true/false", but more meaningful string. See also AppointmentsController.initialize()->_appointmentTable.setRowFactory()
		
		_dateColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventStart().getYear() + "-" 
						+ event.getValue().getEventStart().getMonthValue() + "-" 
						+ event.getValue().getEventStart().getDayOfMonth());
			}
		});
		
		_timeStartColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventStart().getHour()
						+ ":"
						+ event.getValue().getEventStart().getMinute());
			}
		});
		
		_timeEndColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventEnd().getHour()
						+ ":"
						+ event.getValue().getEventEnd().getMinute());
			}
		});
		
		_eventTypeColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventtype().getEventTypeName());
			}
		});
		
		_descriptionColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("description"));
	}
}
