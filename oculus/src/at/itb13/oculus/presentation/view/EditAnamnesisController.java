package at.itb13.oculus.presentation.view;
import at.itb13.oculus.presentation.model.PatientWithProperties2;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
	private TextArea _childhoodAilments;
	@FXML
	private TextArea _medicineIntolerance;
	
	private PatientWithProperties2 _patient;
	
	private Stage _dialogStage;
    private boolean saveClicked = false;

}
