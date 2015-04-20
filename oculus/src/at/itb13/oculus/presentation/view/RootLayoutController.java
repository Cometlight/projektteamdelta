package at.itb13.oculus.presentation.view;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.OculusMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 15.04.2015
 */
public class RootLayoutController implements ControllerMainSetter {
	private static final Logger _logger = LogManager.getLogger(RootLayoutController.class.getName());
	

	
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
		 
			/**
			 * @param newValue
			 * @return
			 */
			private Object showPatientRecord(BorderPane layout, CalendarEventRO event) {
				 try {
			 	        // Load person overview.
			 	        FXMLLoader loader = new FXMLLoader();
			 	        loader.setLocation(OculusMain.class.getResource("view/PatientRecord.fxml"));
			 	        AnchorPane overview = (AnchorPane) loader.load();	

			 	        // Set person overview into the center of root layout.
			 	        layout.setCenter(overview);

			 	        // Give the controller access to the main app.
			 	        PatientRecordController controller = loader.getController();
			 	        controller.setMain(_main);
			 	        controller.showPatientMasterData(event.getPatient());
			 	        controller.showAnamanesis(event.getPatient());

			 	    } catch (IOException e) {
			 	        e.printStackTrace();
			 	    }
				return null;
			}
			/**
			 * @param newValue
			 * @return
			 */
			private Object showPatientRecord(BorderPane layout, PatientRO patient) {
				 try {
			 	        // Load person overview.
			 	        FXMLLoader loader = new FXMLLoader();
			 	        loader.setLocation(OculusMain.class.getResource("view/PatientRecord.fxml"));
			 	        AnchorPane overview = (AnchorPane) loader.load();	

			 	        // Set person overview into the center of root layout.
			 	        layout.setCenter(overview);

			 	        // Give the controller access to the main app.
			 	        PatientRecordController controller = loader.getController();
			 	        controller.setMain(_main);
			 	        controller.showPatientMasterData(patient);
			 	        controller.showAnamanesis(patient);

			 	    } catch (IOException e) {
			 	        e.printStackTrace();
			 	    }
				return null;
			}

}
