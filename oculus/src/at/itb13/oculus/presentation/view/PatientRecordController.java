package at.itb13.oculus.presentation.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 17.04.2015
 */
public class PatientRecordController {

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
	private TextArea _allergiesArea;
	@FXML
	private TextArea _childhoodAilmentsLabel;
	@FXML
	private TextArea _medicineintolerranceLabel;
	@FXML
	private Button _editGeneralButton;
	@FXML
	private Button _editAnamnesisButton;
	
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
	
	private PatientRO _patient;
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
		 _editGeneralButton.setVisible(false);
		 _editAnamnesisButton.setVisible(false);
		 initAppointmentsTab();
	}
	
	 /**
	  * shows the patient general data of the specific patient
	  * @param value
	  */
	public void showPatientMasterData(PatientRO value) {
		if (value != null) {
			_editGeneralButton.setVisible(true);
			_editAnamnesisButton.setVisible(true);
			_patient = value;
            // Fill the labels with info from the person object.
        	_firstNameLabel.setText(value.getFirstName());
        	_lastNameLabel.setText(value.getLastName());
        	_SSNLabel.setText(value.getSocialInsuranceNr());
        	_birthdayLabel.setText((value.getBirthDay() == null) ? "" : value.getBirthDay().toString());
        	_docLabel.setText(value.getDoctor().getUser().getFirstName() + " " +value.getDoctor().getUser().getLastName());
        	_genderLabel.setText(value.getGender().toString());	            
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
	
	/**
	 * shows the anamnesis of the specific patient
	 * @param value
	 */
	public void showAnamanesis(PatientRO value) {
        if (value != null) {
        	_patient = value;
            // Fill the labels with info from the person object.
        	
        	_allergiesArea.setText(value.getAllergy());
        	_childhoodAilmentsLabel.setText(value.getChildhoodAilments());
        	_medicineintolerranceLabel.setText(value.getMedicineIntolerance());
                      
        } else {
            // Person is null, remove all the text.
        	_allergiesArea.setText("");
        	_childhoodAilmentsLabel.setText("");
        	_medicineintolerranceLabel.setText("");	
        }
    }
	 
	/**
	 * shows the appointments of the specific patient
	 * @param value
	 */
	public void showAppointments(PatientRO value) {
		_appointmentsList.clear();
		if(value != null && value.getCalendarevents() != null) {
			_appointmentsList.addAll(
					ControllerFacade.getInstance().getWelcomeAtReception().getAllCalendarEventsSorted(value));
		}
	}
	 /**
	  * initializes the appointment tab. 
	  */
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
		    return row;
		});
		
		_appointmentTable.setItems(_appointmentsList);
		_dateColumn.setSortType(SortType.ASCENDING);
		_appointmentTable.getSortOrder().add(_dateColumn);
		
		_stateColumn.setCellValueFactory(new PropertyValueFactory<CalendarEventRO, String>("open"));
		
		_stateColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				String str;
				if(event.getValue().isOpen()) {
					if(event.getValue().getEventEnd().isBefore(LocalDateTime.now())) {
						str = "missed";
					} else {
						str = "open";
					}
				} else {
					str = "closed";
				}
				return new SimpleStringProperty(str);
			}
		});
		
		_dateColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		});
		
		_timeStartColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventStart().format(DateTimeFormatter.ofPattern("HH:mm")));
			}
		});
		
		_timeEndColumn.setCellValueFactory(new Callback<CellDataFeatures<CalendarEventRO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CalendarEventRO, String> event) {
				return new SimpleStringProperty(
						event.getValue().getEventEnd().format(DateTimeFormatter.ofPattern("HH:mm")));
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
	/**
	 * is called when the edit button on the anamnesis screen is pushed
	 */
	@FXML
	private void handleEditAnamnesis(){
		_main.showEditAnamnesis(_patient);
		_patient = ControllerFacade.getPatientSelected();
		showAnamanesis(_patient);
	}
	/**
	 * is called when the edit button in the general information screen is pushed
	 */
	@FXML
	private void handleEditGeneral(){
		_main.showNewPatientDialog(_patient);
		if(ControllerFacade.getPatientSelected() != null) {
			_patient = ControllerFacade.getPatientSelected();
		}
		showPatientMasterData(_patient);
	}
}
