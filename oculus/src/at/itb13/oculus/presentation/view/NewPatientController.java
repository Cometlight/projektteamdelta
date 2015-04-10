package at.itb13.oculus.presentation.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	@FXML
	private TextField _firstNameField;
	@FXML
	private TextField _lastNameField;
	@FXML
	private TextField _SINField;
	@FXML
	private TextField _birthdayField;
	@FXML
	private TextField _genderField;
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
	
	private Stage _dialogStage;
  //  private PatientWithProperties2 _patient;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        
        	//creating a new Patient and save it in the database

            okClicked = true;
            _dialogStage.close();
        }
    }
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        _dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (_firstNameField.getText() == null || _firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (_lastNameField.getText() == null || _lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if(_SINField.getText() == null || _SINField.getText().length() == 0){
        	errorMessage += "No valid Social Insurance Number!\n";
        }
        if(_birthdayField.getText() == null || _birthdayField.getText().length() == 0){
        	errorMessage += "No valid birthdate!\n";
        }
        if(_genderField.getText() == null || _genderField.getText().length() == 0){
        	errorMessage += "No valid gender!\n";
        }
        if(_streetField.getText() == null || _streetField.getText().length() == 0){
        	errorMessage += "No valid street!\n";
        }
        if(_postalCodeField.getText() == null || _postalCodeField.getText().length() == 0){
        	errorMessage += "No valid postal code!\n";
        }
        if(_countryISOField.getText() == null || _countryISOField.getText().length() == 0){
        	errorMessage += "No valid country!\n";
        }
        if(_cityField.getText() == null || _cityField.getText().length() == 0){
        	errorMessage += "No valid city!\n";
        }
        if(_phoneField.getText() == null || _phoneField.getText().length() == 0){
        	errorMessage += "No valid phone number!\n";
        }
        if(_emailField.getText() == null || _emailField.getText().length() == 0){
        	errorMessage += "No valid email!\n";
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

}
