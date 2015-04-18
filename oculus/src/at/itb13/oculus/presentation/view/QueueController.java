package at.itb13.oculus.presentation.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import at.itb13.oculus.presentation.OculusMain;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class QueueController {
	
	@FXML
	private ListView _queueEntrys;
	
	//general Attributs
	private OculusMain _main;
			
	//general Methods
	public void setMain(OculusMain main) {
		_main = main;		        
	}
			 
	@FXML
	private void initialize() {}

}
