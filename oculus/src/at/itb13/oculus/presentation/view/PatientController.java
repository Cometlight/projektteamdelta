package at.itb13.oculus.presentation.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.exceptions.InvalidInputException;
import at.itb13.oculus.application.patient.PatientSearch;
import at.itb13.oculus.domain.CalendarEvent;
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
import javafx.scene.layout.BorderPane;

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
	private BorderPane _patientRecord;
	
	@FXML
	private TextField _sinField;
	@FXML
	private TextField _firstnameSearch;
	@FXML
	private TextField _lastnameSearch;
	
	//general Attributs
	private OculusMain _main;
	
	//general Methods
	 public void setMain(OculusMain main) {
	        _main = main;
	  }
	 
	 
	 @FXML
	 private void initialize() {
	        // Initialize the person table with the three columns.
	        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("firstName"));
	        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("lastName"));
	        _SSNColumn.setCellValueFactory(new PropertyValueFactory<PatientRO, String>("socialInsuranceNr"));
		 
	        // Listen for selection changes and show the person details when changed.
	        _patientTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> _main.showPatientRecord(_patientRecord, newValue));

	    }
	 private void clearPatientTable(){		        	

	        // Clear person details.
		 	_main.clearPatientData();

	 }
	 
	 /**
	  * Controls the "search" Button, for searching by Social Insurancel Number.
	  * It gets a Patient from the PatientSearch Class and creates a new Patient with Property.
	  * The Patient Data are shown in the GUI
	  */
	 @FXML
	 private void searchByNumberControl(){
		 clearPatientTable();
		 at.itb13.oculus.application.patient.PatientController p = ControllerFacade.getInstance().getPatientController();
		 try {			
			PatientRO pa = p.searchPatientBySocialInsuranceNr(_sinField.getText());
			_main.addPatientData(pa);	// TODO: Why must a patient be stored in _main?
			_patientTable.setItems(_main.getPatientData());	
			_main.showPatientRecord(_patientRecord, pa);

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
		 at.itb13.oculus.application.patient.PatientController p = ControllerFacade.getInstance().getPatientController();
		 List<PatientRO> patients = new ArrayList<>();
		 try {			
			patients =  (List<PatientRO>) p.searchPatientByName(_firstnameSearch.getText(), _lastnameSearch.getText());
			if(patients.size() > 0){
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
