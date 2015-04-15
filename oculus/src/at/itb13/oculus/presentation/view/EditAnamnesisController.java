package at.itb13.oculus.presentation.view;

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
	

	
	private Stage _dialogStage;
    private boolean saveClicked = false;

}
