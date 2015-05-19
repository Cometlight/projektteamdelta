package at.itb13.oculus.presentation.view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.teamD.domain.interfaces.IPatient;
import at.itb13.teamF.adapter.PatientAdapter;
import at.oculus.teamf.presentation.view.ExaminationController;
import at.oculus.teamf.presentation.view.models.Model;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 17.04.2015
 */
public class PatientRecordController {
	private static final Logger _logger = LogManager.getLogger(PatientRecordController.class.getName());
	
	@FXML
	private TextField _ssnTextField;
	@FXML
	private TextField _firstNameField;
	@FXML
	private TextField _lastNameField;
	
	@FXML
	private Label _personalLabel;
	@FXML
	private Label _addressLabel;
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
	
	@FXML
	private BorderPane _examinationProtocolBorderPane;
	private ExaminationController _examinationController;
	
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
	 
	private void initExaminationProtocolsTab() {
		try {// set logged in user in model
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("../../../oculus/teamf/presentation/view/fxml/ExaminationTab.fxml"));
			_examinationProtocolBorderPane.setCenter((AnchorPane) loader.load());

			// Give the controller access to the main app.
			_examinationController = loader.getController();

		} catch (IOException ex) {
			_logger.error("Fail: initExaminationTab",ex);
		}
	}
	
	public void setPatientRO(PatientRO patient){
		 _patient = patient;
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
			String personal = new String();
			personal = personal + ((value.getFirstName()== null) ? "" : value.getFirstName()+" ");
			personal = personal +((value.getLastName()== null) ? "-\n" : value.getLastName()) + "\n";
			personal = personal + ((value.getSocialInsuranceNr()== null) ? "-" : value.getSocialInsuranceNr()) +"\n";
			personal = personal + ((value.getDateOfBirth()== null) ? "-\n" : value.getDateOfBirth().toString() + "\n");
			personal = personal + value.getGender().name();
			_personalLabel.setText(personal);
        	_docLabel.setText(value.getDoctor().getUser().getFirstName() + " " +value.getDoctor().getUser().getLastName());           
        	String address = new String();
        	address = address + ((value.getStreet()== null)? "-\n" : value.getStreet()+"\n");
        	address = address + ((value.getPostalCode()==null)?"": value.getPostalCode()+" ");
        	address = address + ((value.getCity()== null)?"-\n": value.getCity()+"\n");
        	address = address + ((value.getCountryIsoCode()==null)? "-\n" : value.getCountryIsoCode());
        	_addressLabel.setText(address);
        	_phoneLabel.setText(value.getPhone());
        	_emailLabel.setText(value.getEmail());	           
        } else {
        	
            // Person is null, remove all the text.
        	_personalLabel.setText("");
            _docLabel.setText("");    
            _addressLabel.setText("");
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
	
	public void showExaminationProtocols(PatientRO value) {
//		Model.getInstance().setLoggedInUser(null); 	// FIXME: statt null einen user übergeben; anderes team hat schon ein simples "login" system..
		PatientAdapter patientAdapter = new PatientAdapter((Patient)value);
//		Model.getInstance()._patient = patientAdapter;
		Model.getInstance().setPatient(patientAdapter); 		// FIXME
		Model.getInstance().getTabModel().setTabinitpatient(patientAdapter);
		initExaminationProtocolsTab();
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
						event.getValue().getEventType().getEventTypeName());
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
