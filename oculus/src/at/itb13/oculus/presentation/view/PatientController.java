package at.itb13.oculus.presentation.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.domain.CalendarEventRO;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 07.04.2015
 */
public class PatientController {
	private static final Logger _logger = LogManager.getLogger(PatientController.class.getName());

	//Tab Patient Attributs
	@FXML
	private TableView<PatientRO> _patientTable;	
	@FXML
	private TableColumn<PatientRO, String> _firstNameColumn;	
	@FXML
	private TableColumn<PatientRO, String> _lastNameColumn;	
	@FXML
	private TableColumn<PatientRO, String> _SSNColumn;
	
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
	
	//general Attributs
	private OculusMain _main;
	
	//general Methods
	 public void setMain(OculusMain main) {
	        _main = main;

	        
	  }
	 
	 
	 @FXML
	 private void initialize() {
	        // Initialize the person table with the three columns.
//	        _firstNameColumn.setCellValueFactory(	// TODO: OLD, -> DELETE
//	                cellData -> cellData.getValue().firstNameProperty());
//	        _lastNameColumn.setCellValueFactory(
//	                cellData -> cellData.getValue().lastNameProperty());
//	        _SSNColumn.setCellValueFactory(
//	        		cellData -> cellData.getValue().SocialInsuranceNrProperty());
	        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("firstName"));
	        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("lastName"));
	        _SSNColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("socialInsuranceNr"));
		 
	        // Clear person details.
	        showPatientMasterData(null);
	        showAnamanesis(null);

	        // Listen for selection changes and show the person details when changed.
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showPatientMasterData(newValue));
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showAnamanesis(newValue));
	    }
	 private void clearPatientTable(){		        	

	        // Clear person details.
	        showPatientMasterData(null);
	        showAnamanesis(null);
	        
	        _main.clearPatientData();

	 }
	 
	 private void showPatientMasterData(PatientRO value) {
	        if (value != null) {
	            // Fill the labels with info from the person object.
	        	_firstNameLabel.setText(value.getFirstName());
	        	_lastNameLabel.setText(value.getLastName());
	        	_SSNLabel.setText(value.getSocialInsuranceNr());
	        	_birthdayLabel.setText((value.getBirthDay() == null) ? "" : value.getBirthDay().toString());
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
	            _genderLabel.setText("");	            
	            _streetLabel.setText("");
	            _postalCodeLabel.setText("");
	            _cityLabel.setText("");
	            _countryISOLabel.setText("");
	            _phoneLabel.setText("");
	            _emailLabel.setText("");	
	        }
	    }
	 private void showAnamanesis(PatientRO value) {
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
	 
	 /**
	  * Controls the "search" Button, for searching by Social Insurancel Number.
	  * It gets a Patient from the PatientSearch Class and creates a new Patient with Property.
	  * The Patient Data are shown in the GUI
	  */
	 @FXML
	 private void searchByNumberControl(){
		 clearPatientTable();
		 PatientSearch p = new PatientSearch();
		 try {			
			PatientRO pa = p.searchPatientBySocialInsuranceNr(_ssnTextField.getText());
			showPatientMasterData(pa);
			showAnamanesis(pa);
			_main.addPatientData(pa);	// TODO: Why must a patient be stored in _main?
			_patientTable.setItems(_main.getPatientData());			 

		} catch (InvalidInputException e) {
			_logger.info(e);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Patient found");
			alert.setHeaderText("Sorry, the Patient could not be found.");
	        alert.setContentText("Please make sure you have entered the right Social Insurance Number!");
	        alert.showAndWait();
		}
	 }
	 
	 /**
	  * Controls the "search" Button, for searching by Name.
	  * It gets a List of Patients from the PatientSearch Class and creates new Patients with Property.
	  * The Patient Data are shown in the GUI
	  */
	 @FXML
	 private void searchByNameControl(){
		 clearPatientTable();
		 PatientSearch p = new PatientSearch();
		 List<Patient> patients = new ArrayList<>();
		 try {			
			patients = p.searchPatientByName(_firstNameField.getText(), _lastNameField.getText());
			if(patients.size() > 0){
//				for(Patient pa : patients){	// TODO: OLD, -> Delete
//					_main.addPatientData(new PatientWithProperties2(pa));
//				}
				patients.forEach(_main::addPatientData);
				 _patientTable.setItems(_main.getPatientData());
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Patient found");
				alert.setHeaderText("Sorry, the Patient could not be found.");
		        alert.setContentText("Please make sure you have entered the right name");
		        alert.showAndWait();
			}

		} catch (InvalidInputException e) {			
			// TODO (eg. _logger.error(e) + alerts
			//e.printStackTrace();
		}
	 }
	 
	 @FXML
	 private void newPatientControl(){
		
	
		_main.showNewPatientDialog();
//		if (okClicked) {
//		    _main.getPersonData().add();
//		}
		    
	 }
}
