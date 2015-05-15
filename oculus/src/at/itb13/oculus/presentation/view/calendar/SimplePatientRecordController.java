package at.itb13.oculus.presentation.view.calendar;

import at.itb13.teamD.domain.interfaces.IPatient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 13.05.2015
 */
public class SimplePatientRecordController {

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
	private Label _phoneLabel;
	@FXML
	private Label _emailLabel;
	private IPatient _patient;
	private Stage _dialogStage;
	
	 @FXML
	 private void initialize() {}
	
	public void setPatient(IPatient patient){
		_patient = patient;
	}
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		_dialogStage.getIcons().add(new Image("file:ApplicationResources/Images/Auge.png"));
		
	}
	
	public void showPatientInfo(){
		
			String personal = new String();
			personal = personal + ((_patient.getFirstName()== null) ? "" : _patient.getFirstName()+" ");
			personal = personal +((_patient.getLastName()== null) ? "-\n" : _patient.getLastName()) + "\n";
			personal = personal + ((_patient.getSocialInsuranceNr()== null) ? "-" : _patient.getSocialInsuranceNr()) +"\n";
			personal = personal + ((_patient.getDateOfBirth()== null) ? "-\n" : _patient.getDateOfBirth().toString() + "\n");
			personal = personal + _patient.getGender().name();
			_personalLabel.setText(personal);
	    	_docLabel.setText(_patient.getDoctor().getUser().getFirstName() + " " +_patient.getDoctor().getUser().getLastName());           
	    	String address = new String();
	    	address = address + ((_patient.getStreet()== null)? "-\n" : _patient.getStreet()+"\n");
	    	address = address + ((_patient.getPostalCode()==null)?"": _patient.getPostalCode()+" ");
	    	address = address + ((_patient.getCity()== null)?"-\n": _patient.getCity()+"\n");
	    	address = address + ((_patient.getCountryIsoCode()==null)? "-\n" : _patient.getCountryIsoCode());
	    	_addressLabel.setText(address);
	    	_phoneLabel.setText(_patient.getPhone());
	    	_emailLabel.setText(_patient.getEmail());	
		
	}
}
