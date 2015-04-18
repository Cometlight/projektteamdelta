package at.itb13.oculus.presentation.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		 showPatientMasterData(null);
		 showAnamanesis(null);
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
}
