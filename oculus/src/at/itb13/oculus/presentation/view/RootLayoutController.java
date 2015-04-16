package at.itb13.oculus.presentation.view;

import at.itb13.oculus.presentation.OculusMain;
import javafx.fxml.FXML;


/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class RootLayoutController {
	

	
	//general Attributs
		private OculusMain _main;
		
		
		
		//general Methods
		 public void setMain(OculusMain main) {
		        _main = main;		        
		  }
		 
		 @FXML
		 private void initialize() {}
		 
		 @FXML
		 private void tabPatientControl(){
			
			_main.showPatientOverview();
			    
		 }
		 
		 @FXML
		 private void tabAppointmentsControl(){	
		
			_main.showAppointmentsOverview();
			    
		 }
		 
		 @FXML
		 private void tabQueueControl(){					
			_main.showQueue();
			    
		 }

}
