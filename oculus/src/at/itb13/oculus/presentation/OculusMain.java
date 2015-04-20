package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.view.AppointmentsController;
import at.itb13.oculus.presentation.view.ControllerMainSetter;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.PatientController;
import at.itb13.oculus.presentation.view.PatientRecordController;
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

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @date 10.04.2015
 */
public class OculusMain extends Application {
	private static final Logger _logger = LogManager.getLogger(OculusMain.class.getName());
	private static final double MIN_WIDTH = 800d;
	private static final double MIN_HEIGHT = 600d;
	
	private Stage _primaryStage;
	private BorderPane _rootLayout;

	private PatientRO _tempPatient;

	private ObservableList<PatientRO> _patientData = FXCollections.observableArrayList();

	public OculusMain() { }

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public ObservableList<PatientRO> getPatientData() {
		return _patientData;
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @param p
	 */
	public void addPatientData(PatientRO p) {
		_patientData.add(p);
	}

	/**
	 * TODO: Insert Description
	 */
	public void clearPatientData() {
		_patientData.clear();

	}

	/**
	 * TODO: Insert Description
	 */
	@Override
	public void start(Stage primaryStage) {
		_logger.info("Starting OculusMain");
		_primaryStage = primaryStage;
		_primaryStage.setTitle("Oculus");
		_primaryStage.setMinWidth(MIN_WIDTH);
		_primaryStage.setMinHeight(MIN_HEIGHT);

		// Set the application icon.
		_primaryStage.getIcons().add(
				new Image("file:ApplicationResources/Images/eye.png"));

		ControllerFacade.init();	// Load early, so the user does not have to wait when using the application
			// TODO: Show splashscreen or progress bar while loading?
		initRootLayout();
		
		showAppointmentsOverview();

		_logger.info("Finished starting OculusMain");
	}

	/**
	 * TODO: Insert Description
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/RootLayout.fxml"));
			RootLayoutController rlc = new RootLayoutController();
			rlc.setMain(this);
			loader.setController(rlc);
			_rootLayout = loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(_rootLayout);
			_primaryStage.setScene(scene);
			_primaryStage.show();

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMain(this);
			
			_logger.info("initRootLayout() successful");
		} catch (IOException ex) {
			_logger.error(ex);
		}
	}
	
	/**
	 * Loads the .fxml-File and display it in the center of the root-Layout.
	 * 
	 * @param fxmlPath The path of the .fxml-File, eg. "view/PatientOverview.fxml"
	 * @param controllerClass The class of the associated controller. Must implement the interface IController.
	 */
	public <T extends ControllerMainSetter> void showTab(String fxmlPath, Class<T> controllerClass) {
		if(_rootLayout != null) {
			try {
				// Load person overview.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(OculusMain.class
						.getResource(fxmlPath));
				AnchorPane overview = (AnchorPane) loader.load();
	
				// Set person overview into the center of root layout.
				_rootLayout.setCenter(overview);
	
				// Give the controller access to the main app.
				T controller = loader.getController();
				controller.setMain(this);
				
				_logger.info("Successfully loaded " + controllerClass.getName());
			} catch (IOException ex) {
				_logger.error(ex);
			}
		}
	}

	/**
	 * TODO: Insert Description
	 */
	public void showPatientOverview() {
		showTab("view/PatientOverview.fxml", PatientController.class);
		
		// "Old" way of doing that:
//		if(_rootLayout != null) {
//			try {
//				// Load person overview.
//				FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(OculusMain.class
//						.getResource("view/PatientOverview.fxml"));
//				AnchorPane overview = (AnchorPane) loader.load();
//	
//				// Set person overview into the center of root layout.
//				_rootLayout.setCenter(overview);
//	
//				// Give the controller access to the main app.
//				PatientController controller = loader.getController();
//				controller.setMain(this);
//				
//				_logger.info("Successfully loaded PatientOverview");
//			} catch (IOException ex) {
//				_logger.error(ex);
//			}
//		}
	}

	/**
	 * TODO: Insert Description
	 */
	public void showAppointmentsOverview() {
		showTab("view/AppointmentsOverview.fxml", AppointmentsController.class);
		
		// "Old" way of doing that:
//		if(_rootLayout != null) {
//			try {
//				// Load person overview.
//				FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(OculusMain.class
//						.getResource("view/AppointmentsOverview.fxml"));
//				AnchorPane overview = (AnchorPane) loader.load();
//	
//				// Set person overview into the center of root layout.
//				_rootLayout.setCenter(overview);
//	
//				// Give the controller access to the main app.
//				AppointmentsController controller = loader.getController();
//				controller.setMain(this);
//	
//			} catch (IOException ex) {
//				_logger.error(ex);
//			}
//		}

	}

	/**
	 * TODO: Insert Description
	 */
	public void showQueue() {
		showTab("view/Queue.fxml", QueueController.class);
		
		// "Old" way of doing that:
//		if(_rootLayout != null) {
//			try {
//				// Load person overview.
//				FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(OculusMain.class.getResource("view/Queue.fxml"));
//				AnchorPane overview = (AnchorPane) loader.load();
//	
//				// Set person overview into the center of root layout.
//				_rootLayout.setCenter(overview);
//	
//				// Give the controller access to the main app.
//				QueueController controller = loader.getController();
//				controller.setMain(this);
//	
//			} catch (IOException ex) {
//				_logger.error(ex);
//			}
//		}
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public boolean showNewPatientDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/NewPatientDialog.fxml"));
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
			_tempPatient = controller.getPatient();
			_logger.info("showNewPatientDialog successful");
			return controller.isOkClicked();
		} catch (IOException ex) {
			_logger.error(ex);
			return false;
		}
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public PatientRO getCreatedPatient() {
		return _tempPatient;
	}


	/**
	 * TODO: Insert Description
	 * 
	 * @param newValue
	 * @return
	 */
	public Object showPatientRecord(BorderPane layout, PatientRO patient) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/PatientRecord.fxml"));
			AnchorPane overview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			layout.setCenter(overview);

			// Give the controller access to the main app.
			PatientRecordController controller = loader.getController();
			controller.setMain(this);
			controller.showPatientMasterData(patient);
			controller.showAnamanesis(patient);
			_logger.info("showPatientRecord");
		} catch (IOException ex) {
			ex.printStackTrace();
			_logger.error(ex);
		}
		return null;
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public File getPersonFilePath() {	// FIXME: Where is this method used?
		Preferences prefs = Preferences.userNodeForPackage(OculusMain.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @param file
	 */
	public void setPatientFilePath(File file) {	// FIXME: Where is this method used?
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

	/**
	 * TODO: Insert Description
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public Window getPrimaryStage() {
		return _primaryStage;
	}

}
