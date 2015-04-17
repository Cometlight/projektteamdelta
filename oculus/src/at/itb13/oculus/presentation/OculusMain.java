package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.view.AppointmentsController;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.PatientController;
import at.itb13.oculus.presentation.view.QueueController;
import at.itb13.oculus.presentation.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OculusMain extends Application {
	
	 private Stage _primaryStage;
	 private BorderPane _rootLayout;
	 
	 private PatientRO _tempPatient;
	
	 private ObservableList<PatientRO> _patientData = FXCollections.observableArrayList();

	 public OculusMain(){
		 
		//_patientData.add(??);
	 }
	 
	 public ObservableList<PatientRO> getPatientData() {
	        return _patientData;
	    }
	 
	 public void addPatientData(PatientRO p){
		 _patientData.add(p);
	 }
	public void clearPatientData() {
		_patientData.clear();
			
	}
	@Override
	public void start(Stage primaryStage) {
		 _primaryStage = primaryStage;
	     _primaryStage.setTitle("Oculus");

	     // Set the application icon.
	       _primaryStage.getIcons().add(new Image("file:ApplicationResources/Images/eye.png"));
	        
	        initRootLayout();
	        
        //   showAppointmentsOverview();
	        
	   
		
	}
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
        	
 	        
            FXMLLoader loader = new FXMLLoader();           
            loader.setLocation(OculusMain.class.getResource("view/RootLayout.fxml")); 
            _rootLayout = (BorderPane) loader.load();           
 	        
            // Show the scene containing the root layout.
            Scene scene = new Scene(_rootLayout);
            _primaryStage.setScene(scene);
            _primaryStage.show();            

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
 	        controller.setMain(this);             
          
 	       	
        } catch (IOException e) {
            e.printStackTrace();
        }
}
	 /**
	 * 
	 */
	public void showPatientOverview() {
		 try {
 	        // Load person overview.
 	        FXMLLoader loader = new FXMLLoader();
 	        loader.setLocation(OculusMain.class.getResource("view/PatientOverview.fxml"));
 	        AnchorPane overview = (AnchorPane) loader.load();	

 	        // Set person overview into the center of root layout.
 	        _rootLayout.setCenter(overview);

 	        // Give the controller access to the main app.
 	        PatientController controller = loader.getController();
 	        controller.setMain(this);

 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
		
	}
	public void showAppointmentsOverview() {
		 try {
	 	        // Load person overview.
	 	        FXMLLoader loader = new FXMLLoader();
	 	        loader.setLocation(OculusMain.class.getResource("view/AppointmentsOverview.fxml"));
	 	        AnchorPane overview = (AnchorPane) loader.load();	

	 	        // Set person overview into the center of root layout.
	 	        _rootLayout.setCenter(overview);

	 	        // Give the controller access to the main app.
	 	        AppointmentsController controller = loader.getController();
	 	        controller.setMain(this);

	 	    } catch (IOException e) {
	 	        e.printStackTrace();
	 	    }
		
	}

	public void showQueue() {
		 try {
	 	        // Load person overview.
	 	        FXMLLoader loader = new FXMLLoader();
	 	        loader.setLocation(OculusMain.class.getResource("view/Queue.fxml"));
	 	        AnchorPane overview = (AnchorPane) loader.load();	

	 	        // Set person overview into the center of root layout.
	 	        _rootLayout.setCenter(overview);

	 	        // Give the controller access to the main app.
	 	       QueueController controller = loader.getController();
	 	        controller.setMain(this);

	 	    } catch (IOException e) {
	 	        e.printStackTrace();
	 	    }
		
	}

	
	 public boolean showNewPatientDialog() {
	       try {
	           // Load the fxml file and create a new stage for the popup dialog.
	           FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(OculusMain.class.getResource("view/NewPatientDialog.fxml"));
	           AnchorPane page = (AnchorPane) loader.load();

	           // Create the dialog Stage.
	           Stage dialogStage = new Stage();
	           dialogStage.setTitle("New Patient");
	           dialogStage.initModality(Modality.WINDOW_MODAL);
	           dialogStage.initOwner(_primaryStage);
	           Scene scene = new Scene(page);
	           dialogStage.setScene(scene);

	           // Set the person into the controller.
	           NewPatientController controller = loader.getController();
	           controller.setDialogStage(dialogStage);
	          

	           // Show the dialog and wait until the user closes it
	           dialogStage.showAndWait();
	          // _tempPatient = controller.getPatient();
	           return controller.isOkClicked();
	       } catch (IOException e) {
	           e.printStackTrace();
	           return false;
	       }
	   }
	 
	 public PatientRO getCreatedPatient(){
		 return _tempPatient;
	 }
	  public File getPersonFilePath() {
	       Preferences prefs = Preferences.userNodeForPackage(OculusMain.class);
	       String filePath = prefs.get("filePath", null);
	       if (filePath != null) {
	           return new File(filePath);
	       } else {
	           return null;
	       }
	   }


	   public void setPatientFilePath(File file) {
	       Preferences prefs = Preferences.userNodeForPackage(OculusMain.class);
	       if (file != null) {
	           prefs.put("filePath", file.getPath());

	           // Update the stage title.
	           _primaryStage.setTitle("Oculus Patient - " + file.getName());
	       } else {
	           prefs.remove("filePath");

	           // Update the stage title.
	           _primaryStage.setTitle("Oculus");
	       }
	   }

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @return
	 */
	public Window getPrimaryStage() {
		return _primaryStage;
	}

	
}
