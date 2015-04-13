package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;


import at.itb13.oculus.presentation.model.PatientWithProperties2;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.OverviewController;
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

public class OculusMain extends Application {
	
	 private Stage _primaryStage;
	 private BorderPane _rootLayout;
	 private ObservableList<PatientWithProperties2> _patientData = FXCollections.observableArrayList();

	 public OculusMain(){
		 
		//_patientData.add(??);
	 }
	 
	 public ObservableList<PatientWithProperties2> getPatientData() {
	        return _patientData;
	    }
	 
	 public void addPatientData(PatientWithProperties2 p){
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
	        showOverview();
		
	}
	 /**
	 * 
	 */
	private void showOverview() {
		 try {
 	        // Load person overview.
 	        FXMLLoader loader = new FXMLLoader();
 	        loader.setLocation(OculusMain.class.getResource("view/Overview.fxml"));
 	        AnchorPane overview = (AnchorPane) loader.load();

 	        // Set person overview into the center of root layout.
 	        _rootLayout.setCenter(overview);

 	        // Give the controller access to the main app.
 	        OverviewController controller = loader.getController();
 	        controller.setMain(this);

 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
		
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

	           return controller.isOkClicked();
	       } catch (IOException e) {
	           e.printStackTrace();
	           return false;
	       }
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

	
}