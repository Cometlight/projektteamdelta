package at.itb13.oculus.presentation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.view.TabAppointmentsController;
import at.itb13.oculus.presentation.view.EditAnamnesisController;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.TabPatientController;
import at.itb13.oculus.presentation.view.PatientRecordController;
import at.itb13.oculus.presentation.view.TabQueueController;
import at.itb13.oculus.presentation.view.RootLayoutController;
import at.itb13.oculus.presentation.view.StartProcessController;
import at.itb13.oculus.technicalServices.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
	private static final String APPLICATION_ICON_PATH = "file:ApplicationResources/Images/eye.png";
	
	private Scene _primaryScene;
	private Stage _primaryStage;
	private BorderPane _rootLayout;
	private RootLayoutController _rootLayoutController;
	
	private AnchorPane _appointmentsTab;
	private TabAppointmentsController _appointmentsController;
	private AnchorPane _patientTab;
	private TabPatientController _patientController;
	private AnchorPane _queueTab;
	private TabQueueController _queueController;
	
	private PatientRO _tempPatient;	// TODO: Should be moved to the application layer (into the ControllerFacade)

	private ObservableList<PatientRO> _patientData = FXCollections.observableArrayList();
	
	private SplashScreen _splashScreen;

	public OculusMain() { }
    
	@Override
	public void init() throws Exception {
		_splashScreen = new SplashScreen();
	}
	
	/**
	 * TODO: Insert Description
	 */
	@Override
	public void start(Stage primaryStage) {
		_logger.info("Starting OculusMain");
		
		_primaryStage = primaryStage;
		
		final Task<Integer> startupTask = new Task<Integer>() {
            @Override
            protected Integer call() throws InterruptedException {
            	updateMessage("Loading Application Icon...");
            	
        		// Set the application icon.
        		_primaryStage.getIcons().add(
        				new Image(APPLICATION_ICON_PATH));
        		
        		updateMessage("Connecting to database ...");
        		HibernateUtil.init();
        		
        		updateMessage("Loading from database ...");
        		ControllerFacade.init();	// Load early, so the user does not have to wait when using the application

        		updateMessage("Loading Main Tabs ...");
        		initRootLayout();
        		
        		initAppointmentsTab();
        		initPatientTab();
        		initQueueTab();
        		
        		updateMessage("Finished.");
        		
        		return 1;	// TODO
            }
        };
 
        _splashScreen.showSplash(new Stage(), startupTask, () -> showMainStage());
        new Thread(startupTask).start();
	}
	
    private void showMainStage() {
    	_primaryStage.setScene(_primaryScene);
    	_primaryStage.setTitle("Oculus");
		_primaryStage.setMinWidth(MIN_WIDTH);
		_primaryStage.setMinHeight(MIN_HEIGHT);
		_primaryStage.show();
		
		_logger.info("Finished starting OculusMain");
		
		showAppointmentsTab();
    }
	
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
			_primaryScene = new Scene(_rootLayout);

			// Give the controller access to the main app.
			_rootLayoutController = loader.getController();
			_rootLayoutController.setMain(this);
			
			_logger.info("initRootLayout() successful");
		} catch (IOException ex) {
			_logger.error(ex);
		}
	}
	
	
	/**
	 * Loads the view/tabPatient.fxml-File and display it in the center of the root-Layout.
	 */
	public void showPatientOverview() {
		if(_rootLayout != null) {
			try {
				// Load person overview.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(OculusMain.class
						.getResource("view/TabPatient.fxml"));
				AnchorPane overview = (AnchorPane) loader.load();
	
				// Set person overview into the center of root layout.
				_rootLayout.setCenter(overview);
	
				// Give the controller access to the main app.
				TabPatientController controller = loader.getController();
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
					.getResource("view/TabAppointments.fxml"));
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
					.getResource("view/TabPatient.fxml"));
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
					.getResource("view/TabQueue.fxml"));
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
	 * TODO
	 * 
	 * @param calendarEventRO
	 */
	public void showAppointment(CalendarEventRO calendarEventRO) {
		_appointmentsController.showAppointmentInformation(calendarEventRO);
		_appointmentsController.showPatientRecord(calendarEventRO.getPatient());
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
			
			_logger.info("editAnamnesisDialog successful");
			return controller.isSaveClicked();
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

}
