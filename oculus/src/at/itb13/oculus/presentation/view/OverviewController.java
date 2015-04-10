package at.itb13.oculus.presentation.view;

import java.util.ArrayList;


import java.util.List;

import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.presentation.OculusMain;
import at.itb13.oculus.presentation.model.PatientWithProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 07.04.2015
 */
public class OverviewController {

	//Tab Patient
	@FXML
	private TableView<PatientWithProperties> _patientTable;	
	@FXML
	private TableColumn<PatientWithProperties, String> _firstNameColumn;	
	@FXML
	private TableColumn<PatientWithProperties, String> _lastNameColumn;	
	@FXML
	private TableColumn<PatientWithProperties, String> _SSNColumn;
	
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
	private OculusMain _main;
	
	 public void setMain(OculusMain main) {
	        _main = main;

	        // Add observable list data to the table
	        _patientTable.setItems(main.getPatientData());
	        
	    }
	 @FXML
	 private void initialize() {
	        // Initialize the person table with the three columns.
	        _firstNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().firstNameProperty());
	        _lastNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().lastNameProperty());
	        _SSNColumn.setCellValueFactory(
	        		cellData -> cellData.getValue().SocialInsuranceNrProperty());

	        // Clear person details.
	        showPatientMasterData(null);
	        showAnamanesis(null);

	        // Listen for selection changes and show the person details when changed.
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showPatientMasterData(newValue));
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showAnamanesis(newValue));
	    }
	 private void clear(){
		        	

	        // Clear person details.
	        showPatientMasterData(null);
	        showAnamanesis(null);
	        
	        _main.clearPatientData();

	 }
	 
	 private void showPatientMasterData(PatientWithProperties value) {
	        if (value != null) {
	            // Fill the labels with info from the person object.
	            _firstNameLabel.setText(value.getFirstName());
	            _lastNameLabel.setText(value.getLastName());
	            _SSNLabel.setText(value.getSocialInsuranceNr());
	       //     _birthdayLabel.setText(value.getBirthDay().toString());
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
	       //     _birthdayLabel.setText("");
	            _genderLabel.setText("");	            
	            _streetLabel.setText("");
	          _postalCodeLabel.setText("");
	            _cityLabel.setText("");
	            _countryISOLabel.setText("");
	            _phoneLabel.setText("");
	            _emailLabel.setText("");	
	        }
	    }
	 private void showAnamanesis(PatientWithProperties value) {
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
	 /*
	  * Controls the "search" Button, for searching by Social Insurancel Number.
	  * It gets a Patient from the PatientSearch Class and creates a new Patient with Property.
	  * The Patient Data are shown in the GUI
	  */
	 @FXML
	 private void searchByNumberControl(){
		
		 PatientSearch p = new PatientSearch();
		 try {			
			PatientWithProperties pa = new PatientWithProperties(p.searchPatientBySocialInsuranceNr(_ssnTextField.getText()));
			showPatientMasterData(pa);
			showAnamanesis(pa);
			_main.addPatientData(pa);
			_patientTable.setItems(_main.getPatientData());			 

		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Patient found");
			alert.setHeaderText("Sorry, the Patient could not be found.");
	        alert.setContentText("Please make sure you have entered the right Social Insurance Number!");
	        alert.showAndWait();
	        // e.printStackTrace();
		}
	 }
	 /*
	  * Controls the "search" Button, for searching by Name.
	  * It gets a List of Patients from the PatientSearch Class and creates new Patients with Property.
	  * The Patient Data are shown in the GUI
	  */
	 @FXML
	 private void searchByNameControl(){
		 clear();
		 PatientSearch p = new PatientSearch();
		 List<Patient> patients = new ArrayList<>();
		 try {			
			patients = p.searchPatientByName(_firstNameField.getText(), _lastNameField.getText());
			if(patients.size() > 0){
				for(Patient pa : patients){
					_main.addPatientData(new PatientWithProperties(pa));
				}
				 _patientTable.setItems(_main.getPatientData());
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Patient found");
				alert.setHeaderText("Sorry, the Patient could not be found.");
		        alert.setContentText("Please make sure you have entered the right name");
		        alert.showAndWait();
			}

		} catch (InvalidInputException e) {			
			
			//e.printStackTrace();
		}
	 }
}
