package at.itb13.oculus.presentation.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.control.ToggleGroup;
import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.domain.Doctor;
import at.itb13.oculus.domain.Patient.Gender;
import at.itb13.oculus.domain.readonlyinterfaces.DoctorRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.util.DoctorSringConverter;
import at.itb13.oculus.technicalServices.GenericDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 10.04.2015
 */
public class NewPatientController {
	private static final Logger _logger = LogManager.getLogger(NewPatientController.class.getName());
	
	@FXML
	private TextField _firstNameField;
	@FXML
	private TextField _lastNameField;
	@FXML
	private TextField _SINField;
	@FXML
	private TextField _birthdayField;

	private ToggleGroup _genderGroup;

	@FXML
	private RadioButton _maleRadioButton;
	@FXML
	private RadioButton _femaleRadioButton;
	private Gender _gender;
	@FXML
	private ComboBox<DoctorRO> _doctorBox;

	private List<DoctorRO> _doctorsList;	// TODO FIXME delete?
	private DoctorRO _doctor;
	@FXML
	private TextField _streetField;
	@FXML
	private TextField _postalCodeField;
	@FXML
	private TextField _cityField;
	@FXML
	private TextField _countryISOField;
	@FXML
	private TextField _phoneField;
	@FXML
	private TextField _emailField;

	private LocalDate _date;

	private Stage _dialogStage;

	private boolean okClicked = false;
	
	
	private PatientRO _patient;	
	private Boolean _isNewPatient = true;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		setItemsToDoctorBox();

		_gender = Gender.F;
		_genderGroup = new ToggleGroup();
		_femaleRadioButton.setToggleGroup(_genderGroup);
		_maleRadioButton.setToggleGroup(_genderGroup);
		_femaleRadioButton.setSelected(true);

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;

		// Set the application icon.
		_dialogStage.getIcons().add(new Image("file:resources/images/eye.png"));
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	public PatientRO getPatient(){
		return _patient;
	}
	/**
	 * TODO: Description
	 * @param patient
	 */
	public void setPatientRO(PatientRO patient){
		if(patient != null){
			_isNewPatient = false;
			_patient = patient;
			_firstNameField.setText(_patient.getFirstName());
			_lastNameField.setText(_patient.getLastName());
			_SINField.setText(_patient.getSocialInsuranceNr());
			_birthdayField.setText(_patient.getBirthDay().toString());
			if(_patient.getGender().equals("M")){
				_maleRadioButton.setSelected(true);
			}
			_cityField.setText(_patient.getCity());
			_postalCodeField.setText(_patient.getPostalCode());
			_countryISOField.setText(_patient.getCountryIsoCode());
			_streetField.setText(_patient.getStreet());
			_phoneField.setText(_patient.getPhone());
			_emailField.setText(_patient.getEmail());
//			DoctorRO doc = null;
//			for(DoctorRO d : _doctorsList){
//				if(_patient.getDoctor().equals(d)){
//					doc = d;
//				}
//			}
//			_doctorBox.setValue(doc);
		}
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
    private void handleOk() {
		try {
			if (isInputValid()) {
				if(_isNewPatient){
					//creating a new Patient and save it in the database
					try {
						_patient = ControllerFacade.getInstance().getNewPatient()
								.createPatient(_doctor, _SINField.getText(), _firstNameField.getText(), _lastNameField.getText(),_date, _gender, _streetField.getText(), _postalCodeField.getText(),_cityField.getText(), _countryISOField.getText(), _phoneField.getText(), _emailField.getText());
					} catch (InvalidInputException e) {
						_logger.error(e);
						e.printStackTrace();	// TODO no stacktrace
					}
				} else{
					try {
						ControllerFacade.getInstance().getWelcomeAtReception()
							.updatePatient(_patient, _doctor, _SINField.getText(), _firstNameField.getText(), _lastNameField.getText(),_date, _gender, _streetField.getText(), _postalCodeField.getText(),_cityField.getText(), _countryISOField.getText(), _phoneField.getText(), _emailField.getText());
					} catch (InvalidInputException e) {
						_logger.error(e);
						e.printStackTrace();	// TODO no stacktrace
					}
				}
				
				okClicked = true;
			    _dialogStage.close();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		_dialogStage.close();
	}

	@FXML
	private void handleGender() {
		if (_maleRadioButton.isSelected()) {
			_gender = Gender.M;
		} else if (_femaleRadioButton.isSelected()) {
			_gender = Gender.F;
		}

	}

	private void setItemsToDoctorBox() {
		_doctorBox.setConverter(new DoctorSringConverter());
		_doctorBox.getItems().addAll(ControllerFacade.getInstance().getWelcomeAtReception().getDoctorList());

	}

	@FXML
	private void handleDoctorComboBox() {

		_doctor = _doctorBox.getSelectionModel().getSelectedItem();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() throws ParseException {
		String errorMessage = "";

		if (_firstNameField.getText() == null
				|| _firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (_lastNameField.getText() == null
				|| _lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (_birthdayField.getText() != null
				&& _birthdayField.getText().length() > 0) {
			if (_birthdayField.getText().matches(
					"([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
				_date = LocalDate.parse(_birthdayField.getText());
			} else {
				errorMessage += "No valid Birthday! Please make sure that you have choose the correct date format!\n";
			}

		} else {
			_date = null;
		}
		if (_doctorBox.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No doctor selected!\n";
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
	
	@FXML
	private void onActionSINField() {
		String sin = _SINField.getText();
		if(ControllerFacade.getInstance().getWelcomeAtReception().isSocialInsuranceNrValid(sin)) {
			try {
				PatientRO patientRO = ControllerFacade.getInstance().getPatientSearch().searchPatientBySocialInsuranceNr(sin);

				if(patientRO != null) {	// Patient with SIN already exists
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(_dialogStage);
					alert.setTitle("Patient already exists");
					alert.setHeaderText("A patient with the social insurance nr '" + sin + "' already exists!");
					alert.setContentText("Patient information: "
							+ "first name: '" + patientRO.getFirstName()
							+ "', last name: '" + patientRO.getLastName()
							+ "', birthday: '" + patientRO.getBirthDay() + "'");
	
					alert.showAndWait();
				}
			} catch (InvalidInputException e) {
				_logger.warn(e);
			}
		}
	}

}
