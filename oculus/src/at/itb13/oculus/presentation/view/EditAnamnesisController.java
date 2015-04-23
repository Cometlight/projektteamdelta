package at.itb13.oculus.presentation.view;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.application.receptionist.WelcomeAtReception;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 10.04.2015
 */
public class EditAnamnesisController {
	
	@FXML
	private TextArea _allergiesText;
	@FXML
	private TextArea _childhoodAilmentsText;
	@FXML
	private TextArea _medicineIntoleranceText;
	

	private PatientRO _patient;
	private Stage _dialogStage;
    private boolean saveClicked = false;
    
	@FXML
	private void initialize() {}
	
    public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;

		// Set the application icon.
		_dialogStage.getIcons().add(new Image("file:resources/images/eye.png"));
	}
    public boolean isSaveClicked() {
		return saveClicked;
	}
	public PatientRO getPatient(){
		return _patient;
	}
	
	public void setPatientRO(PatientRO patient){
		_patient = patient;
		_allergiesText.setText(_patient.getAllergy());
		_childhoodAilmentsText.setText(_patient.getChildhoodAilments());
		_medicineIntoleranceText.setText(_patient.getMedicineIntolerance());
	}
	
	@FXML
	private void handleSave(){
		WelcomeAtReception controller = ControllerFacade.getInstance().getWelcomeAtReception();
		controller.updateAnamnesis(_patient,_allergiesText.getText(), _childhoodAilmentsText.getText(), _medicineIntoleranceText.getText());
		_dialogStage.close();
	}
	
	@FXML
	private void handleCancel() {
		_dialogStage.close();
	}

}
