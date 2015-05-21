package at.itb13.oculus.presentation;

import java.io.IOException;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.domain.factories.CalendarEventFactory;
import at.itb13.oculus.domain.readonlyinterfaces.CalendarEventRO;
import at.itb13.oculus.domain.readonlyinterfaces.PatientRO;
import at.itb13.oculus.presentation.view.EditAnamnesisController;
import at.itb13.oculus.presentation.view.NewPatientController;
import at.itb13.oculus.presentation.view.PatientRecordController;
import at.itb13.oculus.presentation.view.RootLayoutController;
import at.itb13.oculus.presentation.view.TabAppointmentsController;
import at.itb13.oculus.presentation.view.TabPatientController;
import at.itb13.oculus.presentation.view.TabQueueController;
import at.itb13.oculus.technicalServices.HibernateUtil;
import at.itb13.oculus.technicalServices.exceptions.NoDatabaseConnectionException;
import at.itb13.oculus.technicalServices.persistencefacade.PersistenceFacade;
import at.itb13.teamD.application.CalendarEventFactoryProvider;
import at.itb13.teamD.presentation.controller.TabCalendarController;
import at.itb13.teamD.technicalServices.persistenceFacade.PersistenceFacadeProvider;
import at.itb13.teamF.factories.FactoryTeamF;
import at.itb13.teamF.persistence.PersistenceFacadeTeamF;
import at.oculus.teamf.application.facade.dependenceResolverTB2.DependenceResolverTB2;

/**
 * 
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @date 10.04.2015
 */
public class OculusMain extends Application {
	private static final Logger _logger = LogManager.getLogger(OculusMain.class.getName());
	private static final double MIN_WIDTH = 1024d;
	private static final double MIN_HEIGHT = 768d;
	private static final String APPLICATION_ICON_PATH = "file:ApplicationResources/Images/Auge.png";
	private static final int ERROR_TIME_BEFORE_SHUTDOWN = 4000;
	
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
	private AnchorPane _calendarTab;
	private TabCalendarController _calendarController;
	
	private ObservableList<PatientRO> _patientData = FXCollections.observableArrayList();
	
	private SplashScreen _splashScreen;

	public OculusMain() { }
    
	@Override
	public void init() throws Exception {
		_splashScreen = new SplashScreen();
	}
	
	/**
	 * is called when the program has been started
	 */
	@Override
	public void start(Stage primaryStage) {
		_logger.info("Starting OculusMain");
		
		_primaryStage = primaryStage;
		
		final Task<Void> startupTask = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
            	updateMessage("Loading Application Icon ...");
            	
        		// Set the application icon.
        		_primaryStage.getIcons().add(
        				new Image(APPLICATION_ICON_PATH));
        		
        		updateMessage("Connecting to database ...");
        		//Set PersistenceFacade
        		PersistenceFacadeProvider.setPersistenceFacade(new PersistenceFacade());
        		DependenceResolverTB2.init(new PersistenceFacadeTeamF(), new FactoryTeamF());
        		
        		try {
        			HibernateUtil.init();
        		} catch (NoDatabaseConnectionException ex) {
        			updateMessage("ERROR: Failed to connect to database!");
        			Thread.sleep(ERROR_TIME_BEFORE_SHUTDOWN);
        			System.exit(-1);
        		}
        		
        		updateMessage("Loading from database ...");
        		ControllerFacade.init();	// Load early, so the user does not have to wait when using the application

        		updateMessage("Loading Main Tabs ...");
        		CalendarEventFactoryProvider.setCalendarEventFactory(new CalendarEventFactory());
        		initRootLayout();
        		
        		initAppointmentsTab();
        		initPatientTab();
        		initQueueTab();
        		initCalendarTab();
        		
        		updateMessage("Finished.");
        		
				return null;
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
    
	/**
	 * is called when the "X" on the screen has been pushed
	 */
	@Override
	public void stop() throws Exception {
		_logger.info("Shutting down application...");
		super.stop();
		System.exit(0);	// TODO: Find another way to stop the application. Currently Hibernate won't stop, even when closing any exisiting SessionFactory.
	}

	/**
	 * initializes the root layout and opens the root layout screen
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
			_logger.error("initRootLayout() failed", ex);
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
			_logger.error("Fail: initQueueTab",ex);
		}
	}
	
	private void initCalendarTab(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OculusMain.class
					.getResource("/at/itb13/teamD/presentation/view/TabCalendar.fxml"));
			_calendarTab = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			_calendarController = loader.getController();

		} catch (IOException ex) {
			_logger.error("Fail: initCalendarTab",ex);
		}
	}
	
	public void showAppointmentsTab() {
		if(_appointmentsTab != null) {
			_rootLayout.setCenter(_appointmentsTab);
			_queueController.stopQueueReloader();
			_calendarController.stopCalendarReloader();
		}
	}
	
	public void showPatientTab() { 
		if(_patientTab != null) {
			_rootLayout.setCenter(_patientTab);
			_queueController.stopQueueReloader();
			_calendarController.stopCalendarReloader();
		}
	}
	
	public void showQueueTab() {
		if(_queueTab != null) {
			_rootLayout.setCenter(_queueTab);
			_queueController.startQueueReloader();
			_calendarController.stopCalendarReloader();
		}
	}
	public void showCalendarTab(){
		if(_calendarTab != null){
			_rootLayout.setCenter(_calendarTab);
			_queueController.stopQueueReloader();
			_calendarController.startCalendarReloader();
		}
	}
	
	/**
	 * is called when the button "edit" or "new patient" has been pushed
	 * 
	 * @return boolean
	 * @param patient
	 */
	public boolean showNewPatientDialog(PatientRO patient) {
		try {
			ControllerFacade.setPatientSelected(null);
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

			_logger.info("showNewPatientDialog successful");
			return controller.isOkClicked();
		} catch (IOException ex) {
			_logger.error("showNewPatientDialog failed", ex);
			return false;
		}
	}

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
				controller.showExaminationProtocols(patient);
				_logger.info("showPatientRecord");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			_logger.error("showPatientRecord failed", ex);
		}
		return null;
	}

	public void showAppointment(CalendarEventRO calendarEventRO) {
		_appointmentsController.showAppointmentInformation(calendarEventRO);
		_appointmentsController.showPatientRecord(calendarEventRO.getPatient());
		_appointmentsController.selectCalendarEventInTable(calendarEventRO);
		showAppointmentsTab();
		_rootLayoutController.setTab(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Window getPrimaryStage() {
		return _primaryStage;
	}

	public boolean showEditAnamnesis(PatientRO patient) {
		try {
		
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
			
			
			
			_logger.info("editAnamnesisDialog successful");
			return controller.isSaveClicked();
		} catch (IOException ex) {
			_logger.error("editAnamnesisDialog failed", ex);
			return false;
		}
		
	}
	
	public ObservableList<PatientRO> getPatientData() {
		return _patientData;
	}

	public void addPatientData(PatientRO p) {
		_patientData.add(p);
	}

	public void clearPatientData() {
		_patientData.clear();
	}



}
