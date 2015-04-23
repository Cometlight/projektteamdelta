package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.view.AppointmentsController;
import at.itb13.oculus.presentation.view.EditAnamnesisController;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.PatientController;
import at.itb13.oculus.presentation.view.PatientRecordController;
import at.itb13.oculus.presentation.view.QueueController;
import at.itb13.oculus.presentation.view.RootLayoutController;
import at.itb13.oculus.presentation.view.StartProcessController;
import at.itb13.oculus.technicalServices.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
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
	private RootLayoutController _rootLayoutController;
	
	private AnchorPane _appointmentsTab;
	private AppointmentsController _appointmentsController;
	private AnchorPane _patientTab;
	private PatientController _patientController;
	private AnchorPane _queueTab;
	private QueueController _queueController;
	
	private PatientRO _tempPatient;	// TODO: Should be moved to the application layer (into the ControllerFacade)

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
		//initStartLayout();
		initRootLayout();
		
		initAppointmentsTab();
		initPatientTab();
		initQueueTab();
		
		showAppointmentsTab();

		_logger.info("Finished starting OculusMain");
	}
	
	/*
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception {
		_logger.info("Shutting down application...");
		super.stop();
		System.exit(0);	// TODO: Find another way to stop the application. Currently Hibernate won't stop, even when closing any SessionFactory.
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
			rlc.setTabPane((TabPane)_rootLayout.getTop());

			// Show the scene containing the root layout.
			Scene scene = new Scene(_rootLayout);
			_primaryStage.setScene(scene);
			_primaryStage.show();

			// Give the controller access to the main app.
			_rootLayoutController = loader.getController();
			_rootLayoutController.setMain(this);
			
			_logger.info("initRootLayout() successful");
		} catch (IOException ex) {
			_logger.error(ex);
		}
	}
	public void initStartLayout() {
		try {
		
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/StartPatient.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Start Oculus");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(_primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewPatientController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			_logger.info("showNewPatientDialog successful");
		
		} catch (IOException ex) {
			_logger.error(ex);
		
		}
	}
	
	/**
	 * Loads the .fxml-File and display it in the center of the root-Layout.
	 * 
	 * @param fxmlPath The path of the .fxml-File, eg. "view/PatientOverview.fxml"
	 * @param controllerClass The class of the associated controller. Must implement the interface IController.
	 * @return A reference to the created controller. May be null, if failed to do so.
	 */
//	 <T extends ControllerMainSetter> T showTab(String fxmlPath, Class<T> controllerClass) {
//		T controller = null;
//		if(_rootLayout != null) {
//			try {
//				// Load person overview.
//				FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(OculusMain.class
//						.getResource(fxmlPath));
//				AnchorPane overview = (AnchorPane) loader.load();
//	
//				// Set person overview into the center of root layout.
//				_rootLayout.setCenter(overview);
//	
//				// Give the controller access to the main app.
//				controller = loader.getController();
//				controller.setMain(this);
//				
//				_logger.info("Successfully loaded " + controllerClass.getName());
//			} catch (IOException ex) {
//				_logger.error(ex);
//			}
//		}
//		return controller;
//	}

	/**
	 * TODO: Insert Description
	 * @return 
	 */
	public void showPatientOverview() {
//		return showTab("view/PatientOverview.fxml", PatientController.class);
		
		// "Old" way of doing that:
		if(_rootLayout != null) {
			try {
				// Load person overview.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(OculusMain.class
						.getResource("view/PatientOverview.fxml"));
				AnchorPane overview = (AnchorPane) loader.load();
	
				// Set person overview into the center of root layout.
				_rootLayout.setCenter(overview);
	
				// Give the controller access to the main app.
				PatientController controller = loader.getController();
				controller.setMain(this);
				
				_logger.info("Successfully loaded PatientOverview");
			} catch (IOException ex) {
				_logger.error(ex);
			}
		}
	}
	
	private void initAppointmentsTab() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/AppointmentsOverview.fxml"));
			_appointmentsTab = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			_appointmentsController = loader.getController();
			_appointmentsController.setMain(this);

		} catch (IOException ex) {
			_logger.error(ex);
		}
	}
	
	private void initPatientTab() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/PatientOverview.fxml"));
			_patientTab = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			_patientController = loader.getController();
			_patientController.setMain(this);

		} catch (IOException ex) {
			_logger.error(ex);
		}
	}
	
	private void initQueueTab() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/Queue.fxml"));
			_queueTab = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			_queueController = loader.getController();
			_queueController.setMain(this);

		} catch (IOException ex) {
			_logger.error(ex);
		}
	}

	public void showAppointmentsTab() {
		if(_appointmentsTab != null) {
			_rootLayout.setCenter(_appointmentsTab);
			_queueController.stopQueueReloader();
		}
	}
	
	public void showPatientTab() {
		if(_patientTab != null) {
			_rootLayout.setCenter(_patientTab);
			_queueController.stopQueueReloader();
		}
	}
	
	public void showQueueTab() {
		if(_queueTab != null) {
			_rootLayout.setCenter(_queueTab);
			_queueController.startQueueReloader();
		}
	}
	

	/**
	 * TODO: Insert Description
	 * 
	 * @return
	 */
	public boolean showNewPatientDialog(PatientRO patient) {
		try {
			_tempPatient = null;
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/NewPatientDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			if(patient == null){
				dialogStage.setTitle("New Patient");
			}else{
				dialogStage.setTitle("Edit Patient");
			}
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(_primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewPatientController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPatientRO(patient);

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
			if(patient != null) {
				controller.showPatientMasterData(patient);
				controller.showAnamanesis(patient);
				controller.showAppointments(patient);
				_logger.info("showPatientRecord");
			}
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
	 * TODO
	 * 
	 * @param calendarEventRO
	 */
	public void showAppointment(CalendarEventRO calendarEventRO) {
		_appointmentsController.showAppointmentInformation(calendarEventRO);
		showAppointmentsTab();
		_rootLayoutController.setTab(0);
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

	/**
	 * @return 
	 * 
	 */
	public boolean showEditAnamnesis(PatientRO patient) {
		try {
			_tempPatient = null;
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("view/EditAnamnesisDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Anamnesis");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(_primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			EditAnamnesisController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPatientRO(patient);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			_tempPatient = controller.getPatient();
			
			_logger.info("showNewPatientDialog successful");
			return controller.isSaveClicked();
		} catch (IOException ex) {
			_logger.error(ex);
			return false;
		}
		
	}

}
