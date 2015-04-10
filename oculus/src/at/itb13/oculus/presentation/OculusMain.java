package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import at.itb13.oculus.presentation.model.PatientWithProperties;
import at.itb13.oculus.presentation.view.OverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OculusMain extends Application {
	
	 private Stage _primaryStage;
	 private BorderPane _rootLayout;
	 private ObservableList<PatientWithProperties> _patientData = FXCollections.observableArrayList();

	 public OculusMain(){
		 
		//_patientData.add(??);
	 }
	 
	 public ObservableList<PatientWithProperties> getPatientData() {
	        return _patientData;
	    }
	 
	 public void addPatientData(PatientWithProperties p){
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
