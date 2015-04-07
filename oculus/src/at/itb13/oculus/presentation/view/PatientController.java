package at.itb13.oculus.presentation.view;

import at.itb13.oculus.domain.Patient;
import at.itb13.oculus.domain.PatientWithProperties;
import at.itb13.oculus.presentation.OculusMain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 07.04.2015
 */
public class PatientController {

	@FXML
	private TableView<PatientWithProperties> _patientTable;	
	@FXML
	private TableColumn<PatientWithProperties, String> _firstNameColumn;	
	@FXML
	private TableColumn<PatientWithProperties, String> _lastNameColumn;	
	@FXML
	private TableColumn<PatientWithProperties, String> _SSNColumn;
	
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
	
	private OculusMain _main;
	
	 public void setMain(OculusMain main) {
	        _main = main;

	        // Add observable list data to the table
	        _patientTable.setItems(main.getPatientData());
	    }
	 @FXML
	 private void initialize() {
	        // Initialize the person table with the two columns.
	        _firstNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().firstNameProperty());
	        _lastNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().lastNameProperty());
	        _SSNColumn.setCellValueFactory(
	        		cellData -> cellData.getValue().SocialInsuranceNrProperty());

	        // Clear person details.
	        showPatientDetails(null);

	        // Listen for selection changes and show the person details when changed.
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showPatientDetails(newValue));
	    }
	 
	 private void showPatientDetails(PatientWithProperties value) {
	        if (value != null) {
	            // Fill the labels with info from the person object.
	            _firstNameLabel.setText(value.getFirstName());
	            _lastNameLabel.setText(value.getLastName());
	            _SSNLabel.setText(value.getSocialInsuranceNr());
	            _birthdayLabel.setText(value.getBirthDay().toString());
	            _genderLabel.setText(value.getGender());	            
	            _streetLabel.setText(value.getStreet());
	     //       _postalCodeLabel.setText(Integer.toUnsignedString((newValue.getPostalCode())));
	            _cityLabel.setText(value.getCity());
	            _countryISOLabel.setText(value.getCountryIsoCode());
	            _phoneLabel.setText(value.getPhone());
	            _emailLabel.setText(value.getEmail());	           
	        } else {
	            // Person is null, remove all the text.
	            _firstNameLabel.setText("");
	            _lastNameLabel.setText("");
	            _streetLabel.setText("");
	            _postalCodeLabel.setText("");
	            _cityLabel.setText("");
	            _birthdayLabel.setText("");
	        }
	    }
}
